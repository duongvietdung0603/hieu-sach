package com.thuvien.thuvien.model.NhapSach;

import com.thuvien.thuvien.model.NhaCungCap;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nhap_sach")
public class NhapSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.DATE)
    private Date ngayNhap;

    private int soLuong = 0;
    private double tongTien = 0;
    private byte status = 0; //1 là đã thanh toán

    @ManyToOne
    @JoinColumn(name = "nhaCungCap_id", nullable = false)
    private NhaCungCap nhaCungCap;


    @OneToMany( mappedBy = "nhapSach", cascade = CascadeType.ALL)
    private List<NhapSachChiTiet> nhapSachChiTiet;


    public NhapSach() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
