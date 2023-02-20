package com.zasa.superduper.Models;
public class Opeartion_Model {
    private String assign_area;
    private String assign_shop;
    private String assign_line;
    private String last_purchase;

    public Opeartion_Model(String assign_area, String assign_shop, String assign_line, String last_purchase) {
        this.assign_area = assign_area;
        this.assign_shop = assign_shop;
        this.assign_line = assign_line;
        this.last_purchase = last_purchase;
    }

    public String getAssign_area() {
        return assign_area;
    }

    public void setAssign_area(String assign_area) {
        this.assign_area = assign_area;
    }

    public String getAssign_shop() {
        return assign_shop;
    }

    public void setAssign_shop(String assign_shop) {
        this.assign_shop = assign_shop;
    }

    public String getAssign_line() {
        return assign_line;
    }

    public void setAssign_line(String assign_line) {
        this.assign_line = assign_line;
    }

    public String getLast_purchase() {
        return last_purchase;
    }

    public void setLast_purchase(String last_purchase) {
        this.last_purchase = last_purchase;
    }
}
