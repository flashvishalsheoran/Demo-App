package com.android.vishal.demoproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.vishal.demoproject.R;
import com.android.vishal.demoproject.adapter.CustomerAdapter;
import com.android.vishal.demoproject.api.RetrofitApiInterface;
import com.android.vishal.demoproject.comparator.DateComparator;
import com.android.vishal.demoproject.databinding.ActivityMainBinding;
import com.android.vishal.demoproject.model.CustomerModel;
import com.android.vishal.demoproject.model.ListResponse;
import com.android.vishal.demoproject.util.Constants;
import com.android.vishal.demoproject.util.RetrofitBuilder;
import com.android.vishal.demoproject.viewmodel.HomeScreenViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBindings;
    private final String TAG = "Home Activity";

    private ArrayList<CustomerModel> mList;
    private Retrofit retrofitObj;
    private RetrofitApiInterface apiInterface;
    private CustomerAdapter mAdapter;
    private ProgressDialog mDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindings = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBindings.getRoot());

        mDailog = new ProgressDialog(this);
        mList = new ArrayList<>();
        getCustomerData();

    }


    //Function to get data from the Server
    private void getCustomerData() {

        showDailog();

        retrofitObj = RetrofitBuilder.getRetrofitClient();
        apiInterface = retrofitObj.create(RetrofitApiInterface.class);
        Call<ListResponse> call = apiInterface.getAllCustomerData();

        call.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {

                ListResponse customerResponse = response.body();
                mList.addAll(customerResponse.getList());
                setAdapter();
                dismissDailog();
                Log.e(TAG, "onResponse: Fetched ListResponse from the Server...");

            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                dismissDailog();
                Log.e(TAG, "onFailure: Getting Error while fetching data from the server... Error is : "+t.getMessage() + " , " +t.getCause());
                Toast.makeText(MainActivity.this, "Error fetching data from the server...", Toast.LENGTH_SHORT).show();
            }
        });


    }


    //Function to set Data into the ListView
    private void setAdapter() {

        if (mList.size() > 0) {

            ArrayList<CustomerModel> activeList = new ArrayList<>();
            ArrayList<CustomerModel> onBoardedList = new ArrayList<>();
            ArrayList<CustomerModel> leftList = new ArrayList<>();

            for (CustomerModel model : mList) {

                if (model.getStatus().equalsIgnoreCase(Constants.STATUS_ACTIVE)) activeList.add(model);
                if (model.getStatus().equalsIgnoreCase(Constants.STATUS_ONBOARDED)) onBoardedList.add(model);
                if (model.getStatus().equalsIgnoreCase(Constants.STATUS_LEFT)) leftList.add(model);

            }

            //Sorting Date Wise
            Collections.sort(activeList, new DateComparator());
            Collections.sort(onBoardedList, new DateComparator());
            Collections.sort(leftList, new DateComparator());


            //Again Adding in the List
            mList.clear();
            mList.addAll(activeList);
            mList.addAll(onBoardedList);
            mList.addAll(leftList);

            mAdapter = new CustomerAdapter(mList, MainActivity.this);
            mBindings.mListView.setAdapter(mAdapter);

        }
        else{
            Toast.makeText(this, "There is no data to display...", Toast.LENGTH_SHORT).show();
        }

    }


    //Function to show the progress Dailog
    private void showDailog(){

        if(mDailog != null && mDailog.isShowing()){
            mDailog.dismiss();
        }

        mDailog.setCancelable(false);
        mDailog.setMessage("Loading Data...");
        mDailog.show();

    }


    //Function to Dismiss the progress Dailog
    private void dismissDailog(){

        if(mDailog != null && !mDailog.isShowing()){
            return;
        }

      mDailog.dismiss();

    }

}
