package com.zasa.superduper.Models;

public class Question_Model {
    private String question_name;

    private String type;

    public Question_Model(String question_name, String type) {
        this.question_name = question_name;
        this.type = type;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
