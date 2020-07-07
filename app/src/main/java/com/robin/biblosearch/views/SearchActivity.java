package com.robin.biblosearch.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.robin.biblosearch.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setTitle("Search");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}