package com.zasa.superduper.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zasa.superduper.R;


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

        /*more_shareApp_btn = view.findViewById(R.id.more_shareApp_btn);
        more_shareApp_btn.setOnClickListener(this);*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MoreActivity.this, HomeActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }
}