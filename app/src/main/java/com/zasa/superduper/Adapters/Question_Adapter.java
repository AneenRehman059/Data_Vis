package com.zasa.superduper.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.zasa.superduper.Models.Question_Model;
import com.zasa.superduper.R;
import com.zasa.superduper.activities.CaptureImageActivity;
import com.zasa.superduper.activities.ScannerViewActivity;

import java.util.ArrayList;

public class Question_Adapter extends RecyclerView.Adapter {
    ArrayList<Question_Model> questionList;
    Context context;

    public Question_Adapter(ArrayList<Question_Model> questionList, Context context) {
        this.questionList = questionList;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {

        if (questionList.get(position).getType().equalsIgnoreCase("text"))
            return 1;
        else if (questionList.get(position).getType().equalsIgnoreCase("picture"))
            return 2;
        else if (questionList.get(position).getType().equalsIgnoreCase("qr"))
            return 3;
        else
            questionList.get(position).getType().equalsIgnoreCase("");

        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 1) {
            view = layoutInflater.inflate(R.layout.textitem, parent, false);
            return new ViewHolderText(view);
        } else if (viewType == 2) {
            view = layoutInflater.inflate(R.layout.image_item, parent, false);
            return new PicViewHolder(view);
        } else if (viewType == 3) {
            view = layoutInflater.inflate(R.layout.qr_item, parent, false);
            return new ViewHolderQr(view);
        } else
            view = layoutInflater.inflate(R.layout.each_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Question_Model model = questionList.get(position);

//        holder.tv_question.setText(model.getQuestion_name());

        if (questionList.get(position).getType().equalsIgnoreCase("text")) {
            ViewHolderText viewHolderText = (ViewHolderText) holder;
            viewHolderText.txt_cate.setText(model.getQuestion_name());
        } else if (questionList.get(position).getType().equalsIgnoreCase("picture")) {
            PicViewHolder viewHolderPic = (PicViewHolder) holder;
            viewHolderPic.txt_categ.setText(model.getQuestion_name());

            viewHolderPic.btn_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CaptureImageActivity.class);
                    context.startActivity(intent);
                }
            });

        } else if (questionList.get(position).getType().equalsIgnoreCase("qr")) {
            ViewHolderQr viewHolderQr = (ViewHolderQr) holder;
            viewHolderQr.txt_catego.setText(model.getQuestion_name());
            viewHolderQr.btn_qr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ScannerViewActivity.class);
                    context.startActivity(intent);
                }
            });
        } else {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tv_question.setText(model.getQuestion_name());
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class ViewHolderText extends RecyclerView.ViewHolder {
        EditText ed_number;
        TextView txt_cate;

        public ViewHolderText(@NonNull View itemView) {
            super(itemView);

            ed_number = itemView.findViewById(R.id.edt_enter_number);
            txt_cate = itemView.findViewById(R.id.txt_cate);
        }
    }

    class PicViewHolder extends RecyclerView.ViewHolder {

        TextView txt_categ;
        MaterialButton btn_iv;

        public PicViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_iv = itemView.findViewById(R.id.btn_capture_image);
            txt_categ = itemView.findViewById(R.id.txt_categ);
        }
    }

    class ViewHolderQr extends RecyclerView.ViewHolder {
        Button btn_qr;
        TextView txt_catego;

        public ViewHolderQr(@NonNull View itemView) {
            super(itemView);

            btn_qr = itemView.findViewById(R.id.btn_qr);
            txt_catego = itemView.findViewById(R.id.txt_catego);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_question;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_question = itemView.findViewById(R.id.txt_categories);
        }
    }
}
