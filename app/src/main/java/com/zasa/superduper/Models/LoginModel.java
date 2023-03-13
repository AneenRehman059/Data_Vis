package com.zasa.superduper.Models;

import com.zasa.superduper.MyApp;
import com.zasa.superduper.helpers.PreferencesData;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginModel {
    private String name;
    private String email;
    private String id;

    public LoginModel(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginModel getLoggedUserFromPreference()
    {
        JSONObject userPreference = PreferencesData.getJsonObject(MyApp.getMyAppContext(),"userObject");
        try {
            if(userPreference!=null) {
                this.id = "" + userPreference.get("id");
                this.name = "" + userPreference.getString("name");
                this.email = "" + userPreference.getString("email");
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

}
