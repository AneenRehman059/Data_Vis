package com.zasa.superduper.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.zasa.superduper.Models.Compaign_Model;
import com.zasa.superduper.Models.Question_Model;
import com.zasa.superduper.Models.Routes_Model;
import com.zasa.superduper.Models.ShopList_Model;
import com.zasa.superduper.Models.Surveys_Model;

import java.util.ArrayList;

import retrofit2.http.Query;

public class LocalDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DataVis";
    public static final String ROUTES_TABLE = "routes_table";
    public static final String SHOPS_TABLE = "shops_table";
    public static final String COMPAIGN_TABLE = "compaign_table";
    public static final String SURVEYS_TABLE = "surveys_table";
    public static final String QUESTIONS_TABLE = "questions_table";
    public static final int VER = 1;

    public LocalDB(@Nullable Context context) {

        super(context, DATABASE_NAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create  table " + ROUTES_TABLE + "(route_id integer primary key, route_code text, route_name text, route_description text, route_status text," +
                " created_at text, updated_at text, town_id integer)");

        db.execSQL("create table " + SHOPS_TABLE + "(shop_id integer primary key, shop_code text, shop_name text, shop_description text, shop_status text, " +
                "shop_owner_name text, " + "shop_owner_phone integer, shop_owner_status text, created_at text, updated_at text, channel_id integer," +
                "class_id integer,shop_category_id integer,shop_subcategory_id integer,shop_address text)");

        db.execSQL("create table "+COMPAIGN_TABLE+"(compaign_id integer primary key,compaign_code text,compaign_name text,compaign_description text," +
                "compaign_start_date text,compaign_end_date text,compaign_status text,compaign_image text,created_at text,updated_at text)");

        db.execSQL("create table "+SURVEYS_TABLE+"(survey_id integer primary key, survey_code text,survey_name text,survey_description text," +
                "survey_status text,compaign_id integer,created_at text,updated_at text)");

        db.execSQL("create table "+QUESTIONS_TABLE+"(question_id integer primary key, question_code text,question_name text,question_description text," +
                "question_status text,question_type text,created_at text,updated_at text,survey_id integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + ROUTES_TABLE + "");
        db.execSQL("drop table if exists " + SHOPS_TABLE + "");
        db.execSQL("drop table if exists "+COMPAIGN_TABLE+"");
        db.execSQL("drop table if exists "+SURVEYS_TABLE+"");
        db.execSQL("drop table if exists "+QUESTIONS_TABLE+"");
        onCreate(db);
    }

    public ArrayList<Routes_Model> getRoutes() {
        ArrayList<Routes_Model> operationList = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().rawQuery("select * from " + ROUTES_TABLE + "", null);
        while (cursor.moveToNext()) {
            int route_id = cursor.getInt(0);
            String route_code = cursor.getString(1);
            String route_name = cursor.getString(2);
            String route_description = cursor.getString(3);
            String route_status = cursor.getString(4);
            String created_at = cursor.getString(5);
            String updated_at = cursor.getString(6);
            int town_id = cursor.getInt(7);
            operationList.add(new Routes_Model(route_name, route_id, route_code, route_description, route_status, created_at, updated_at, town_id));
        }
        cursor.close();
        return operationList;
    }

    public ArrayList<ShopList_Model> getShops() {
        ArrayList<ShopList_Model> shopList = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from " + SHOPS_TABLE + "", null);

        while (cursor.moveToNext()) {
            int shop_id = cursor.getInt(0);
            String shop_code = cursor.getString(1);
            String shop_name = cursor.getString(2);
            String shop_description = cursor.getString(3);
            String shop_status = cursor.getString(4);
            String shop_owner_name = cursor.getString(5);
            int shop_owner_phone = cursor.getInt(6);
            String shop_owner_status = cursor.getString(7);
            String created_at = cursor.getString(8);
            String updated_at = cursor.getString(9);
            int channel_id = cursor.getInt(10);
            int class_id = cursor.getInt(11);
            int shop_category_id = cursor.getInt(12);
            int shop_subCategory_id = cursor.getInt(13);
            String shop_address = cursor.getString(14);
            shopList.add(new ShopList_Model(shop_id, shop_code, shop_name, shop_description, shop_status, shop_owner_name, shop_owner_phone, shop_owner_status, created_at,updated_at,channel_id,class_id,shop_category_id,shop_subCategory_id,shop_address));
        }
        cursor.close();
        return shopList;
    }

    public ArrayList<Compaign_Model> getCompains() {
        ArrayList<Compaign_Model> moduleList = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().rawQuery("select * from " + COMPAIGN_TABLE + "", null);
        while (cursor.moveToNext()) {
            int compaign_id = cursor.getInt(0);
            String compaign_code = cursor.getString(1);
            String compaign_name = cursor.getString(2);
            String compaign_description = cursor.getString(3);
            String compaign_start_date = cursor.getString(4);
            String compaign_end_date = cursor.getString(5);
            String compaign_status = cursor.getString(6);
            String compaign_image = cursor.getString(7);
            String created_at = cursor.getString(8);
            String updated_at = cursor.getString(9);

            moduleList.add(new Compaign_Model(compaign_id, compaign_code, compaign_name, compaign_description, compaign_start_date, compaign_end_date, compaign_status, compaign_image, created_at, updated_at));
        }
        cursor.close();
        return moduleList;
    }

    public ArrayList<Surveys_Model> getSurveys() {
        ArrayList<Surveys_Model> surveyList = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().rawQuery("select * from " + SURVEYS_TABLE + "", null);
        while (cursor.moveToNext()) {
            int survey_id = cursor.getInt(0);
            String survey_code = cursor.getString(1);
            String survey_name = cursor.getString(2);
            String survey_description = cursor.getString(3);
            String survey_status = cursor.getString(4);
            int compaign_id = cursor.getInt(5);
            String created_at = cursor.getString(6);
            String updated_at = cursor.getString(7);
            surveyList.add(new Surveys_Model(survey_id, survey_code, survey_name, survey_description, survey_status, compaign_id, created_at, updated_at));
        }
        cursor.close();
        return surveyList;
    }

    public ArrayList<Question_Model> getQuestions() {
        ArrayList<Question_Model> questionList = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().rawQuery("select * from " + QUESTIONS_TABLE + "", null);
        while (cursor.moveToNext()) {
            int question_id = cursor.getInt(0);
            String question_code = cursor.getString(1);
            String question_name = cursor.getString(2);
            String question_description = cursor.getString(3);
            String question_status = cursor.getString(4);
            String question_type = cursor.getString(5);
            String created_at = cursor.getString(6);
            String updated_at = cursor.getString(7);
            int survey_id = cursor.getInt(8);
            questionList.add(new Question_Model(question_id, question_code, question_name, question_description, question_status, question_type, created_at, updated_at,survey_id));
        }
        cursor.close();
        return questionList;
    }
}
