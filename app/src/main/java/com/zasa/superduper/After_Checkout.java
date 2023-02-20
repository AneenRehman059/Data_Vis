package com.zasa.superduper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zasa.superduper.Adapters.Categories_Adapter;
import com.zasa.superduper.Models.Category_Model;

import java.util.ArrayList;
import java.util.List;

public class After_Checkout extends AppCompatActivity {
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

        List<String> nestedList1 = new ArrayList<>();
        nestedList1.add("Jams and Honey");
        nestedList1.add("Pickles and Chutneys");
        nestedList1.add("Readymade Meals");
        nestedList1.add("Chyawanprash and Health Foods");
        nestedList1.add("Pasta and Soups");

        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add("Decorates");
        nestedList2.add("Tea Table");
        nestedList2.add("Bedsits");
        nestedList2.add("Certain");
        nestedList2.add("Namkeen and Snacks");
        nestedList2.add("Honey and Spreads");

        catList.add(new Category_Model("Rose Patel", nestedList1));
        catList.add(new Category_Model("Tulip",nestedList2));
        catList.add(new Category_Model("Maxob",nestedList1));
        catList.add(new Category_Model("Competitors",nestedList2));
        catList.add(new Category_Model("Competitors",nestedList1));
        catList.add(new Category_Model("Competitors",nestedList2));
        catList.add(new Category_Model("Rose Patel",nestedList1));

        Categories_Adapter adapter = new Categories_Adapter(catList, this);
        rcv_categories.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv_categories.setLayoutManager(layoutManager);
    }
}