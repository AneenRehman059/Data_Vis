package com.zasa.superduper.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zasa.superduper.Adapters.Compaign_Adapter;
import com.zasa.superduper.ApiManager.CompaignAppManager;
import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.Models.Compaign_Module_Model;
import com.zasa.superduper.MyCallBack;
import com.zasa.superduper.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Compaign_Activity extends AppCompatActivity implements MyCallBack {
    RecyclerView rv_daily_operation_mod;
    int count = 0;
    String apiToken;
    public static String storename;
    ArrayList<Compaign_Module_Model> moduleList = new ArrayList<>();
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    boolean timerStarted = false;
    boolean clicked = false;
    TextView timerText;
    Button stopStartButton,checkOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        TextView tvStorename = findViewById(R.id.tvStoreName);
        timerText = findViewById(R.id.tvTimer);
        stopStartButton = findViewById(R.id.startStopButton);
        checkOutButton = findViewById(R.id.checkOutButton);

        tvStorename.setText(storename);
        SharedPreferences sharedPreferenc = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferenc.edit();
        apiToken = String.valueOf(editor.putString("token_id",""));

        rv_daily_operation_mod = findViewById(R.id.rv_operation_module_items);
        ImageView btn_back = findViewById(R.id.btn_back);
        timer = new Timer();

        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true;
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!clicked){
                    Toast.makeText(Compaign_Activity.this, "Please Check Out", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    onBackPressed();   
                }
            }
        });
        setModuleItem();
    }

    private void setModuleItem() {


        CompaignAppManager checkOutAppManager = new CompaignAppManager(this,this);
        JSONObject compaign_params = new JSONObject();
        try {
            compaign_params.put("token",apiToken);
            compaign_params.put("shop_id",getIntent().getExtras().getString("shop_id"));

            checkOutAppManager.postCompaignLogin(getIntent().getExtras().getString("shop_id"));
//            Toast.makeText(this, apiToken, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

//        stringAppManager checkOutAppManager = new stringAppManager(this,this);
//            checkOutAppManager.postLogin();


    }

    @Override
    public void notify(Object obj, String type) {
        if (type.equalsIgnoreCase("questions")){
            moduleList = (ArrayList<Compaign_Module_Model>)obj;

            Compaign_Adapter moduleAdapter = new Compaign_Adapter(moduleList, this);
            rv_daily_operation_mod.setAdapter(moduleAdapter);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            rv_daily_operation_mod.setLayoutManager(gridLayoutManager);
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void resetTapped(View view)
    {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("Are you sure you want to reset the timer?");
        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(timerTask != null)
                {
                    timerTask.cancel();
                    setButtonUI("START", R.color.green);
                    time = 0.0;
                    timerStarted = false;
                    timerText.setText(formatTime(0,0,0));

                }
            }
        });

        resetAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                //do nothing
            }
        });

        resetAlert.show();

    }

    public void startStopTapped(View view)
    {
        if(timerStarted == false)
        {
            timerStarted = true;
            setButtonUI("STOP", R.color.red);

            startTimer();
        }
        else
        {
            timerStarted = false;
            setButtonUI("START", R.color.green);

            timerTask.cancel();
        }
    }

    private void setButtonUI(String start, int color)
    {
        checkOutButton.setVisibility(View.VISIBLE);
        stopStartButton.setText(start);
        stopStartButton.setTextColor(ContextCompat.getColor(this, color));
    }

    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }


    private String getTimerText()
    {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d",hours) + " : " + String.format("%02d",minutes) + " : " + String.format("%02d",seconds);
    }

    @Override
    public void onBackPressed() {

        if (!clicked){
            Toast.makeText(Compaign_Activity.this, "Please Check Out", Toast.LENGTH_SHORT).show();
        }
        else
        {
            super.onBackPressed();
            finish();
        }
    }
}