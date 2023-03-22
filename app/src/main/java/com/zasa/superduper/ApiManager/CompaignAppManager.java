package com.zasa.superduper.ApiManager;

import static com.zasa.superduper.helpers.LocalDB.COMPAIGN_TABLE;
import static com.zasa.superduper.helpers.LocalDB.ROUTES_TABLE;
import static com.zasa.superduper.helpers.LocalDB.SHOPS_TABLE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zasa.superduper.Models.Compaign_Model;
import com.zasa.superduper.MyCallBack;
import com.zasa.superduper.R;
import com.zasa.superduper.helpers.LocalDB;
import com.zasa.superduper.helpers.PreferencesData;
import com.zasa.superduper.retrofit.ApiEndpoints;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompaignAppManager {
    private static final String TAG = "SyncManager";


    private Activity parentActivity;

    //private WebServiceObjectClient client;

    private MyCallBack callBack;

    private JSONObject jRequestParam;

    public String selectedGridDate;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    LocalDB localDB;

    public CompaignAppManager(Activity parentActivity, MyCallBack callBack) {
        this.parentActivity = parentActivity;
        this.callBack = callBack;
    }

    public void getShopCompaigns(String shop_id) {

        requestQueue = Volley.newRequestQueue(this.parentActivity);
        progressDialog = new ProgressDialog(this.parentActivity);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        localDB = new LocalDB(parentActivity);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, ApiEndpoints.CompaignURL + "/" + shop_id, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if (response.has("compaigns")) {
                                // Set Api's Data Here
                                JSONArray jCompaigns = response.getJSONArray("compaigns");
                                ArrayList<Compaign_Model> moduleList = new ArrayList<>();

                                for (int i = 0; i < jCompaigns.length(); i++) {
                                    JSONObject jsonObject = jCompaigns.getJSONObject(i);
                                    Compaign_Model compaign = new Compaign_Model(jCompaigns.getJSONObject(i).getInt("compaign_id"), "", jCompaigns.getJSONObject(i).getString("compaign_name"), "", "", "", "", jCompaigns.getJSONObject(i).getString("compaign_image"), "", "");

                                    if (jsonObject.has("compaign_image"))
                                        compaign.setCompaign_image(ApiEndpoints.WEB_URL + jsonObject.getString("compaign_image"));

                                    compaign.setShop_id(Integer.parseInt(shop_id));

                                    compaign.setCompaign_id(jsonObject.getInt("compaign_id"));
                                    moduleList.add(compaign);

                                    ContentValues contentValues = new ContentValues();
                                    SQLiteDatabase db = localDB.getWritableDatabase();
                                    contentValues.put("compaign_id", moduleList.get(i).getCompaign_id());
                                    contentValues.put("compaign_code", moduleList.get(i).getCompaign_code());
                                    contentValues.put("compaign_name", moduleList.get(i).getCompaign_name());
                                    contentValues.put("compaign_description", moduleList.get(i).getCompaign_description());
                                    contentValues.put("compaign_start_date", moduleList.get(i).getCompaign_start_date());
                                    contentValues.put("compaign_end_date", moduleList.get(i).getCompaign_end_date());
                                    contentValues.put("compaign_status", moduleList.get(i).getCompaign_status());
                                    contentValues.put("compaign_image", moduleList.get(i).getCompaign_image());
                                    contentValues.put("created_at", moduleList.get(i).getCreated_at());
                                    contentValues.put("updated_at", moduleList.get(i).getUpdated_at());

//                                    db.insert(COMPAIGN_TABLE, null, contentValues);

                                    long addData = db.update(COMPAIGN_TABLE,contentValues, "compaign_id=" +moduleList.get(i).getCompaign_id(),null);
                                    if (addData != -1){
//                                        Toast.makeText(parentActivity, "Already Exists", Toast.LENGTH_SHORT).show();
//
                                    }
                                    else
                                    {
                                        db.insert(COMPAIGN_TABLE, null, contentValues);
                                    }

                                }


                                callBack.notify(moduleList, "questions");

                            } else {

                                if (!parentActivity.isFinishing())
                                    Toast.makeText(parentActivity, response.get("message").toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
                            if (!parentActivity.isFinishing())
                                Toast.makeText(parentActivity, ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if (!parentActivity.isFinishing())
                            progressDialog.dismiss();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (!parentActivity.isFinishing())
                            progressDialog.dismiss();
                        String em = "";
                        if (error.getMessage() != null)
                            em = error.getMessage();
                        ArrayList<Compaign_Model> moduleList = new ArrayList<>();
                        moduleList.add(new Compaign_Model(1,  "", "SKU Availability", "", "", "", "", "", "", ""));
                        moduleList.add(new Compaign_Model(1 , "", "Planogram", "", "", "", "", "", "", ""));
                        moduleList.add(new Compaign_Model(1,  "", "GPS Check in", "", "", "", "", "", "", ""));
                        moduleList.add(new Compaign_Model(1 , "", "Expiry Tracking", "", "", "", "", "", "", ""));
                        moduleList.add(new Compaign_Model(1 , "", "OSA (Competitor)", "", "", "", "", "", "", ""));
                        moduleList.add(new Compaign_Model(1,  "", "Secondary Gondola Display", "", "", "", "", "", "", ""));
                        moduleList.add(new Compaign_Model(1,  "", "Outlet Facia Picture", "", "", "", "", "", "", ""));
                        moduleList.add(new Compaign_Model(1,  "", "POSM Tracking", "", "", "", "", "", "", ""));
                        moduleList.add(new Compaign_Model(1,  "", "Share to Shelf", "", "", "", "", "", "", ""));

                        callBack.notify(moduleList, "questions");


//                        if(!parentActivity.isFinishing())
//                            Variables.showAlert(parentActivity,"Alert","Server Connectivity error. Slow or no Internet Connection.\n"+em);
                    }
                }
        ) {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = PreferencesData.getString(parentActivity, "token_id", "");
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
