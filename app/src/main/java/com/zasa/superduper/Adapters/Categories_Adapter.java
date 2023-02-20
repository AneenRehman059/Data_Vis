package com.zasa.superduper.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.Models.Category_Model;
import com.zasa.superduper.R;

import java.util.ArrayList;
import java.util.List;

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.viewHolder> {
    ArrayList<Category_Model> catList;
    private List<String> list = new ArrayList<>();
    Context context;

    public Categories_Adapter(ArrayList<Category_Model> catList, Context context) {
        this.catList = catList;
        this.context = context;
    }

    @NonNull
    @Override
    public Categories_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.each_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Categories_Adapter.viewHolder holder, int position) {
        Category_Model model = catList.get(position);

        holder.category_name.setText(model.getCategory_name());

        boolean isExpandable = model.isExpandable();
        holder.expandableLayot.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if (isExpandable){
            holder.mArrowImage.setImageResource(R.drawable.arrow_up_24);
        }else{
            holder.mArrowImage.setImageResource(R.drawable.arrow_down_24);
        }

        Nested_Cat_Adapter adapter =new Nested_Cat_Adapter(list);
        holder.nestedRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.nestedRecyclerView.setHasFixedSize(true);
        holder.nestedRecyclerView.setAdapter(adapter);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setExpandable(!model.isExpandable());
                list = model.getNestedList();
                notifyItemChanged(holder.getAdapterPosition());
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
            nestedRecyclerView = itemView.findViewById(R.id.child_rv);
            expandableLayot = itemView.findViewById(R.id.expandable_layout);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            mArrowImage = itemView.findViewById(R.id.iv_arrow_down);
        }
    }
}
