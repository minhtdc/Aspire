package com.example.aspire.data_models;

public class Users {
    private String name, des;

    public Users() { }

    public Users(String name, String degree) {
        this.name = name;
        this.des = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
