package com.robin.biblosearch.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.robin.biblosearch.R;

import static com.robin.biblosearch.views.MainActivity.INTENT_KEY;

public class RecentsFavouritesActivity extends AppCompatActivity {
    private String EXTRA_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recents_favourites);

        if(getIntent().hasExtra(INTENT_KEY)){
            EXTRA_VALUE = getIntent().getStringExtra(INTENT_KEY);
        }


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(EXTRA_VALUE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}