package com.thuvien.thuvien.model.BanSach;

import com.thuvien.thuvien.model.NhapSach.NhapSachChiTiet;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ban_sach")
public class BanSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date ngayBan;

    private int soLuong = 0;
    private double thanhTien = 0;
    private byte status = 0; //1 là đã thanh toán, k thể sửa được nữa

    @OneToMany( mappedBy = "banSach", cascade = CascadeType.ALL)
    private List<BanSachChiTiet> banSachChiTiet;

    public BanSach() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
