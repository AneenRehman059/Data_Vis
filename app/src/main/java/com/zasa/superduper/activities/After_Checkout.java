package com.zasa.superduper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.zasa.superduper.Adapters.Survey_Adapter;
import com.zasa.superduper.Models.Surveys_Model;
import com.zasa.superduper.MyCallBack;
import com.zasa.superduper.R;

import java.util.ArrayList;

public class After_Checkout extends AppCompatActivity implements MyCallBack {
    RecyclerView rcv_categories;
    ArrayList<Surveys_Model> catList = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_checkout);

        rcv_categories = findViewById(R.id.rv_categories);

        getCatList();
    }

    private void getCatList() {

//        catList.add(new Surveys_Model("Rose Patel"));
//        catList.add(new Surveys_Model("Tulip"));
//        catList.add(new Surveys_Model("Maxob"));
//        catList.add(new Surveys_Model("Competitors"));
//        catList.add(new Surveys_Model("Competitors"));
//        catList.add(new Surveys_Model("Competitors"));
//        catList.add(new Surveys_Model("Rose Patel"));

        Survey_Adapter adapter = new Survey_Adapter(catList, this,this,sqLiteDatabase);
        rcv_categories.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv_categories.setLayoutManager(layoutManager);
    }

    @Override
    public void notify(Object boj, String type) {

    }
}