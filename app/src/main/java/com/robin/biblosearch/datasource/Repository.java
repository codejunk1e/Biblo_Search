package com.robin.biblosearch.datasource;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.robin.biblosearch.datasource.local.Database;
import com.robin.biblosearch.datasource.remote.ApiEndpoints;
import com.robin.biblosearch.datasource.remote.ApiService;
import com.robin.biblosearch.models.SearchResponse;
import com.robin.biblosearch.models.VolumeInfo;
import com.robin.biblosearch.utils.AppExecutors;
import com.robin.biblosearch.utils.RxResult;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;

public class Repository {
    ApiEndpoints apiEndpoints = ApiService.buildService(ApiEndpoints.class);
    private static final String TAG = Repository.class.getSimpleName();
    private final Database database;


    public Observable<SearchResponse> getSearchResults(String keywords){
        return apiEndpoints.searchBooks(keywords);
    }


    public Repository(Application application) {
        database = Database.getInstance(application);
    }

    public LiveData<List<VolumeInfo>> getAllBooks (){
        return database.bookDao().loadAllBookss();
    }

    public void insertIntoDatabase(VolumeInfo item) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            database.bookDao().insertBook(item);
        });
    }

    public void deleteFromDatabase(VolumeInfo item) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            database.bookDao().deleteBook(item);
        });
    }

    public LiveData<List<VolumeInfo>> getAllFavourites() {
        return database.bookDao().getAllFavourites();
    }
}
