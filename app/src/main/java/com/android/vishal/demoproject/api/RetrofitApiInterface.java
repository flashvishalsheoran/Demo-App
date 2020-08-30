package com.android.vishal.demoproject.api;

import com.android.vishal.demoproject.model.ListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiInterface {

    @GET("listAll")
    Call<ListResponse> getAllCustomerData();

}
