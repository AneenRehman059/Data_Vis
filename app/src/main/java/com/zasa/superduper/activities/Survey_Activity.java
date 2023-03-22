package com.zasa.superduper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.zasa.superduper.Adapters.Survey_Adapter;
import com.zasa.superduper.ApiManager.SurveysAppManager;
import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.Models.Surveys_Model;
import com.zasa.superduper.MyCallBack;
import com.zasa.superduper.R;
import com.zasa.superduper.helpers.LocalDB;

import java.util.ArrayList;

public class Survey_Activity extends AppCompatActivity implements MyCallBack {
    RecyclerView rcv_categories;
    String api_Token;
    ArrayList<Surveys_Model> surveyList = new ArrayList<>();
    LocalDB localDB ;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_checkout);

        localDB = new LocalDB(Survey_Activity.this);

        SharedPreferences sharedPreferenc = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferenc.edit();
        api_Token = String.valueOf(editor.putString("token_id",""));
        rcv_categories = findViewById(R.id.rv_categories);
        TextView tvGroupTitle = findViewById(R.id.tvGroupTitle);
        tvGroupTitle.setText(getIntent().getExtras().getString("compaign_name"));
        getCatList();
    }

    private void getCatList() {
        SurveysAppManager surveysAppManager = new SurveysAppManager(this,this);
        surveysAppManager.getShopSurveys(getIntent().getExtras().getInt("compaign_id"));

    }

    @Override
    public void notify(Object obj, String type) {

        if (type.equalsIgnoreCase("surveys")){
            surveyList = (ArrayList<Surveys_Model>)obj;

            surveyList = localDB.getSurveys();

            Survey_Adapter adapter = new Survey_Adapter(surveyList, this,this,sqLiteDatabase);
            rcv_categories.setAdapter(adapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            rcv_categories.setLayoutManager(layoutManager);
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }


}