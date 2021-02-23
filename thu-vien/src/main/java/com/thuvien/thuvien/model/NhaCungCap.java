package com.thuvien.thuvien.model;

import com.thuvien.thuvien.model.NhapSach.NhapSach;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nha_cung_cap")
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ten;
    private String diaChi = "Đang cập nhật";
    private String dienThoai = "Đang cập nhật";
    private String email = "Đang cập nhật";
    private String ghiChu ;

    @OneToMany( mappedBy = "nhaCungCap", cascade = CascadeType.ALL)
    private List<NhapSach> nhapSach;

    public NhaCungCap() {
    }

    public NhaCungCap(String ten) {
        this.ten = ten;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
