package com.robin.biblosearch.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;

import com.robin.biblosearch.datasource.Repository;
import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.SearchResponse;
import com.robin.biblosearch.models.VolumeInfo;
import com.robin.biblosearch.utils.RxResult;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecentsFavouritesViewModel extends AndroidViewModel {
    Repository repository;
    private LiveData<RxResult<List<Item>>> results;


    public RecentsFavouritesViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<VolumeInfo>> getAllRecents() {
        return repository.getAllBooks();
    }

    public LiveData<List<VolumeInfo>> getAllFavourites() {
        return repository.getAllFavourites();
    }

    public void performSearch (String keywords){
        results =  LiveDataReactiveStreams.fromPublisher(
                repository.getSearchResults(keywords)
                        .subscribeOn(Schedulers.io())
                        .map(SearchResponse::getItems)
                        .map(RxResult::success)
                        .onErrorReturn(RxResult::error)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)
                        .cache()
        );
    };

   public LiveData<RxResult<List<Item>>> getSearchResult(){
        return results;
    }
}
