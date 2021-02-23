package com.thuvien.thuvien.model.MuonSach;


import com.thuvien.thuvien.model.Sach;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "muon_sach_chi_tiet")
public class MuonSachChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date ngayMuon;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ngayTra;

    private int soLuong = 1;
    private double thanhTien;

    @Temporal(TemporalType.DATE)
    private Date ngayTraThucTe;

    private double tienPhat = 0;

    private byte status = 0; //1 là đã trả


    @ManyToOne
    @JoinColumn(name = "sach_id", nullable = false)
    private Sach sach;

    @ManyToOne
    @JoinColumn(name = "muonSach_id", nullable = false)
    private MuonSach muonSach;

    public MuonSachChiTiet() {
    }

    public MuonSachChiTiet(MuonSach muonSach) {
        this.muonSach = muonSach;
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

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
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

    public MuonSach getMuonSach() {
        return muonSach;
    }

    public void setMuonSach(MuonSach muonSach) {
        this.muonSach = muonSach;
    }

    public Date getNgayTraThucTe() {
        return ngayTraThucTe;
    }

    public void setNgayTraThucTe(Date ngayTraThucTe) {
        this.ngayTraThucTe = ngayTraThucTe;
    }

    public double getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(double tienPhat) {
        this.tienPhat = tienPhat;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
