package com.zasa.superduper.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.zasa.superduper.HomeActivity;
import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPrefManager sharedPrefManager;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_DATE = "date";
    private static final String PREF_DATE = "prf_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //off dark mode

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPrefManager = new SharedPrefManager(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        /////// this get date form operation activity //////
        String sharedpref_date = sharedPreferences.getString(KEY_DATE, null);
        String date = sharedPreferences.getString(PREF_DATE, null);

        ////// this again send sahred pref save date to operation activity //////
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_DATE, sharedpref_date);
        editor.apply();

//        if (date != null) {
//            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
//            startActivity(intent);
//        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (sharedPrefManager.isLoggedIn()){
                    Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 3000);

    }
}