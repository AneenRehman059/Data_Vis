package com.zasa.superduper.ApiManager;

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
import com.zasa.superduper.Models.Routes_Model;
import com.zasa.superduper.Models.ShopList_Model;
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

public class ShopAppManager {

    private static final String TAG = "SyncManager";
    private Activity parentActivity;

    //private WebServiceObjectClient client;
    private MyCallBack callBack;

    private JSONObject jRequestParam;

    public String selectedGridDate;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    LocalDB localDB ;

    public ShopAppManager(Activity parentActivity, MyCallBack callBack) {
        this.parentActivity = parentActivity;
        this.callBack = callBack;
    }

    public void getShops(final JSONObject requestParams) {

        jRequestParam = requestParams;
        requestQueue = Volley.newRequestQueue(this.parentActivity);

        JSONObject jobj = new JSONObject();
        progressDialog = new ProgressDialog(this.parentActivity);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        localDB = new LocalDB(parentActivity);

        String urlParam="";
        try {
//            jobj.put("request", jRequestParam);
            urlParam = requestParams.getString("route_id");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, ApiEndpoints.ShopsURL+urlParam, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if(response.has("shops")){
                                // Set Api's Data Here
                                JSONArray jShops = response.getJSONArray("shops");
                                ArrayList<ShopList_Model> shopList = new ArrayList<>();
                                for (int i = 0; i < jShops.length(); i++) {
//                                    JSONObject jsonObject =
//                                    jCompaigns.getJSONObject(i).getString("compaign_name");
                                    shopList.add(new ShopList_Model(jShops.getJSONObject(i).getInt("shop_id"),"",jShops.getJSONObject(i).getString("shop_name"), "","","",jShops.getJSONObject(i).getInt("shop_owner_phone"),"","","",jShops.getJSONObject(i).getInt("channel_id"),jShops.getJSONObject(i).getInt("class_id"),jShops.getJSONObject(i).getInt("shop_category_id"),jShops.getJSONObject(i).getInt("shop_subcategory_id"),""));

                                    ContentValues contentValues = new ContentValues();
                                    SQLiteDatabase db = localDB.getWritableDatabase();
                                    contentValues.put("shop_id",shopList.get(i).getShop_id());
                                    contentValues.put("shop_code",shopList.get(i).getShop_code());
                                    contentValues.put("shop_name",shopList.get(i).getShop_name());
                                    contentValues.put("shop_description",shopList.get(i).getShop_description());
                                    contentValues.put("shop_status",shopList.get(i).getShop_status());
                                    contentValues.put("shop_owner_name",shopList.get(i).getShop_owner_name());
                                    contentValues.put("shop_owner_phone",shopList.get(i).getShop_owner_phone());
                                    contentValues.put("shop_owner_status",shopList.get(i).getShop_owner_status());
                                    contentValues.put("created_at",shopList.get(i).getCreated_at());
                                    contentValues.put("updated_at",shopList.get(i).getUpdated_at());
                                    contentValues.put("channel_id",shopList.get(i).getChannel_id());
                                    contentValues.put("class_id",shopList.get(i).getClass_id());
                                    contentValues.put("shop_category_id",shopList.get(i).getShop_category_id());
                                    contentValues.put("shop_subcategory_id",shopList.get(i).getShop_subcategory_id());
                                    contentValues.put("shop_address",shopList.get(i).getShop_address());

                                   long addData = db.update(SHOPS_TABLE,contentValues, "shop_id=" +shopList.get(i).getShop_id(),null);

                                   if (addData != -1){
//                                       Toast.makeText(parentActivity, "Already Exists", Toast.LENGTH_SHORT).show();
                                   }
                                   else
                                   {
                                       db.insert(SHOPS_TABLE,null,contentValues);
                                   }
                                }

                                callBack.notify(shopList,"shops");
//                                callBack.notify(response.getJSONObject("user_info"),"login");
                            }else{

                                if(!parentActivity.isFinishing())
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
                        ArrayList<ShopList_Model> shopList = new ArrayList<>();
                        shopList.add(new ShopList_Model(1,"","Bismillah General Store","Channel Name","","",1234,"","","",1,1,1,1,""));
                        shopList.add(new ShopList_Model(1,"","Bismillah General Store","Channel Name","","",1234,"","","",1,1,1,1,""));
                        shopList.add(new ShopList_Model(1,"","Bismillah General Store","Channel Name","","",1234,"","","",1,1,1,1,""));
                        shopList.add(new ShopList_Model(1,"","Bismillah General Store","Channel Name","","",1234,"","","",1,1,1,1,""));
                        shopList.add(new ShopList_Model(1,"","Bismillah General Store","Channel Name","","",1234,"","","",1,1,1,1,""));
                        shopList.add(new ShopList_Model(1,"","Bismillah General Store","Channel Name","","",1234,"","","",1,1,1,1,""));
                        shopList.add(new ShopList_Model(1,"","Bismillah General Store","Channel Name","","",1234,"","","",1,1,1,1,""));

                        callBack.notify(shopList, "shops");


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
