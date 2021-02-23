package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.Bean.DoanhThuNgay;
import com.thuvien.thuvien.Bean.DoanhThuThang;
import com.thuvien.thuvien.model.*;
import com.thuvien.thuvien.service.*;
import com.sun.xml.internal.ws.streaming.XMLStreamWriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
@CrossOrigin
public class MainController {
    @Autowired
    private SachService sachService;
    @Autowired
    private TheLoaiSachService loaiSachService;
    @Autowired
    private NhaCungCapService nhaCungCapService;
    @Autowired
    private NhapSachService nhapSachService;

    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private MuonSachService muonSachService;
    @Autowired
    private DoanhThuService doanhThuService;


    @GetMapping("/")
    public String index (){
        return "redirect:/dashboard";
    }

    @GetMapping("/403")
    public String accessDenied() {
         return "403";
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(name = "error",required = false,defaultValue = "0") String error, Model model) {
        if(!error.equals("0")){
            model.addAttribute("err", true);
        }
        return "dang_nhap";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("Sach", sachService.tongSach());
        model.addAttribute("KhachHang", khachHangService.tongKhachHang());
        model.addAttribute("NhanVien", nhanVienService.tongNhanVien());
        model.addAttribute("NhaCC", nhaCungCapService.tongNhaCC());
        model.addAttribute("DanhSach", nhapSachService.findAll());

        return "dashboard";
    }

    @GetMapping("/doanh-thu-nam/muon-sach")
    public ResponseEntity<List<DoanhThuThang>> doanhThu1(){
        return new ResponseEntity<>(doanhThuService.doanhThuThang_muon(), HttpStatus.OK);
    }

    @GetMapping("/doanh-thu-nam/ban-sach")
    public ResponseEntity<List<DoanhThuThang>> doanhThu2(){
        return new ResponseEntity<>(doanhThuService.doanhThuThang_ban(), HttpStatus.OK);
    }

    @GetMapping("/doanh-thu-ngay")
    public ResponseEntity<List<DoanhThuNgay>> doanhThuNgay(){
        return new ResponseEntity<>(doanhThuService.doanhThuNgay(), HttpStatus.OK);
    }

    @GetMapping("/dashboard1")
    public String dashboard1(Model model) {
        model.addAttribute("Sach", sachService.tongSach());
        model.addAttribute("KhachHang", khachHangService.tongKhachHang());
        model.addAttribute("NhanVien", nhanVienService.tongNhanVien());
        model.addAttribute("NhaCC", nhaCungCapService.tongNhaCC());
        model.addAttribute("DanhSach", nhapSachService.findAll());

        return "dashboard";
    }
}
