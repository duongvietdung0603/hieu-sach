package com.thuvien.thuvien.Bean;

import java.util.Date;
import java.util.List;

public class ListDiemDanh {
    private Date ngayDiemDanh;
    private List<DiemDanh> diemDanh;

    public ListDiemDanh() {
    }

    public Date getNgayDiemDanh() {
        return ngayDiemDanh;
    }

    public void setNgayDiemDanh(Date ngayDiemDanh) {
        this.ngayDiemDanh = ngayDiemDanh;
    }

    public List<DiemDanh> getDiemDanh() {
        return diemDanh;
    }

    public void setDiemDanh(List<DiemDanh> diemDanh) {
        this.diemDanh = diemDanh;
    }
}
