package com.zasa.superduper.Models;

public class ShopList_Model {
    private int shop_id;
    private String shop_code;
    private String shop_name;
    private String shop_description;
    private String shop_status;
    private String shop_owner_name;
    private int shop_owner_phone;
    private String shop_owner_status;
    private String created_at;
    private String updated_at;
    private int channel_id;
    private int class_id;
    private int shop_category_id;
    private int shop_subcategory_id;
    private String shop_address;

    public ShopList_Model(int shop_id, String shop_code, String shop_name, String shop_description, String shop_status, String shop_owner_name, int shop_owner_phone, String shop_owner_status, String created_at, String updated_at, int channel_id, int class_id, int shop_category_id, int shop_subcategory_id, String shop_address) {
        this.shop_id = shop_id;
        this.shop_code = shop_code;
        this.shop_name = shop_name;
        this.shop_description = shop_description;
        this.shop_status = shop_status;
        this.shop_owner_name = shop_owner_name;
        this.shop_owner_phone = shop_owner_phone;
        this.shop_owner_status = shop_owner_status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.channel_id = channel_id;
        this.class_id = class_id;
        this.shop_category_id = shop_category_id;
        this.shop_subcategory_id = shop_subcategory_id;
        this.shop_address = shop_address;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_code() {
        return shop_code;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_description() {
        return shop_description;
    }

    public void setShop_description(String shop_description) {
        this.shop_description = shop_description;
    }

    public String getShop_status() {
        return shop_status;
    }

    public void setShop_status(String shop_status) {
        this.shop_status = shop_status;
    }

    public String getShop_owner_name() {
        return shop_owner_name;
    }

    public void setShop_owner_name(String shop_owner_name) {
        this.shop_owner_name = shop_owner_name;
    }

    public int getShop_owner_phone() {
        return shop_owner_phone;
    }

    public void setShop_owner_phone(int shop_owner_phone) {
        this.shop_owner_phone = shop_owner_phone;
    }

    public String getShop_owner_status() {
        return shop_owner_status;
    }

    public void setShop_owner_status(String shop_owner_status) {
        this.shop_owner_status = shop_owner_status;
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

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getShop_category_id() {
        return shop_category_id;
    }

    public void setShop_category_id(int shop_category_id) {
        this.shop_category_id = shop_category_id;
    }

    public int getShop_subcategory_id() {
        return shop_subcategory_id;
    }

    public void setShop_subcategory_id(int shop_subcategory_id) {
        this.shop_subcategory_id = shop_subcategory_id;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }
}
