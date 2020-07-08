package com.robin.biblosearch.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.robin.biblosearch.datasource.Repository;
import com.robin.biblosearch.models.VolumeInfo;

import java.util.List;

public class RecentsFavouritesViewModel extends AndroidViewModel {
    Repository repository;

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
}
