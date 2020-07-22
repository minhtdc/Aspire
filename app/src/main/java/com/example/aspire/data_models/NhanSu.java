package com.example.aspire.data_models;

public class NhanSu {
    private String name, des;

    public NhanSu() { }

    public NhanSu(String name, String degree) {
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
