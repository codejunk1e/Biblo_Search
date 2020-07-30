package com.robin.biblosearch.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robin.biblosearch.datasource.Repository;
import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.SearchResponse;
import com.robin.biblosearch.utils.RxResult;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivityViewModel extends AndroidViewModel {
    Repository repository;
    private static final String TAG = SearchActivityViewModel.class.getSimpleName();

    public SearchActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }


    public LiveData<RxResult<List<Item>>>  getSearchResult(String keywords){
        return LiveDataReactiveStreams.fromPublisher(
                repository.getSearchResults(keywords)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(SearchResponse::getItems)
                        .map(RxResult::success)
                        .onErrorReturn(RxResult::error)
                        .toFlowable(BackpressureStrategy.MISSING)
        );
    };
}


