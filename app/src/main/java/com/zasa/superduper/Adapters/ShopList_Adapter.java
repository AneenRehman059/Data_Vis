package com.zasa.superduper.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.zasa.superduper.activities.Compaign_Activity;
import com.zasa.superduper.Models.ShopList_Model;
import com.zasa.superduper.R;
import com.zasa.superduper.activities.ShopLocationActivity;
import com.zasa.superduper.helpers.PreferencesData;

import java.util.ArrayList;
import java.util.Locale;


public class ShopList_Adapter extends RecyclerView.Adapter<ShopList_Adapter.viewHolder> {
    ArrayList<ShopList_Model> shopList;
    Context context;
    SQLiteDatabase sqLiteDatabase;
    String latitude,longitude;

    public ShopList_Adapter(ArrayList<ShopList_Model> shopList, Context context, SQLiteDatabase sqLiteDatabase) {
        this.shopList = shopList;
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public ShopList_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_list_rcv_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopList_Adapter.viewHolder holder, int position) {
        ShopList_Model model = shopList.get(position);

         latitude  = PreferencesData.getString(context, "latitude","");
         longitude  = PreferencesData.getString(context, "longitude","");
        holder.txt_shop_name.setText(model.getShop_name());
        holder.txt_shop_description.setText(model.getShop_description());

        holder.iv_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, ShopLocationActivity.class);
//                context.startActivity(intent);

//                String uri = String.format(Locale.ENGLISH, "geo:%f,%f"," latitude", longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 31.475351 74.2709009"));
                context.startActivity(intent);
            }
        });

        holder.iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:+923334548890"));
                context.startActivity(callIntent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewGroup viewGroup = view.findViewById(android.R.id.content);

                ImageButton close;
                Button btn_open_shop;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.shop_status_dialog,viewGroup,false);
                builder.setCancelable(false);
                builder.setView(view1);

                close = view1.findViewById(R.id.btn_dialog);
                btn_open_shop = view1.findViewById(R.id.btnOpenShop);

                final AlertDialog alertDialog = builder.create();

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                btn_open_shop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, Compaign_Activity.class);
                        Compaign_Activity.storename = holder.txt_shop_name.getText().toString();
                        intent.putExtra("shop_id",model.getShop_id());
                        context.startActivity(intent);
                    }
                });

                alertDialog.show();

//                Intent intent = new Intent(context, Compaign_Activity.class);
//                Compaign_Activity.storename = holder.txt_shop_name.getText().toString();
//                intent.putExtra("shop_id",model.getShop_id());
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_shop_name,txt_shop_description;
        ImageView iv_call,iv_map;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txt_shop_name = itemView.findViewById(R.id.store_name);
            txt_shop_description = itemView.findViewById(R.id.shop_description);
            iv_call = itemView.findViewById(R.id.iv_call);
            iv_map = itemView.findViewById(R.id.iv_map);
        }
    }
}