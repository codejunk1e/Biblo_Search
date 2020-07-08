package com.robin.biblosearch.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.robin.biblosearch.datasource.Repository;
import com.robin.biblosearch.models.VolumeInfo;

import java.util.List;

public class SearchActivityViewModel extends ViewModel {
    Repository repository = new Repository();

    public LiveData<List<VolumeInfo>> getSearchResult(String keywords){
        return repository.getSearchResults(keywords);
    }
}
