package com.thuvien.thuvien.model.NhapSach;

import com.thuvien.thuvien.model.Sach;

import javax.persistence.*;

@Entity
@Table(name = "nhap_sach_chi_tiet")
public class NhapSachChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int soLuong = 1;
    private double thanhTien = 0;
    private String ghiChu = "";

    @ManyToOne
    @JoinColumn(name = "nhapSach_id", nullable = false)
    private NhapSach nhapSach;

    @ManyToOne
    @JoinColumn(name = "sach_id", nullable = false)
    private Sach sach;

    public NhapSachChiTiet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public NhapSach getNhapSach() {
        return nhapSach;
    }

    public void setNhapSach(NhapSach nhapSach) {
        this.nhapSach = nhapSach;
    }
}
