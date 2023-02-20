package com.zasa.superduper.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.R;

import java.util.List;

public class Nested_Cat_Adapter extends RecyclerView.Adapter<Nested_Cat_Adapter.viewHolder> {
    private List<String> mList;

    public Nested_Cat_Adapter(List<String> mList) {
        this.mList = mList;

    }

    @NonNull
    @Override
    public Nested_Cat_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_categories_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Nested_Cat_Adapter.viewHolder holder, int position) {
        holder.txt_subCat_name.setText(mList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Under Development", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_subCat_name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_subCat_name = itemView.findViewById(R.id.nestedItemTv);
        }
    }
}
