package com.zasa.superduper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.zasa.superduper.Adapters.Operation_Adapter;
import com.zasa.superduper.Models.Opeartion_Model;
import com.zasa.superduper.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class OperationActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_DATE = "date";
    private static final String PREF_DATE = "prf_date";
    ArrayList<Opeartion_Model> operationList = new ArrayList<>();
    RecyclerView operation_rv;

    private int layout = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);

        /////// get current date and shared to splash activity and save in shared prefrence ////////
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String date = sharedPreferences.getString(KEY_DATE, null);
        String current_date = getCurrentDate();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DATE, current_date);
        editor.apply();

        operation_rv = findViewById(R.id.rv_opeartion);

        //////// this get save date in splash activty and compare with current date ///////
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String sharedpref_date = sharedPreferences.getString(PREF_DATE, null);

        if (current_date.equals(sharedpref_date)){
//            Intent intent = new Intent(OperationActivity.this, HomeActivity.class);
//            startActivity(intent);
//            finish();

            getOperationList();
        }
       else {
            Intent intent = new Intent(OperationActivity.this, StartDay_Activity.class);
            startActivity(intent);
            finish();
        }
    }

    private void getOperationList() {
        operationList.add(new Opeartion_Model("Gulberg","59 A Rafaeam Society Malir Halt","Line 2 address","Last Purchase: 2 week ago"));
        operationList.add(new Opeartion_Model("Johar Town ","59 A Rafaeam Society Malir Halt","Line 2 address","Last Purchase: 2 week ago"));
        operationList.add(new Opeartion_Model("Defense Phase I","59 A Rafaeam Society Malir Halt","Line 2 address","Last Purchase: 2 week ago"));
        operationList.add(new Opeartion_Model("Mughal Pura","59 A Rafaeam Society Malir Halt","Line 2 address","Last Purchase: 2 week ago"));
        operationList.add(new Opeartion_Model("BaghbanPura","59 A Rafaeam Society Malir Halt","Line 2 address","Last Purchase: 2 week ago"));
        operationList.add(new Opeartion_Model("Paragon City","59 A Rafaeam Society Malir Halt","Line 2 address","Last Purchase: 2 week ago"));
        operationList.add(new Opeartion_Model("Lahore Cant","59 A Rafaeam Society Malir Halt","Line 2 address","Last Purchase: 2 week ago"));
        operationList.add(new Opeartion_Model("Model Town","59 A Rafaeam Society Malir Halt","Line 2 address","Last Purchase: 2 week ago"));

        Operation_Adapter operation_adapter = new Operation_Adapter(operationList,this);
        operation_rv.setAdapter(operation_adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        operation_rv.setLayoutManager(layoutManager);
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}