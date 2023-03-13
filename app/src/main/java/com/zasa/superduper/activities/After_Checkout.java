package com.zasa.superduper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zasa.superduper.Adapters.Categories_Adapter;
import com.zasa.superduper.Models.Category_Model;
import com.zasa.superduper.MyCallBack;
import com.zasa.superduper.R;

import java.util.ArrayList;
import java.util.List;

public class After_Checkout extends AppCompatActivity implements MyCallBack {
    RecyclerView rcv_categories;
    ArrayList<Category_Model> catList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_checkout);

        rcv_categories = findViewById(R.id.rv_categories);

        getCatList();
    }

    private void getCatList() {

        catList.add(new Category_Model("Rose Patel"));
        catList.add(new Category_Model("Tulip"));
        catList.add(new Category_Model("Maxob"));
        catList.add(new Category_Model("Competitors"));
        catList.add(new Category_Model("Competitors"));
        catList.add(new Category_Model("Competitors"));
        catList.add(new Category_Model("Rose Patel"));

        Categories_Adapter adapter = new Categories_Adapter(catList, this,this);
        rcv_categories.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv_categories.setLayoutManager(layoutManager);
    }

    @Override
    public void notify(Object boj, String type) {

    }
}