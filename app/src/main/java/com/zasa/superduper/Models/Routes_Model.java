package com.zasa.superduper.Models;
public class Routes_Model {
    private String route_name;
    private int route_id;
    private String route_code;
    private String route_description;
    private String route_status;
    private String created_at;
    private String updated_at;
    private int town_id;

//    public Routes_Model(String assign_area) {
//        this.route_name = assign_area;
//    }


    public Routes_Model(String route_name, int route_id, String route_code, String route_description, String route_status, String created_at, String updated_at, int town_id) {
        this.route_name = route_name;
        this.route_id = route_id;
        this.route_code = route_code;
        this.route_description = route_description;
        this.route_status = route_status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.town_id = town_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getRoute_code() {
        return route_code;
    }

    public void setRoute_code(String route_code) {
        this.route_code = route_code;
    }

    public String getRoute_description() {
        return route_description;
    }

    public void setRoute_description(String route_description) {
        this.route_description = route_description;
    }

    public String getRoute_status() {
        return route_status;
    }

    public void setRoute_status(String route_status) {
        this.route_status = route_status;
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

    public int getTown_id() {
        return town_id;
    }

    public void setTown_id(int town_id) {
        this.town_id = town_id;
    }
}
