package com.zasa.superduper.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.zasa.superduper.R;

public class ProfileActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //setSupportActionBar(toolbar);

        context = ProfileActivity.this;



        // showUserImg();


    }


}