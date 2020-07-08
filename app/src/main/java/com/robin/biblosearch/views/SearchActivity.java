package com.robin.biblosearch.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.robin.biblosearch.R;
import com.robin.biblosearch.models.VolumeInfo;
import com.robin.biblosearch.viewmodels.SearchActivityViewModel;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private SearchActivityViewModel mViewModel;
    private static final String TAG = SearchActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mViewModel = new ViewModelProvider(this).get(SearchActivityViewModel.class);

        setTitle("Search");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSearch("harry");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    public void getSearch(String keyword){
        mViewModel.getSearchResult(keyword).observe(this, volumes -> {
            if (volumes != null){
                Log.e(TAG, volumes.toString());
            }
        });
    }
}