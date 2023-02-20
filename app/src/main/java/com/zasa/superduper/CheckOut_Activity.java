package com.zasa.superduper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zasa.superduper.Adapters.Daily_OperationModule_Adapter;
import com.zasa.superduper.Models.Daily_OperationModule_Model;

import java.util.ArrayList;

public class CheckOut_Activity extends AppCompatActivity {
    RecyclerView rv_daily_operation_mod;
    int count = 0;
    ArrayList<Daily_OperationModule_Model> moduleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        rv_daily_operation_mod = findViewById(R.id.rv_operation_module_items);

        setModuleItem();
    }

    private void setModuleItem() {
        moduleList.add(new Daily_OperationModule_Model(R.drawable.bottles, "SKU Availability"));
        moduleList.add(new Daily_OperationModule_Model(R.drawable.planogram, "Planogram"));
        moduleList.add(new Daily_OperationModule_Model(R.drawable.gps, "GPS Check in"));
        moduleList.add(new Daily_OperationModule_Model(R.drawable.expiry, "Expiry Tracking"));
        moduleList.add(new Daily_OperationModule_Model(R.drawable.osa, "OSA (Competitor)"));
        moduleList.add(new Daily_OperationModule_Model(R.drawable.sku, "Secondary Gondola Display"));
        moduleList.add(new Daily_OperationModule_Model(R.drawable.planogram, "Outlet Facia Picture"));
        moduleList.add(new Daily_OperationModule_Model(R.drawable.gps, "POSM Tracking"));
        moduleList.add(new Daily_OperationModule_Model(R.drawable.strike, "Share to Shelf"));
        Daily_OperationModule_Adapter moduleAdapter = new Daily_OperationModule_Adapter(moduleList, this);
        rv_daily_operation_mod.setAdapter(moduleAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv_daily_operation_mod.setLayoutManager(gridLayoutManager);
    }
}