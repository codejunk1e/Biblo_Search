package com.robin.biblosearch.datasource.local;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.robin.biblosearch.models.Item;
import com.robin.biblosearch.models.VolumeInfo;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM recent_book_table ORDER BY updated_at DESC")
    LiveData<List<VolumeInfo>> loadAllBookss();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBook(VolumeInfo item);

    @Delete
    void deleteBook(VolumeInfo item);

    @Query("SELECT * FROM recent_book_table WHERE favourite = '1' ORDER BY updated_at DESC ")
    LiveData<List<VolumeInfo>> getAllFavourites();

    @Query("SELECT * FROM recent_book_table WHERE favourite = '1' ORDER BY updated_at DESC ")
    List<VolumeInfo> getAllFavouritesForWidget();
}
