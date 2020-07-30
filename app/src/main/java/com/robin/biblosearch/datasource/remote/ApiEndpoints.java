package com.robin.biblosearch.datasource.remote;

import com.robin.biblosearch.models.SearchResponse;
import com.robin.biblosearch.utils.RxResult;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoints {

    @GET("volumes")
    Observable<SearchResponse>  searchBooks(@Query("q") String keywords);

}