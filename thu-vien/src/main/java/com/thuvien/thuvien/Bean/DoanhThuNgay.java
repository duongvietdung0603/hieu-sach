package com.thuvien.thuvien.Bean;

public class DoanhThuNgay {
    private String name;
    private double data;

    public DoanhThuNgay() {
    }

    public DoanhThuNgay(String name, double data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
