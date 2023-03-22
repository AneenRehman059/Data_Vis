package com.zasa.superduper.ApiManager;

import static com.zasa.superduper.helpers.LocalDB.QUESTIONS_TABLE;

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
import com.zasa.superduper.Models.Question_Model;
import com.zasa.superduper.MyCallBack;
import com.zasa.superduper.helpers.LocalDB;
import com.zasa.superduper.helpers.PreferencesData;
import com.zasa.superduper.retrofit.ApiEndpoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuestionsAppManager {

    private static final String TAG = "SyncManager";



    private Activity parentActivity;

    //private WebServiceObjectClient client;

    private MyCallBack callBack;

    private JSONObject jRequestParam;

    public String selectedGridDate;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    LocalDB localDB;

    public QuestionsAppManager(Activity parentActivity, MyCallBack callBack) {
        this.parentActivity = parentActivity;
        this.callBack = callBack;
    }

    public void getSurveyQuestions(final JSONObject requestParams) {

        jRequestParam = requestParams;
        requestQueue = Volley.newRequestQueue(this.parentActivity);

        JSONObject jobj= new JSONObject();
        progressDialog = new ProgressDialog(this.parentActivity);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        localDB = new LocalDB(parentActivity);

        try {
            jobj.put("request",jRequestParam);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiEndpoints.QuestionURL,null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if(response.has("questions")){

                                // Set Api's Data Here
                                JSONArray jQuestions = response.getJSONArray("questions");
                                ArrayList<Question_Model> questionList = new ArrayList<>();
                                for (int i = 0; i < jQuestions.length(); i++) {
//                                    JSONObject jsonObject =
//                                    jCompaigns.getJSONObject(i).getString("compaign_name");
                                    questionList.add(new Question_Model(jQuestions.getJSONObject(i).getInt("question_id"),"",jQuestions.getJSONObject(i).getString("question_name"),"","",jQuestions.getJSONObject(i).getString("question_type"),"","",jQuestions.getJSONObject(i).getInt("survey_id")));

                                    ContentValues contentValues = new ContentValues();
                                    SQLiteDatabase db = localDB.getWritableDatabase();
                                    contentValues.put("question_id", questionList.get(i).getQuestion_id());
                                    contentValues.put("question_code", questionList.get(i).getQuestion_code());
                                    contentValues.put("question_name", questionList.get(i).getQuestion_name());
                                    contentValues.put("question_description", questionList.get(i).getQuestion_description());
                                    contentValues.put("question_status", questionList.get(i).getQuestion_status());
                                    contentValues.put("question_type", questionList.get(i).getQuestion_type());
                                    contentValues.put("created_at", questionList.get(i).getCreated_at());
                                    contentValues.put("updated_at", questionList.get(i).getUpdated_at());
                                    contentValues.put("survey_id", questionList.get(i).getSurvey_id());

                                    db.insert(QUESTIONS_TABLE, null, contentValues);
                                }

                                callBack.notify(questionList,"questions");
//                                callBack.notify(response.getJSONObject("user_info"),"login");
                            }else{

                                if(!parentActivity.isFinishing())
                                    Toast.makeText(parentActivity, response.get("message").toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
                            progressDialog.dismiss();

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
                        ArrayList<Question_Model> questionList = new ArrayList<>();
                        questionList.add(new Question_Model(1,"","Rose Patel","","","text","","",1));
                        questionList.add(new Question_Model(1,"","Tulip","","","picture","","",1));
                        questionList.add(new Question_Model(1,"","Maxob","","","qr","","",1));
                        questionList.add(new Question_Model(1,"","Competitors","","","text","","",1));
                        questionList.add(new Question_Model(1,"","Competitors","","","picture","","",1));
                        questionList.add(new Question_Model(1,"","Competitors","","","qr","","",1));
                        questionList.add(new Question_Model(1,"","Rose Patel","","","text","","",1));
                        questionList.add(new Question_Model(1,"","Rose Patel","","","picture","","",1));
                        questionList.add(new Question_Model(1,"","Rose Patel","","","qr","","",1));

                        callBack.notify(questionList,"questions");


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
