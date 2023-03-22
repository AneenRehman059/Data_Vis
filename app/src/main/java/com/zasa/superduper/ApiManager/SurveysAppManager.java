package com.zasa.superduper.ApiManager;

import static com.zasa.superduper.helpers.LocalDB.SURVEYS_TABLE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zasa.superduper.Models.Surveys_Model;
import com.zasa.superduper.MyCallBack;
import com.zasa.superduper.helpers.LocalDB;
import com.zasa.superduper.helpers.PreferencesData;
import com.zasa.superduper.retrofit.ApiEndpoints;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SurveysAppManager {
    private static final String TAG = "SyncManager";



    private Activity parentActivity;

    //private WebServiceObjectClient client;

    private MyCallBack callBack;

    private JSONObject jRequestParam;

    public String selectedGridDate;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    LocalDB localDB;

    public SurveysAppManager(Activity parentActivity, MyCallBack callBack) {
        this.parentActivity = parentActivity;
        this.callBack = callBack;
    }

    public void getShopSurveys(int compaign_id) {

        requestQueue = Volley.newRequestQueue(this.parentActivity);

        JSONObject jobj= new JSONObject();
        progressDialog = new ProgressDialog(this.parentActivity);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        localDB = new LocalDB(parentActivity);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiEndpoints.SurveysURL+"/"+compaign_id,null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if(response.has("surveys")){
                                // Set Api's Data Here
                                JSONArray jSurveys = response.getJSONArray("surveys");
                                ArrayList<Surveys_Model> catList = new ArrayList<>();
                                for (int i = 0; i < jSurveys.length(); i++) {
                                    catList.add(new Surveys_Model(jSurveys.getJSONObject(i).getInt("survey_id"),"",jSurveys.getJSONObject(i).getString("survey_name"),"","",jSurveys.getJSONObject(i).getInt("compaign_id"),"",""));

                                    ContentValues contentValues = new ContentValues();
                                    SQLiteDatabase db = localDB.getWritableDatabase();
                                    contentValues.put("survey_id", catList.get(i).getSurvey_id());
                                    contentValues.put("survey_code", catList.get(i).getSurvey_code());
                                    contentValues.put("survey_name", catList.get(i).getSurvey_name());
                                    contentValues.put("survey_description", catList.get(i).getSurvey_description());
                                    contentValues.put("survey_status", catList.get(i).getSurvey_status());
                                    contentValues.put("compaign_id", catList.get(i).getCompaign_id());
                                    contentValues.put("created_at", catList.get(i).getCreated_at());
                                    contentValues.put("updated_at", catList.get(i).getUpdated_at());

                                    db.insert(SURVEYS_TABLE, null, contentValues);

                                }

                                callBack.notify(catList,"surveys");
//                                callBack.notify(response.getJSONObject("user_info"),"login");
                            }else{

                                if(!parentActivity.isFinishing())
                                    Toast.makeText(parentActivity, response.get("message").toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
                            if(!parentActivity.isFinishing())
                                Toast.makeText(parentActivity, ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if(!parentActivity.isFinishing())
                            progressDialog.dismiss();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(!parentActivity.isFinishing())
                            progressDialog.dismiss();
                        String em="";
                        if(error.getMessage()!=null)
                            em=error.getMessage();
                        ArrayList<Surveys_Model> catList = new ArrayList<>();
                        catList.add(new Surveys_Model(1,"","Rose Patel","","",1,"",""));
                        catList.add(new Surveys_Model(1,"","Tulip","","",1,"",""));
                        catList.add(new Surveys_Model(1,"","Maxob","","",1,"",""));
                        catList.add(new Surveys_Model(1,"","Competitors","","",1,"",""));
                        catList.add(new Surveys_Model(1,"","Competitors","","",1,"",""));
                        catList.add(new Surveys_Model(1,"","Competitors","","",1,"",""));
                        catList.add(new Surveys_Model(1,"","Rose Patel","","",1,"",""));

                        callBack.notify(catList,"surveys");

//                        if(!parentActivity.isFinishing())
//                            Variables.showAlert(parentActivity,"Alert","Server Connectivity error. Slow or no Internet Connection.\n"+em);
                    }
                }
        ){
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token  = PreferencesData.getString(parentActivity, "token_id","");
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
//				params.put("token", ACCESS_TOKEN);
                params.put("Authorization", "Bearer " + token);
                return params;

            }
        };
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);

        requestQueue.add(jsonObjectRequest);

    }

}
