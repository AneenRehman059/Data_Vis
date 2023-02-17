package com.zasa.superduper;

public class Scoring_Criteria_Model {
    int scoring_pic;
    private String itemText;

    public Scoring_Criteria_Model(int scoring_pic, String itemText) {
        this.scoring_pic = scoring_pic;
        this.itemText = itemText;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public int getScoring_pic() {
        return scoring_pic;
    }

    public void setScoring_pic(int scoring_pic) {
        this.scoring_pic = scoring_pic;
    }

}
