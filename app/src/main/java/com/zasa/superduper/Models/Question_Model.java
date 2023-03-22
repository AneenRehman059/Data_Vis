package com.zasa.superduper.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Question_Model implements Serializable {
    private int question_id;
    private String question_code;
    private String question_name;
    private String question_description;
    private String question_status;
    private String question_type;
    private String created_at;
    private String updated_at;
    private int survey_id;
    private String answer;
    private ArrayList<String> arrayList ;

    public Question_Model(int question_id,String question_code,String question_name,String question_description,String question_status, String type,String created_at,String updated_at,int survey_id) {
        this.question_id = question_id;
        this.question_code = question_code;
        this.question_name = question_name;
        this.question_description = question_description;
        this.question_status = question_status;
        this.question_type = type;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.survey_id = survey_id;
        this.arrayList = new ArrayList<>();
        this.answer = "";
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_code() {
        return question_code;
    }

    public void setQuestion_code(String question_code) {
        this.question_code = question_code;
    }

    public String getQuestion_description() {
        return question_description;
    }

    public void setQuestion_description(String question_description) {
        this.question_description = question_description;
    }

    public String getQuestion_status() {
        return question_status;
    }

    public void setQuestion_status(String question_status) {
        this.question_status = question_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }
}
