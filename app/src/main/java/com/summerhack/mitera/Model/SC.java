package com.summerhack.mitera.Model;

public class SC {
    private String name;
    private String desc;
public SC(){

}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public SC(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
