package com.zasa.superduper.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.After_Checkout;
import com.zasa.superduper.Models.Daily_OperationModule_Model;
import com.zasa.superduper.Models.Scoring_Adapter;
import com.zasa.superduper.R;

import java.util.ArrayList;

public class Daily_OperationModule_Adapter extends RecyclerView.Adapter<Daily_OperationModule_Adapter.viewHolder> {
    ArrayList<Daily_OperationModule_Model> moduleList;
    Context context;

    public Daily_OperationModule_Adapter(ArrayList<Daily_OperationModule_Model> moduleList, Context context) {
        this.moduleList = moduleList;
        this.context = context;
    }

    @NonNull
    @Override
    public Daily_OperationModule_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.daily_operation_module_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Daily_OperationModule_Adapter.viewHolder holder, int position) {
        Daily_OperationModule_Model model = moduleList.get(position);

        holder.iv_module.setImageResource(model.getModuledPic());
        holder.txt_module.setText(model.getModule_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, After_Checkout.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView iv_module;
        TextView txt_module;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            iv_module = itemView.findViewById(R.id.iv_single_mod);
            txt_module = itemView.findViewById(R.id.tv_single_mod);
        }
    }
}
