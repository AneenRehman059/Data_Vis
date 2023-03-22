package com.zasa.superduper.Models;

public class Compaign_Model {
    private int shop_id;
    private int compaign_id;
    private String compaign_code;
    private String compaign_name;
    private String compaign_description;
    private String compaign_start_date;
    private String compaign_end_date;
    private String compaign_status;
    private String compaign_image;
    private String created_at;
    private String updated_at;

    public Compaign_Model( int compaign_id, String compaign_code, String compaign_name, String compaign_description, String compaign_start_date, String compaign_end_date, String compaign_status, String compaign_image, String created_at, String updated_at) {
        this.compaign_id = compaign_id;
        this.compaign_code = compaign_code;
        this.compaign_name = compaign_name;
        this.compaign_description = compaign_description;
        this.compaign_start_date = compaign_start_date;
        this.compaign_end_date = compaign_end_date;
        this.compaign_status = compaign_status;
        this.compaign_image = compaign_image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getCompaign_id() {
        return compaign_id;
    }

    public void setCompaign_id(int compaign_id) {
        this.compaign_id = compaign_id;
    }

    public String getCompaign_name() {
        return compaign_name;
    }

    public void setCompaign_name(String compaign_name) {
        this.compaign_name = compaign_name;
    }

    public String getCompaign_image() {
        return compaign_image;
    }

    public void setCompaign_image(String compaign_image) {
        this.compaign_image = compaign_image;
    }

    public String getCompaign_code() {
        return compaign_code;
    }

    public void setCompaign_code(String compaign_code) {
        this.compaign_code = compaign_code;
    }

    public String getCompaign_description() {
        return compaign_description;
    }

    public void setCompaign_description(String compaign_description) {
        this.compaign_description = compaign_description;
    }

    public String getCompaign_start_date() {
        return compaign_start_date;
    }

    public void setCompaign_start_date(String compaign_start_date) {
        this.compaign_start_date = compaign_start_date;
    }

    public String getCompaign_end_date() {
        return compaign_end_date;
    }

    public void setCompaign_end_date(String compaign_end_date) {
        this.compaign_end_date = compaign_end_date;
    }

    public String getCompaign_status() {
        return compaign_status;
    }

    public void setCompaign_status(String compaign_status) {
        this.compaign_status = compaign_status;
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
