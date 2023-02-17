package com.zasa.superduper.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.R;
import com.zasa.superduper.Scoring_Criteria_Model;

import java.util.ArrayList;
import java.util.List;

public class Scoring_Adapter extends RecyclerView.Adapter<Scoring_Adapter.viewHolder> {
    ArrayList<Scoring_Criteria_Model> mList;
    Context context;

    public Scoring_Adapter(List<Scoring_Criteria_Model> mList, Context context) {
        this.mList = (ArrayList<Scoring_Criteria_Model>) mList;
        this.context = context;
    }

    @NonNull
    @Override
    public Scoring_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.monthly_scoring_rcv_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Scoring_Adapter.viewHolder holder, int position) {
        Scoring_Criteria_Model model = mList.get(position);

        holder.scoring_image.setImageResource(model.getScoring_pic());
        holder.textView.setText(model.getItemText());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView scoring_image;
        TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            scoring_image = itemView.findViewById(R.id.scoring_iv);
            textView = itemView.findViewById(R.id.scoring_citeria_name);
        }
    }
}
