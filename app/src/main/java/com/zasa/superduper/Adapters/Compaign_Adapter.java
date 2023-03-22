package com.zasa.superduper.Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zasa.superduper.activities.Survey_Activity;
import com.zasa.superduper.Models.Compaign_Model;
import com.zasa.superduper.R;

import java.util.ArrayList;

public class Compaign_Adapter extends RecyclerView.Adapter<Compaign_Adapter.viewHolder> {
    ArrayList<Compaign_Model> campaignList;
    Context context;
    SQLiteDatabase sqLiteDatabase;

    public Compaign_Adapter(ArrayList<Compaign_Model> moduleList, Context context, SQLiteDatabase sqLiteDatabase) {
        this.campaignList = moduleList;
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public Compaign_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.daily_operation_module_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Compaign_Adapter.viewHolder holder, int position) {
        Compaign_Model model = campaignList.get(position);

//        holder.iv_module.setImageResource(model.getModuledPic());
        holder.txt_module.setText(model.getCompaign_name());

//        if (holder.rb_compaign.isChecked()){
//
//        }

        Picasso.get().load(model.getCompaign_image()).placeholder(R.drawable.bottles)
                .into(holder.iv_module);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Survey_Activity.class);
                intent.putExtra("compaign_name",model.getCompaign_name());
                intent.putExtra("compaign_id",model.getCompaign_id());
                Toast.makeText(context, model.getCompaign_name(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return campaignList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView iv_module;
        TextView txt_module;
        RadioButton rb_compaign;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            iv_module = itemView.findViewById(R.id.iv_single_mod);
            txt_module = itemView.findViewById(R.id.tv_single_mod);
            rb_compaign = itemView.findViewById(R.id.rb_compaign);
        }
    }
}
