package com.android.vishal.demoproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.vishal.demoproject.R;
import com.android.vishal.demoproject.model.CustomerModel;
import com.android.vishal.demoproject.util.Constants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class CustomerAdapter extends ArrayAdapter<CustomerModel> {

    private ArrayList<CustomerModel> mList;
    private  Context mContext;


    public CustomerAdapter(@NonNull  ArrayList<CustomerModel> data, Context context) {
        super(context, R.layout.single_list_row_item, data);
        this.mList = data;
        this.mContext=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Checking for Null View
        View view = convertView;
        if (view == null) {
            LayoutInflater tempInflator;
            tempInflator = LayoutInflater.from(mContext);
            view = tempInflator.inflate(R.layout.single_list_row_item, null);
        }

        // Get the data item for this position
        CustomerModel userData = getItem(position);

        if(userData != null){

            TextView userNameTv = view.findViewById(R.id.userNameTv);
            TextView userGenderTv = view.findViewById(R.id.userGenderTv);
            TextView userAgeTv = view.findViewById(R.id.userAgeTv);
            TextView userStatusTv = view.findViewById(R.id.statusTv);
            ImageView userProfileIv = view.findViewById(R.id.userProfileIv);

            Glide.with(mContext).load(userData.getImg()).into(userProfileIv);
            userNameTv.setText(userData.getName());
            userGenderTv.setText(userData.getGender());
            userAgeTv.setText(String.valueOf(userData.getAge()) + " yr");
            userStatusTv.setText(userData.getStatus());

            if(userData.getStatus().equalsIgnoreCase(Constants.STATUS_ACTIVE)) userStatusTv.setTextColor(Color.parseColor("#00ff00"));

            if(userData.getStatus().equalsIgnoreCase(Constants.STATUS_ONBOARDED)) userStatusTv.setTextColor(Color.parseColor("#ffc736"));

            if(userData.getStatus().equalsIgnoreCase(Constants.STATUS_LEFT)) userStatusTv.setTextColor(Color.parseColor("#ff2233"));
        }


        return view;
    }


}
