package com.zasa.superduper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zasa.superduper.Adapters.Question_Adapter;
import com.zasa.superduper.Models.Question_Model;
import com.zasa.superduper.Models.Scoring_Adapter;
import com.zasa.superduper.R;
import com.zasa.superduper.RecyclerItemClickListener;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    RecyclerView questionsRv;
    ArrayList<Question_Model> questionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionsRv = findViewById(R.id.rv_question);

        getQuestionList();
    }

    private void getQuestionList() {
        questionList.add(new Question_Model("Pasta","text"));
        questionList.add(new Question_Model("Spices","qr"));
        questionList.add(new Question_Model("Salt","picture"));
        questionList.add(new Question_Model("Sauces and Ketchup","text"));
        questionList.add(new Question_Model("Snacks","qr"));
        questionList.add(new Question_Model("Kurkure","picture"));
        questionList.add(new Question_Model("Tea Table","text"));
        questionList.add(new Question_Model("Honey and Spreads","qr"));
        questionList.add(new Question_Model("Namkeen and Snacks","picture"));
        questionList.add(new Question_Model("Tea Table","text"));
        questionList.add(new Question_Model("Salt","qr"));
        questionList.add(new Question_Model("Pasta","picture"));
        questionList.add(new Question_Model("Pasta","qr"));

        Question_Adapter question_adapter = new Question_Adapter(questionList, this);
        questionsRv.setAdapter(question_adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        questionsRv.setLayoutManager(layoutManager);

//        questionsRv.addOnItemTouchListener(new RecyclerItemClickListener(this, questionsRv, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                switch (position){
//                    case 0:
//                        Intent intent = new Intent(QuestionActivity.this, CaptureImageActivity.class);
//                        startActivity(intent);
//                        break;
//                    case 1:
//                        Intent i = new Intent(QuestionActivity.this, ScannerViewActivity.class);
//                        startActivity(i);
//                        break;
//
//                    case 2:
//                        Intent inte = new Intent(QuestionActivity.this, NumericActivity.class);
//                        startActivity(inte);
//                        break;
//                }
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));
    }
}