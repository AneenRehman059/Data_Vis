package com.zasa.superduper.Models;

public class Daily_OperationModule_Model {
    int moduledPic;
    private String module_name;

    public Daily_OperationModule_Model(int moduledPic, String module_name) {
        this.moduledPic = moduledPic;
        this.module_name = module_name;
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
}
