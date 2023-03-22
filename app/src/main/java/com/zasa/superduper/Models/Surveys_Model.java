package com.zasa.superduper.Models;

public class Surveys_Model {
    private int survey_id;
    private String survey_code;
    private String survey_name;
    private String survey_description;
    private String survey_status;
    private int compaign_id;
    private String created_at;
    private String updated_at;


    public Surveys_Model(int survey_id, String survey_code, String survey_name, String survey_description, String survey_status, int compaign_id, String created_at, String updated_at) {
        this.survey_id = survey_id;
        this.survey_code = survey_code;
        this.survey_name = survey_name;
        this.survey_description = survey_description;
        this.survey_status = survey_status;
        this.compaign_id = compaign_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public String getSurvey_code() {
        return survey_code;
    }

    public void setSurvey_code(String survey_code) {
        this.survey_code = survey_code;
    }

    public String getSurvey_name() {
        return survey_name;
    }

    public void setSurvey_name(String survey_name) {
        this.survey_name = survey_name;
    }

    public String getSurvey_description() {
        return survey_description;
    }

    public void setSurvey_description(String survey_description) {
        this.survey_description = survey_description;
    }

    public String getSurvey_status() {
        return survey_status;
    }

    public void setSurvey_status(String survey_status) {
        this.survey_status = survey_status;
    }

    public int getCompaign_id() {
        return compaign_id;
    }

    public void setCompaign_id(int compaign_id) {
        this.compaign_id = compaign_id;
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
}
