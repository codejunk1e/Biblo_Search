package com.robin.biblosearch.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.robin.biblosearch.datasource.Repository;
import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.VolumeInfo;

public class BookDetailsViewModel extends AndroidViewModel {
    Repository repository;

    public BookDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insertIntoDatabase(VolumeInfo item){
        repository.insertIntoDatabase(item);
    }
    public void deleteFromDatabase(VolumeInfo item){
        repository.deleteFromDatabase(item);
    }
}
