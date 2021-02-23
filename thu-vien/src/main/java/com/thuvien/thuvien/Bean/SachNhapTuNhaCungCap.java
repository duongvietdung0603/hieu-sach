package com.thuvien.thuvien.Bean;

import java.util.Date;

public class SachNhapTuNhaCungCap {
    private Long MaSach;
    private String tenSach;
    private String tacGia;
    private int soLuong;
    private double tongTien;
    private Date ngayNhap;

    public SachNhapTuNhaCungCap() {
    }

    public SachNhapTuNhaCungCap(Long maSach, String tenSach, String tacGia, int soLuong, Date ngayNhap) {
        MaSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Long getMaSach() {
        return MaSach;
    }

    public void setMaSach(Long maSach) {
        MaSach = maSach;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}
