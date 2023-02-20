package com.zasa.superduper.Models;

import java.util.List;

public class Category_Model {
    private String category_name;
    private boolean isExpandable;
    private List<String> nestedList;

    public Category_Model(String category_name, List<String> nestedList) {
        this.category_name = category_name;
        this.nestedList = nestedList;
        isExpandable = false;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public List<String> getNestedList() {
        return nestedList;
    }

    public void setNestedList(List<String> nestedList) {
        this.nestedList = nestedList;
    }
}
