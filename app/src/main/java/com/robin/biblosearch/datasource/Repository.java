package com.robin.biblosearch.datasource;

import androidx.lifecycle.MutableLiveData;

import com.robin.biblosearch.datasource.remote.ApiEndpoints;
import com.robin.biblosearch.datasource.remote.ApiService;
import com.robin.biblosearch.models.VolumeInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    MutableLiveData<List<VolumeInfo>> data = new MutableLiveData<>();
    ApiEndpoints apiEndpoints = ApiService.buildService(ApiEndpoints.class);


    public MutableLiveData<List<VolumeInfo>> getSearchResults(String keywords){
        apiEndpoints.searchBooks( keywords).enqueue(
                new Callback<List<VolumeInfo>>() {
                    @Override
                    public void onResponse(Call<List<VolumeInfo>> call, Response<List<VolumeInfo>> response) {
                        data.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<VolumeInfo>> call, Throwable t) {
                        data.postValue(null);
                    }
                }
        );
        return data;
    }
}
