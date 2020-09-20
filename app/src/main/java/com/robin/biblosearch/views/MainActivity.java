package com.robin.biblosearch.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.robin.search.internal.SearchLayout;
import com.robin.search.widget.MaterialSearchView;
import com.robin.biblosearch.R;
import com.robin.biblosearch.adapters.SearchVolumeAdapter;
import com.robin.biblosearch.adapters.TruncatedVolumeAdapter;
import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.VolumeInfo;
import com.robin.biblosearch.viewmodels.RecentsFavouritesViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    public static final String BOOK_EXTRA_KEY = "com.robin.biblosearch.BOOK_EXTRA_KEY";
    public static final String INTENT_KEY = "com.robin.biblosearch.INTENT_KEY";
    public static final String INTENT_SEARCH_KEY = "com.robin.biblosearch.INTENT_SEARCH_KEY";
    private static final String TAG = "MainActivity";
    Intent intent;
    MaterialSearchView materialSearchView;
    List<Item> items;
    RecentsFavouritesViewModel recentsFavouritesViewModel;
    AdView mAdView;
    private TruncatedVolumeAdapter recentVolumeAdapter;
    private TruncatedVolumeAdapter favVolumeAdapter;
    private SearchView searchView;
    private RecyclerView recentsRecyclerView;
    private RecyclerView favouritesRecyclerView;
    private TextView emptyFavesTextView;
    private SearchVolumeAdapter searchVolumeAdapter;
    private TextView emptyRecentsTextView;
    private GoogleSignInAccount lastSignedInAccount;

    @Override
    protected void onResume() {
        super.onResume();
        getFavouritesAndRecents();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, RecentsFavouritesActivity.class);
        lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this);


        recentsRecyclerView = findViewById(R.id.recyclerView_recents);
        recentVolumeAdapter = new TruncatedVolumeAdapter(null, this);
        recentsRecyclerView.setAdapter(recentVolumeAdapter);

        favouritesRecyclerView = findViewById(R.id.recyclerView_favourites);
        favVolumeAdapter = new TruncatedVolumeAdapter(null, this);
        favouritesRecyclerView.setAdapter(favVolumeAdapter);

        emptyFavesTextView = findViewById(R.id.empty_favs);
        emptyRecentsTextView = findViewById(R.id.empty_recents);
        recentsFavouritesViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(RecentsFavouritesViewModel.class);


        searchVolumeAdapter = new SearchVolumeAdapter(MainActivity.this, position -> {
            Intent intent = new Intent(MainActivity.this, BookDetailsActivity.class);
            VolumeInfo volumeInfo = items.get(position).getVolumeInfo();
            volumeInfo.setId(items.get(position).getId());
            if (items.get(position).getVolumeInfo().getImageLinks() != null) {
                volumeInfo.setThumbnail(items.get(position).getVolumeInfo().getImageLinks().getThumbnail());
            }

            Log.e(TAG, String.valueOf(new Date()));
            intent.putExtra(BOOK_EXTRA_KEY, volumeInfo);
            startActivity(intent);

        });
        getFavouritesAndRecents();
        setUpAds();
        setUpSearchView();
        enableStrictMode();
    }

    private void setUpSearchView() {
        materialSearchView = findViewById(R.id.material_search_view);
        materialSearchView.setAdapterLayoutManager(new LinearLayoutManager(this));
        materialSearchView.setNavigationIconSupport(SearchLayout.NavigationIconSupport.MENU);
        materialSearchView.setAdapter(searchVolumeAdapter);

        Glide.with(this)
                .load(Objects.requireNonNull(lastSignedInAccount.getPhotoUrl()).toString())
                .error(R.drawable.ic_user)
                .circleCrop()
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull @NotNull Drawable resource, @Nullable @org.jetbrains.annotations.Nullable Transition<? super Drawable> transition) {
                        materialSearchView.setMenuIconImageDrawable(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable @org.jetbrains.annotations.Nullable Drawable placeholder) {
                    }
                });

/*
        materialSearchView.setOnNavigationClickListener(() -> {
            Toast.makeText(
                    this,
                    "Feature Incoming... \n Search to get gooks you want for now!",
                    Toast.LENGTH_LONG).show();
        });

*/
        materialSearchView.setOnQueryTextListener(new SearchLayout.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(@NotNull CharSequence query) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(@NotNull CharSequence query) {
                recentsFavouritesViewModel.performSearch(query.toString());
                recentsFavouritesViewModel.getSearchResult().observe(MainActivity.this, rxResult -> {

                    if (rxResult.getResult() != null && rxResult.getError() == null) {
                        Log.e("OKHttp", "Observed Again!!!");
                        items = rxResult.getResult();
                        searchVolumeAdapter.updateList(items);
                        Log.e(TAG, MainActivity.this + " " + items.toString());
                    } else {
                        assert rxResult.getError() != null;
                        rxResult.getError().printStackTrace();
                    }
                });
                closeKeyboard();
                return true;
            }
        });
        materialSearchView.setTextHint(getString(R.string.search_books));
        materialSearchView.setElevation(8f);
        materialSearchView.setBackgroundStrokeColor(ContextCompat.getColor(this, R.color.divider));
        materialSearchView.setClearFocusOnBackPressed(true);
        materialSearchView.setOnFocusChangeListener(b -> {
            if (b) {
                materialSearchView.setNavigationIconSupport(SearchLayout.NavigationIconSupport.ARROW);
            } else {
                materialSearchView.setNavigationIconSupport(SearchLayout.NavigationIconSupport.MENU);
            }
        });

    }

    private void setUpAds() {

        MobileAds.initialize(this, initializationStatus -> Log.d("Ads", initializationStatus.toString()));

        mAdView = findViewById(R.id.main_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void openRecents(View view) {
        intent.putExtra(INTENT_KEY, getResources().getString(R.string.recents));
        startActivity(intent);
    }

    public void openFavourites(View view) {
        intent.putExtra(INTENT_KEY, getResources().getString(R.string.favourites));
        startActivity(intent);
    }


    public void getFavouritesAndRecents() {
        getFavourites();
        getRecents();
    }

    private void getFavourites() {
        recentsFavouritesViewModel.getAllFavourites().observe(this, volumeInfos -> {

            if (volumeInfos.isEmpty()) {
                toggleVisibility(favouritesRecyclerView, emptyFavesTextView);
            } else {
                favVolumeAdapter.update(volumeInfos);
            }
        });
    }

    private void getRecents() {
        recentsFavouritesViewModel.getAllRecents().observe(this, volumeInfos -> {
            if (volumeInfos.isEmpty()) {
                toggleVisibility(recentsRecyclerView, emptyRecentsTextView);
            } else {
                recentVolumeAdapter.update(volumeInfos);
            }
        });
    }

    private void toggleVisibility(RecyclerView recyclerView, TextView textView) {
        recyclerView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void enableStrictMode() {
        StrictMode.ThreadPolicy strictMode = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDialog()
                .build();
        StrictMode.setThreadPolicy(strictMode);
    }


}