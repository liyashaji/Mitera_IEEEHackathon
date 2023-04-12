package com.summerhack.mitera.Model;

import java.util.UUID;

public class User {
    private String name;
    private String phone;
    private String address;
    private String email;
    private String id;
    private String age;

    public String  getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User(String name, String phone, String address, String email, String id,String age) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.id = id;
        this.age = age;
    }
    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
