package com.zasa.superduper.activities;

import static com.zasa.superduper.helpers.LocalDB.ROUTES_TABLE;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import com.zasa.superduper.ApiManager.CompaignAppManager;
import com.zasa.superduper.ApiManager.QuestionsAppManager;
import com.zasa.superduper.ApiManager.RoutesAppManager;
import com.zasa.superduper.ApiManager.ShopAppManager;
import com.zasa.superduper.ApiManager.SurveysAppManager;
import com.zasa.superduper.CustomToastError;
import com.zasa.superduper.Home.HomeFragment;
import com.zasa.superduper.Models.Compaign_Model;
import com.zasa.superduper.Models.LoginModel;
import com.zasa.superduper.Models.Question_Model;
import com.zasa.superduper.Models.Routes_Model;
import com.zasa.superduper.Models.ShopList_Model;
import com.zasa.superduper.Models.Surveys_Model;
import com.zasa.superduper.MyCallBack;
import com.zasa.superduper.Profile.ProfileActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.helpers.LocalDB;
import com.zasa.superduper.helpers.PreferencesData;
import com.zasa.superduper.retrofit.ApiEndpoints;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MyCallBack {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    ArrayList<Fragment> fragments = new ArrayList<>();
    NavigationView navigationView;
    TextView headerUsername, abUserName, tv_headerName, tv_headerEmail;
    DrawerLayout drawer;
    View header;
    View view;
    Context context;
    CircleImageView userHeaderImage;
    int value;
    Handler handler = new Handler();
    ProgressBar progressBar;
    TextView text_id;
    TextView hdrTvEmail;
    ArrayList<Routes_Model> operationList = new ArrayList<>();
    LocalDB localDB;
    RequestQueue requestQueue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupDrawer();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        header = navigationView.getHeaderView(0);
        bottomNavigationView.setBackground(null);
        headerUsername = header.findViewById(R.id.tv_haader_name);
        tv_headerName = findViewById(R.id.tv_haaderNname);
        hdrTvEmail = header.findViewById(R.id.tvHdrEmail);
        tv_headerEmail = findViewById(R.id.tv_HdrEmail);

        progressBar = findViewById(R.id.progressbarId);
        text_id = findViewById(R.id.textid);
        view = findViewById(android.R.id.content);
        abUserName = findViewById(R.id.ab_username);

        // app main jahan bhy login response say koe value cheheyay to ya 2 line likhr kr get krain gay //
        LoginModel user = new LoginModel("", "");
        user.getLoggedUserFromPreference();

        tv_headerEmail.setText(user.getEmail());
        tv_headerName.setText(user.getName());
        headerUsername.setText(user.getName());
        hdrTvEmail.setText(user.getEmail());

        requestQueue = Volley.newRequestQueue(HomeActivity.this);
        localDB = new LocalDB(HomeActivity.this);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startProgress();
            }
        });
        thread.start();

        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        fragments.add(new HomeFragment());

//        fragments.add(new MoreFragment());
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragments.get(0)).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragments.get(0)).commit();
            } else if (item.getItemId() == R.id.operation) {

                if (isTimeAutomatic(HomeActivity.this)) {
                    startActivity(new Intent(HomeActivity.this, RoutesActivity.class));
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//                    finish();
                } else {
                    new CustomToastError().Show_Toast(HomeActivity.this, view, getString(R.string.error_msg));
//                    Toast.makeText(HomeActivity.this, "Please set auto time in datetime setting", Toast.LENGTH_SHORT).show();
                }
            } else if (item.getItemId() == R.id.setting) {
                startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//                finish();
            } else if (item.getItemId() == R.id.more) {
                startActivity(new Intent(HomeActivity.this, MoreActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//                finish();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragments.get(1)).commit();
            }
            return true;
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new HomeFragment());
        transaction.commit();

//        context = HomeActivity.this;

        RoutesAppManager routesAppManager = new RoutesAppManager(this, this);
        routesAppManager.postRoutes();

    }

    public static boolean isTimeAutomatic(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
        } else {
            return android.provider.Settings.System.getInt(c.getContentResolver(), android.provider.Settings.System.AUTO_TIME, 0) == 1;
        }
    }

    public void setupDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent);
        } else if (id == R.id.sync_down) {
//            Intent intent = new Intent(HomeActivity.this, FAQsActivity.class);
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            startActivity(intent);

        } else if (id == R.id.sync_up) {

//            Intent intent1 = new Intent(context, WebViewActivity.class);
//            intent1.putExtra("URL", Privacy_Policy_Url);
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            startActivity(intent1);
//            finish();
        } else if (id == R.id.privacy_policy) {

//            Intent intent1 = new Intent(context, WebViewActivity.class);
//            intent1.putExtra("URL", Terms_Conditions_Url);
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            startActivity(intent1);
//            finish();
        } else if (id == R.id.setting) {
//            Intent intent = new Intent(HomeActivity.this, ContactUsActivity.class);
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            startActivity(intent);
        } else if (id == R.id.attendance) {
            Intent intent = new Intent(HomeActivity.this, DailyAttendenceActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            finish();
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void notify(Object obj, String type) {

        if (type.equalsIgnoreCase("routes")) {
            operationList = (ArrayList<Routes_Model>) obj;
            for (Routes_Model route : operationList) {

                ShopAppManager shopAppManager = new ShopAppManager(HomeActivity.this, HomeActivity.this);
                JSONObject shop_params = new JSONObject();

                try {
                    shop_params.put("route_id", route.getRoute_id());
                    shopAppManager.getShops(shop_params);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if (type.equalsIgnoreCase("shops")) {
            ArrayList<ShopList_Model> routesShopList = (ArrayList<ShopList_Model>) obj;
            for (ShopList_Model shop : routesShopList) {
                CompaignAppManager compaignAppManager = new CompaignAppManager(HomeActivity.this, HomeActivity.this);
                compaignAppManager.getShopCompaigns("" + shop.getShop_id());
            }
        }
        else if (type.equalsIgnoreCase("compaigns")) {
            ArrayList<Compaign_Model> campaignList = (ArrayList<Compaign_Model>) obj;
            for (Compaign_Model compaigns : campaignList) {
                SurveysAppManager surveysAppManager = new SurveysAppManager(HomeActivity.this, HomeActivity.this);
                surveysAppManager.getShopSurveys(Integer.parseInt("" + compaigns.getCompaign_id()));
            }
        }
//        else if (type.equalsIgnoreCase("surveys")) {
//            ArrayList<Surveys_Model> surveysList = (ArrayList<Surveys_Model>) obj;
//            for (Surveys_Model surveys : surveysList) {
//                QuestionsAppManager questionsAppManager = new QuestionsAppManager(HomeActivity.this, HomeActivity.this);
//                questionsAppManager.getSurveyQuestions(Integer.parseInt("" + surveys.getSurvey_id()));
//            }
//        }
    }

    public void startProgress() {
        for (value = 0; value < 100; value = value + 1) {
            try {
                Thread.sleep(50);
                progressBar.setProgress(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    text_id.setText(String.valueOf(value));
                }
            }, 5000);
        }
    }
}