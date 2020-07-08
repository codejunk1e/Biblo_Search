package com.robin.biblosearch.datasource.remote;

import com.robin.biblosearch.models.VolumeInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoints {

    @GET("volumes")
    public Call<List<VolumeInfo>> searchBooks(@Query("q") String keywords);

}