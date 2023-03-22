package com.zasa.superduper.retrofit;

public class ApiEndpoints {
    public static String WEB_URL = "http://idlogix.utis.pk:8089";
    public static String BASE_URL = WEB_URL + "/api/";
    public static String SignInURL = BASE_URL + "login";
    public static String ProfileURL = BASE_URL + "userLogin";
    public static String StoreURL = BASE_URL + "get_stores";
    public static String GetRoutesURL = BASE_URL + "routes/";
    public static String SurveysURL = BASE_URL + "surveys";
    public static String CompaignURL = BASE_URL + "compaigns";
    public static String QuestionURL = BASE_URL + "questions/";

    public static String ShopsURL = BASE_URL + "shops/";

    public static String PREF_TOKEN = "isToken";
}
