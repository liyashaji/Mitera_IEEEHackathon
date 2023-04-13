package com.summerhack.mitera.Model;

public class Symptom {
    private String title;

    private String desc;
    private String uid;
    public Symptom(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Symptom(String title, String desc, String uid) {
        this.title = title;
        this.desc = desc;
        this.uid = uid;
    }
}
