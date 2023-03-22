package com.zasa.superduper.Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.Models.Surveys_Model;
import com.zasa.superduper.MyCallBack;
import com.zasa.superduper.R;
import com.zasa.superduper.activities.QuestionActivity;

import java.util.ArrayList;
import java.util.List;

public class Survey_Adapter extends RecyclerView.Adapter<Survey_Adapter.viewHolder> {
    ArrayList<Surveys_Model> catList;
    private List<String> list = new ArrayList<>();
    private MyCallBack myCallBack;
    Context context;
    SQLiteDatabase sqLiteDatabase;

    public Survey_Adapter(ArrayList<Surveys_Model> catList, Context context, MyCallBack myCallBack, SQLiteDatabase sqLiteDatabase) {
        this.catList = catList;
        this.context = context;
        this.myCallBack = myCallBack;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public Survey_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.each_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Survey_Adapter.viewHolder holder, int position) {
        Surveys_Model model = catList.get(position);

        holder.category_name.setText(model.getSurvey_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("category_id",model.getSurvey_id());
                intent.putExtra("category_name",model.getSurvey_name());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView category_name;
        RelativeLayout expandableLayot;
        RecyclerView nestedRecyclerView;
        LinearLayout linearLayout;
        ImageView mArrowImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            category_name = itemView.findViewById(R.id.txt_categories);
            linearLayout = itemView.findViewById(R.id.linear_layout);

        }
    }
}
