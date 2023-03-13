package com.zasa.superduper.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.helpers.PreferencesData;


public class MoreActivity extends AppCompatActivity  {

    Context context;


    View more_logout_btn, more_profile_btn, more_back_btn,
            more_Draw_btn, more_privacy_policy_btn, more_Terms_Conditions_btn, more_ContactUs_btn,more_FAQs_btn,more_LoyaltyBunchFamily_btn;
    TextView tv_versionName;
    String Privacy_Policy_Url,Terms_Conditions_Url,Contact_Number,Support_Email,Website_Url;
    // ImageView
    int int_Member_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        context = MoreActivity.this;

        more_logout_btn = findViewById(R.id.more_logout_btn);
        more_logout_btn.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   PreferencesData.saveString(MoreActivity.this, "token_id",null);
                                                   PreferencesData.saveString(MoreActivity.this, "isLogged","no");
                                                   SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
                                                   SharedPreferences.Editor editor = sharedPreferences.edit();
                                                   editor.putBoolean("hasLoggedIn", false);
                                                   Intent intent = new Intent(MoreActivity.this,LoginActivity.class);
                                                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                   intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                    startActivity(intent);
                                                    finish();

                                               }
                                           }
        );

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(MoreActivity.this, HomeActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }
}