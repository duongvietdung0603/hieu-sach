package com.thuvien.thuvien.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "diem_danh_nhan_vien")
public class DiemDanhNhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date ngayDiemDanh;

    private int status; //0 là đúng giờ, 1 là chậm, 2 là vắng

    @ManyToOne
    @JoinColumn(name = "nhanVien_id", nullable = false)
    private NhanVien nhanVien;

    public DiemDanhNhanVien() {
    }

    public DiemDanhNhanVien(Date ngayDiemDanh, int status, NhanVien nhanVien) {
        this.ngayDiemDanh = ngayDiemDanh;
        this.status = status;
        this.nhanVien = nhanVien;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgayDiemDanh() {
        return ngayDiemDanh;
    }

    public void setNgayDiemDanh(Date ngayDiemDanh) {
        this.ngayDiemDanh = ngayDiemDanh;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}
