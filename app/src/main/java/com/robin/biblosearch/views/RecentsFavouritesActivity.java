package com.robin.biblosearch.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.robin.biblosearch.R;
import com.robin.biblosearch.adapters.VolumeAdapter;
import com.robin.biblosearch.models.VolumeInfo;
import com.robin.biblosearch.viewmodels.RecentsFavouritesViewModel;

import java.util.List;

import static com.robin.biblosearch.views.MainActivity.INTENT_KEY;
import static com.robin.biblosearch.views.SearchActivity.BOOK_EXTRA_KEY;

public class RecentsFavouritesActivity extends AppCompatActivity implements VolumeAdapter.OncClickLister {
    private String EXTRA_VALUE;
    List<VolumeInfo> items;
    private static final String TAG = RecentsFavouritesActivity.class.getSimpleName();
    RecentsFavouritesViewModel recentsFavouritesViewModel;
    RecyclerView recyclerView;
    private ProgressBar progressBar;
    private VolumeAdapter volumeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recents_favourites);
        recyclerView = findViewById(R.id.recents_fav_recyclerview);
        progressBar = findViewById(R.id.recents_fav_load_progress);
        progressBar.setVisibility(View.VISIBLE);
        recentsFavouritesViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(RecentsFavouritesViewModel.class);


        if(getIntent().hasExtra(INTENT_KEY)){
            EXTRA_VALUE = getIntent().getStringExtra(INTENT_KEY);
        }


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(EXTRA_VALUE);
        }

        if (EXTRA_VALUE.equals(getResources().getString(R.string.recents))){
            getRecents();
        }else {
            getFavourites();
        }
    }

    private void getFavourites() {
        recentsFavouritesViewModel.getAllFavourites().observe(this, volumeInfos -> {
            Log.e(TAG, volumeInfos.toString());
            items = volumeInfos;
            volumeAdapter = new VolumeAdapter(volumeInfos, this, this);
            recyclerView.setAdapter(volumeAdapter);
            progressBar.setVisibility(View.INVISIBLE);
        });
    }

    private void getRecents() {
        recentsFavouritesViewModel.getAllRecents().observe(this, volumeInfos -> {
            items = volumeInfos;
            Log.e(TAG, volumeInfos.toString());
            volumeAdapter = new VolumeAdapter(volumeInfos, this, this);
            recyclerView.setAdapter(volumeAdapter);
            progressBar.setVisibility(View.INVISIBLE);
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickVolumeItem(int position) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra(BOOK_EXTRA_KEY, items.get(position));
        startActivity(intent);
    }
}