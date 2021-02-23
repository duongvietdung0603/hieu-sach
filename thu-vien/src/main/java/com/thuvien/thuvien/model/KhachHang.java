package com.thuvien.thuvien.model;


import com.thuvien.thuvien.model.MuonSach.MuonSach;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ten;
    @Column(name = "dienThoai", nullable = false, unique = true)
    private String dienThoai;

    private String ngheNghiep;
    private String diaChi;

    @OneToMany( mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<MuonSach> muonSach;


    public KhachHang() {
    }

    public KhachHang(String ten, String dienThoai) {
        this.ten = ten;
        this.dienThoai = dienThoai;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
