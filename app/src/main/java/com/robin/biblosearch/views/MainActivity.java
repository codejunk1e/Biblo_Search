package com.robin.biblosearch.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.robin.biblosearch.R;
import com.robin.biblosearch.adapters.TruncatedVolumeAdapter;
import com.robin.biblosearch.models.VolumeInfo;
import com.robin.biblosearch.viewmodels.RecentsFavouritesViewModel;

import java.util.List;

import static com.robin.biblosearch.views.SearchActivity.BOOK_EXTRA_KEY;

public class MainActivity extends AppCompatActivity implements TruncatedVolumeAdapter.OncClickLister {
    Intent intent;
    List<VolumeInfo> items;
    private TruncatedVolumeAdapter volumeAdapter;
    public static final String INTENT_KEY = "com.robin.biblosearch.INTENT_KEY";
    public static final String INTENT_SEARCH_KEY = "com.robin.biblosearch.INTENT_SEARCH_KEY";
    private SearchView searchView;
    private RecyclerView recentsRecyclerView;
    private RecyclerView favouritesRecyclerView;
    private TextView emptyFavesTextView;
    private TextView emptyRecentsTextView;
    RecentsFavouritesViewModel recentsFavouritesViewModel;
    AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, RecentsFavouritesActivity.class);
        recentsRecyclerView = findViewById(R.id.recyclerView_recents);
        favouritesRecyclerView = findViewById(R.id.recyclerView_favourites);
        emptyFavesTextView = findViewById(R.id.empty_favs);
        emptyRecentsTextView = findViewById(R.id.empty_recents);
        recentsFavouritesViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(RecentsFavouritesViewModel.class);
        getFavouritesAndRecents();
        setUpAds();
    }

    private void setUpAds() {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d("Ads", initializationStatus.toString());
            }
        });

        mAdView = findViewById(R.id.main_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        setUpSearch();
        return true;
    }

    public void openRecents(View view) {
        intent.putExtra(INTENT_KEY,  getResources().getString(R.string.recents));
        startActivity(intent);
    }

    public void openFavourites(View view) {
        intent.putExtra(INTENT_KEY,  getResources().getString(R.string.favourites));
        startActivity(intent);
    }

    private void setUpSearch(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra(INTENT_SEARCH_KEY, query);
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void getFavouritesAndRecents(){
        getFavourites();
        getRecents();
    }


    private void getFavourites() {
        recentsFavouritesViewModel.getAllFavourites().observe(this, volumeInfos -> {

            if(volumeInfos.isEmpty()){
                favouritesRecyclerView.setVisibility(View.GONE);
                emptyFavesTextView.setVisibility(View.VISIBLE);
            }else{
                items = volumeInfos;
                volumeAdapter = new TruncatedVolumeAdapter(volumeInfos, this, this);
                favouritesRecyclerView.setAdapter(volumeAdapter);
            }
        });
    }

    private void getRecents() {
        recentsFavouritesViewModel.getAllRecents().observe(this, volumeInfos -> {
            if(volumeInfos.isEmpty()){
                recentsRecyclerView.setVisibility(View.GONE);
                emptyRecentsTextView.setVisibility(View.VISIBLE);
            }else{
                items = volumeInfos;
                volumeAdapter = new TruncatedVolumeAdapter(volumeInfos, this, this);
                recentsRecyclerView.setAdapter(volumeAdapter);
            }
        });
    }

    @Override
    public void onClickVolumeItem(int position) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra(BOOK_EXTRA_KEY, items.get(position));
        startActivity(intent);
    }
}