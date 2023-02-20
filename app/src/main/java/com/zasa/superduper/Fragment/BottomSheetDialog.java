package com.zasa.superduper.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.zasa.superduper.Adapters.Operation_Adapter;
import com.zasa.superduper.Adapters.ShopList_Adapter;
import com.zasa.superduper.Models.ShopList_Model;
import com.zasa.superduper.R;

import java.util.ArrayList;


public class BottomSheetDialog extends BottomSheetDialogFragment {
    RecyclerView recyclerView_shopList;
    ArrayList<ShopList_Model> shopList = new ArrayList<>();

    public BottomSheetDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);

        recyclerView_shopList = view.findViewById(R.id.rv_shopList);

        getShopList();

        return view;
    }

    private void getShopList() {
        shopList.add(new ShopList_Model("Bismillah General Store","Channel Name","Nearest Landmark","0321-3254786"));
        shopList.add(new ShopList_Model("Bismillah General Store","Channel Name","Nearest Landmark","0321-3254786"));
        shopList.add(new ShopList_Model("Bismillah General Store","Channel Name","Nearest Landmark","0321-3254786"));
        shopList.add(new ShopList_Model("Bismillah General Store","Channel Name","Nearest Landmark","0321-3254786"));
        shopList.add(new ShopList_Model("Bismillah General Store","Channel Name","Nearest Landmark","0321-3254786"));
        shopList.add(new ShopList_Model("Bismillah General Store","Channel Name","Nearest Landmark","0321-3254786"));
        shopList.add(new ShopList_Model("Bismillah General Store","Channel Name","Nearest Landmark","0321-3254786"));

        ShopList_Adapter shopList_adapter = new ShopList_Adapter(shopList,getContext());
        recyclerView_shopList.setAdapter(shopList_adapter);



        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView_shopList.setLayoutManager(layoutManager);

        shopList_adapter.notifyDataSetChanged();

    }
}