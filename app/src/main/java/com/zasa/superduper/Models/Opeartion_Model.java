package com.zasa.superduper.Models;
public class Opeartion_Model {
    private String assign_area;
    private String assign_shop;
    private String assign_line;

    public Opeartion_Model(String assign_area, String assign_shop, String assign_line) {
        this.assign_area = assign_area;
        this.assign_shop = assign_shop;
        this.assign_line = assign_line;
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
}
