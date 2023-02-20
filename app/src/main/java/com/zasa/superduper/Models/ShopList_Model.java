package com.zasa.superduper.Models;

public class ShopList_Model {
    private String shop_name;
    private String channel_name;
    private String nearest_place;
    private String contact;

    public ShopList_Model(String shop_name, String channel_name, String nearest_place, String contact) {
        this.shop_name = shop_name;
        this.channel_name = channel_name;
        this.nearest_place = nearest_place;
        this.contact = contact;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getNearest_place() {
        return nearest_place;
    }

    public void setNearest_place(String nearest_place) {
        this.nearest_place = nearest_place;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
