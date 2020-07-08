package com.robin.biblosearch.datasource.remote;

import com.robin.biblosearch.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoints {

    @GET("volumes")

    public Call<SearchResponse> searchBooks(@Query("q") String keywords);

}