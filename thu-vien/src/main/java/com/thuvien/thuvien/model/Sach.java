package com.thuvien.thuvien.model;


import com.thuvien.thuvien.model.MuonSach.MuonSachChiTiet;
import com.thuvien.thuvien.model.NhapSach.NhapSachChiTiet;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sach")
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenSach;
    private String tacGia = "Đang cập nhật";
    private String nhaXuatBan = "Đang cập nhật";
    private int namXuatBan = 2020;
    private int soLuong = 0;
    private double giaBia = 60000;
    private double giaMuon;
//    private double sale;

    @ManyToOne
    @JoinColumn(name = "theLoaiSach_id", nullable = false)
    private TheLoaiSach theLoaiSach;

    @OneToMany( mappedBy = "sach", cascade = CascadeType.ALL)
    private List<MuonSachChiTiet> muonSachChiTiet;

    @OneToMany( mappedBy = "sach", cascade = CascadeType.ALL)
    private List<NhapSachChiTiet> nhapSachChiTiet;

    public Sach() {
    }

    public Sach(String tenSach, TheLoaiSach theLoaiSach) {
        this.tenSach = tenSach;
        this.theLoaiSach = theLoaiSach;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public TheLoaiSach getTheLoaiSach() {
        return theLoaiSach;
    }

    public void setTheLoaiSach(TheLoaiSach theLoaiSach) {
        this.theLoaiSach = theLoaiSach;
    }

    public double getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(double giaBia) {
        this.giaBia = giaBia;
    }

    public double getGiaMuon() {
        return giaMuon;
    }

    public void setGiaMuon(double giaMuon) {
        this.giaMuon = giaMuon;
    }
}
