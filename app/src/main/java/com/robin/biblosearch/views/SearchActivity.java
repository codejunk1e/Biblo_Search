package com.robin.biblosearch.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.robin.biblosearch.R;
import com.robin.biblosearch.adapters.SearchVolumeAdapter;
import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.SearchResponse;
import com.robin.biblosearch.models.VolumeInfo;
import com.robin.biblosearch.viewmodels.SearchActivityViewModel;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.robin.biblosearch.views.MainActivity.INTENT_SEARCH_KEY;

public class SearchActivity extends AppCompatActivity implements SearchVolumeAdapter.OncClickLister {
    private SearchActivityViewModel mViewModel;
    private static final String TAG = SearchActivity.class.getSimpleName();
    public static final  String BOOK_EXTRA_KEY = "com.robin.biblosearch.BOOK_EXTRA_KEY";
    List<Item> items;
    RecyclerView recyclerView;
    private SearchVolumeAdapter searchVolumeAdapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(SearchActivityViewModel.class);
        recyclerView = findViewById(R.id.search_recycler);
        progressBar = findViewById(R.id.search_load_progress);
        progressBar.setVisibility(View.VISIBLE);

        setTitle("Search");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        getSearch(getIntent().getStringExtra(INTENT_SEARCH_KEY));
        searchVolumeAdapter= new SearchVolumeAdapter(items, this, this);
        recyclerView.setAdapter(searchVolumeAdapter);
    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

*/
    public void getSearch(String keyword){
        mViewModel.getSearchResult(keyword).observe(this, rxResult ->{
            if (rxResult.getResult()!= null && rxResult.getError() == null){
                this.items = rxResult.getResult();
                searchVolumeAdapter = new SearchVolumeAdapter(items, this,  this);
                recyclerView.setAdapter(searchVolumeAdapter);
                Log.e(TAG, items.toString());
                progressBar.setVisibility(View.INVISIBLE);
            }

            else {
                rxResult.getError().printStackTrace();
            }
        });
    }

    @Override
    public void onClickSearchItem(int position) {

        Intent intent = new Intent(this, BookDetailsActivity.class);
        VolumeInfo volumeInfo = items.get(position).getVolumeInfo();
        volumeInfo.setId(items.get(position).getId());
        if (items.get(position).getVolumeInfo().getImageLinks() != null) {
            volumeInfo.setThumbnail(items.get(position).getVolumeInfo().getImageLinks().getThumbnail());
        }
        Log.e(TAG, String.valueOf(new Date()));
        intent.putExtra(BOOK_EXTRA_KEY, volumeInfo);
        startActivity(intent);

    }
}

