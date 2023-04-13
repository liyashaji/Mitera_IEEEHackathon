package com.summerhack.mitera.Model;

import java.util.Calendar;
import java.util.Date;

public class Chat {
    private String msg;
    private Date date;
    private String id;
    public Chat(){

    }

    @Override
    public String toString() {
        return "Chat{" +
                "msg='" + msg + '\'' +
                ", date=" + date +
                ", id='" + id + '\'' +
                '}';
    }

    public Chat(String msg, Date date, String id) {
        this.msg = msg;
        this.date = date;
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
