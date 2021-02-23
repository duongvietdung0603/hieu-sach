package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.model.*;
import com.thuvien.thuvien.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
public class InitData {
    @Autowired
    private SachService sachService;
    @Autowired
    private TheLoaiSachService theLoaiSachService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private NhaCungCapService nhaCungCapService;
    @Autowired
    private NhanVienService nhanVienService;


    @PostConstruct
    public void index(){
        TheLoaiSach theLoaiSach1 = new TheLoaiSach("Tiểu thuyết");
        TheLoaiSach theLoaiSach2 = new TheLoaiSach("Truyện tranh");
        TheLoaiSach theLoaiSach3 = new TheLoaiSach("Ngôn tình");
        TheLoaiSach theLoaiSach4 = new TheLoaiSach("Lịch sử");

        theLoaiSachService.save(theLoaiSach1);
        theLoaiSachService.save(theLoaiSach2);
        theLoaiSachService.save(theLoaiSach3);
        theLoaiSachService.save(theLoaiSach4);

        for (int i = 0; i < 10; i++) {
            Sach sach = new Sach("Tên sách "+i,theLoaiSach1);
            sachService.save(sach);
        }

        for (int i = 0; i < 10; i++) {
            KhachHang khachHang = new KhachHang("Khách hàng "+i, "0987654"+i);
            khachHangService.themMoi(khachHang);
        }

        for (int i = 0; i < 10; i++) {
            NhaCungCap nhaCungCap = new NhaCungCap("Nhà cung cấp "+i);
            nhaCungCapService.save(nhaCungCap);
        }

        for (int i = 0; i < 10; i++) {
            NhanVien nhanVien = new NhanVien("Nhân viên "+i, "Nam", "Nhân viên", 1);
            nhanVienService.save(nhanVien);
        }
    }
}
