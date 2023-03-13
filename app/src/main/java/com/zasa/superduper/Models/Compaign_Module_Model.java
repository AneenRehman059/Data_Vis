package com.zasa.superduper.Models;

public class Compaign_Module_Model {
    int moduledPic;
    private String module_name;
    private String image_url;
    private String compaign_id;
    private String module_id;

    public Compaign_Module_Model(int moduledPic, String module_name) {
        this.moduledPic = moduledPic;
        this.module_name = module_name;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getCompaign_id() {
        return compaign_id;
    }

    public void setCompaign_id(String compaign_id) {
        this.compaign_id = compaign_id;
    }

    public int getModuledPic() {
        return moduledPic;
    }

    public void setModuledPic(int moduledPic) {
        this.moduledPic = moduledPic;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
