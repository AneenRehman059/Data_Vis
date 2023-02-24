package com.zasa.superduper.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.zasa.superduper.activities.HomeActivity;
import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPrefManager sharedPrefManager;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_DATE = "date";
    private static final String PREF_DATE = "prf_date";

    TextView tv_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //off dark mode

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv_splash = findViewById(R.id.splash_Logo);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fly_in_from_left);
        tv_splash.startAnimation(animation);

        sharedPrefManager = new SharedPrefManager(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        /////// this get date form operation activity //////
        String sharedpref_date = sharedPreferences.getString(KEY_DATE, null);
        String date = sharedPreferences.getString(PREF_DATE, null);

        ////// this again send sahred pref save date to operation activity //////
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_DATE, sharedpref_date);
        editor.apply();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);

                if (hasLoggedIn){
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