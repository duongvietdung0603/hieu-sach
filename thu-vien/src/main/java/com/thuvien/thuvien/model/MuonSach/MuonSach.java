package com.thuvien.thuvien.model.MuonSach;


import com.thuvien.thuvien.model.KhachHang;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "muon_sach")
public class MuonSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date ngayMuon;

    private int soLuong = 0;
    private double thanhTien = 0;

    private byte status = 0; // 1 là đã trả hết

    @ManyToOne
    @JoinColumn(name = "khachHang_id", nullable = false)
    private KhachHang khachHang;


    @OneToMany( mappedBy = "muonSach", cascade = CascadeType.ALL)
    private List<MuonSachChiTiet> muonSachChiTiet;


    public MuonSach() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
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

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
