package com.robin.biblosearch.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.robin.biblosearch.R;
import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.VolumeInfo;
import com.robin.biblosearch.viewmodels.BookDetailsViewModel;
import com.robin.biblosearch.widget.FavBookService;

import java.util.Date;

import static com.robin.biblosearch.views.SearchActivity.BOOK_EXTRA_KEY;

public class BookDetailsActivity extends AppCompatActivity {
    private VolumeInfo item;
    ImageView bookImage;
    TextView title;
    TextView subTitle;
    TextView authors;
    TextView publisher;
    TextView description;
    MaterialButton readPreview;
    FloatingActionButton fabFavourite;
    private String BookId;
    AdView mAdView;
    private SharedPreferences preferences;
    public static final String FAVORITE_KEY= "com.robin.biblosearch.FAVORITE_KEY";
    BookDetailsViewModel bookDetailsViewModel;
    private FirebaseAnalytics mFirebaseAnalytics;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        preferences = getSharedPreferences(FAVORITE_KEY, MODE_PRIVATE);
        bookDetailsViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(BookDetailsViewModel.class);


        setTitle("Details");
        bookImage = findViewById(R.id.details_book_image);
        title = findViewById(R.id.details_book_title);
        subTitle = findViewById(R.id.details_book_subtitle);
        authors = findViewById(R.id.details_book_authors);
        publisher = findViewById(R.id.details_book_publisher);
        description = findViewById(R.id.details_book_description);
        fabFavourite = findViewById(R.id.fab_favourite);
        readPreview = findViewById(R.id.button_read_preview);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        if (getIntent().hasExtra(BOOK_EXTRA_KEY)){
            item = getIntent().getParcelableExtra(BOOK_EXTRA_KEY);
            item.setUpdatedAt(new Date());


            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, item.getId());
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getTitle());
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "volume");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        }

        Glide.with(this)
                .load(item.getThumbnail())
                .override(128,192)
                .error(R.drawable.default_image)
                .into(bookImage);
        title.setText(item.getTitle());
        subTitle.setText(item.getSubtitle());
        authors.setText(item.getAuthorsInString());
        publisher.setText(item.getPublisher());
        description.setText(item.getDescription());
        BookId = item.getId();


        checkForFabState();
        listeners();


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setUpAds();

    }

    private void setUpAds() {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d("Ads", initializationStatus.toString());
            }
        });

        mAdView = findViewById(R.id.details_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void listeners() {
        readPreview.setOnClickListener(v -> {
            openWebPage( item.getPreviewLink());
                }
        );
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void shareBook() {
        ShareCompat.IntentBuilder
                .from(this)
                .setSubject(String.valueOf(title.getText()))
                .setText(description.getText() + " Read Preview here " + item.getPreviewLink())
                .setType("text/plain")
                .setChooserTitle("Share to...")
                .startChooser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deets_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_share){
            shareBook();
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkForFabState(){
        if (preferences.getBoolean(BookId, false)) {
            fabFavourite.setImageDrawable(getResources().getDrawable(R.drawable.ic__star_filled));

        } else {
            fabFavourite.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_empty));
        }
    }

    public void fabClicked(View view) {

        if (!preferences.getBoolean(BookId, false)) {
            fabFavourite.setImageDrawable(getResources().getDrawable(R.drawable.ic__star_filled));
            updateValue(true);
            item.setFavourite(true);
            bookDetailsViewModel.insertIntoDatabase(item);

        } else {
            fabFavourite.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_empty));
            updateValue(false);
            item.setFavourite(false);
            FavBookService.startActionUpdateWidgets(this);
        }
    }

    private void updateValue( Boolean newValue) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(BookId, newValue);
        editor.apply();
    }


    @Override
    protected void onStop() {
        bookDetailsViewModel.insertIntoDatabase(item);
        super.onStop();
    }
}