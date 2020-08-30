package com.android.vishal.demoproject.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.vishal.demoproject.api.RetrofitApiInterface;
import com.android.vishal.demoproject.model.CustomerModel;
import com.android.vishal.demoproject.model.ListResponse;
import com.android.vishal.demoproject.util.RetrofitBuilder;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeScreenViewModel extends AndroidViewModel {

    private final String TAG = "Home View Model";
    private MutableLiveData<ArrayList<CustomerModel>> customerLiveData;
    private Retrofit retrofitObj;
    private RetrofitApiInterface apiInterface;


    public HomeScreenViewModel(@NonNull Application application) {
        super(application);
    }


    //Function to return the LiveDat object of the Customer Data
    public LiveData<ArrayList<CustomerModel>> getCustomerData() {

        //if the list is null
        if (customerLiveData == null) {
            customerLiveData = new MutableLiveData<ArrayList<CustomerModel>>();
            //we will load it asynchronously from server in this method
            getDataFromServer();
        }

        //finally we will return the list
        return customerLiveData;
    }



    //Function to fetch data from the server
    public void getDataFromServer(){

        retrofitObj = RetrofitBuilder.getRetrofitClient();
        apiInterface = retrofitObj.create(RetrofitApiInterface.class);
        Call<ListResponse> call = apiInterface.getAllCustomerData();



    }


}
