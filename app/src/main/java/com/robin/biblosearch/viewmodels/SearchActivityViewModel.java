package com.robin.biblosearch.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robin.biblosearch.datasource.Repository;
import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.SearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivityViewModel extends AndroidViewModel {
    Repository repository;
    private static final String TAG = SearchActivityViewModel.class.getSimpleName();
    private MutableLiveData<List<Item>> mItems;

    public SearchActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }


    public AsyncTask<String, Void, Void> getSearchResult(String keywords){
        mItems = new MutableLiveData();

        @SuppressLint("StaticFieldLeak")
        class FetchBooks extends AsyncTask<String, Void, Void> {

            @Override
            protected Void doInBackground(String... strings) {
                repository.getSearchResults(keywords).enqueue(
                        new Callback<SearchResponse>() {
                            @Override
                            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                                mItems.postValue(response.body().getItems());
                            }

                            @Override
                            public void onFailure(Call<SearchResponse> call, Throwable t) {
                                mItems= null;
                                Log.e(TAG, t.getMessage());
                            }
                        }
                );
                return  null;
            }
        }

        return new FetchBooks().execute(keywords);
    }

    public MutableLiveData<List<Item>> getItems() {
        return mItems;
    }
}


