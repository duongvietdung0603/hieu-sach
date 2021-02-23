package com.thuvien.thuvien.model.BanSach;

import com.thuvien.thuvien.model.MuonSach.MuonSach;
import com.thuvien.thuvien.model.Sach;

import javax.persistence.*;

@Entity
@Table(name = "ban_sach_chi_tiet")
public class BanSachChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int soLuong = 1;
    private double thanhTien;

    @ManyToOne
    @JoinColumn(name = "sach_id", nullable = false)
    private Sach sach;

    @ManyToOne
    @JoinColumn(name = "banSach_id", nullable = false)
    private BanSach banSach;

    public BanSachChiTiet() {
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

    public BanSach getBanSach() {
        return banSach;
    }

    public void setBanSach(BanSach banSach) {
        this.banSach = banSach;
    }
}
