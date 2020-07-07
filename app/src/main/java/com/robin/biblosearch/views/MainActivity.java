package com.robin.biblosearch.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.robin.biblosearch.R;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    public static final String INTENT_KEY = "com.robin.biblosearch.INTENT_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, RecentsFavouritesActivity.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
}