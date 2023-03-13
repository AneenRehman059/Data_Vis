package com.zasa.superduper.Splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import com.zasa.superduper.activities.HomeActivity;
import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.SharedPrefManager;
import com.zasa.superduper.helpers.PreferencesData;

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

        String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA};
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112 );
        }
        else {
            //do here
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);
                String isLogged = PreferencesData.getString(SplashActivity.this,"isLogged","no");
                if(isLogged.equalsIgnoreCase("yes")){
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

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SplashActivity.this, permissions, 112 );
            }
    }
}