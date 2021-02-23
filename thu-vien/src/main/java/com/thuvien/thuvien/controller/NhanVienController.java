package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.Bean.DiemDanh;
import com.thuvien.thuvien.model.DiemDanhNhanVien;
import com.thuvien.thuvien.model.KhachHang;
import com.thuvien.thuvien.model.NhanVien;
import com.thuvien.thuvien.service.DiemDanhService;
import com.thuvien.thuvien.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private DiemDanhService diemDanhService;


    @RequestMapping("")
    public String index(Model model,
                        @RequestParam(name = "search", required = false, defaultValue = "") String search){
         model.addAttribute("search", search);
        model.addAttribute("DanhSach", nhanVienService.index(search));
        return "NhanVien/danh_sach";
    }

    @GetMapping("/them")
    public String add(Model model){
        model.addAttribute("NhanVien", new NhanVien());
        model.addAttribute("Active", "Thêm mới");
        return "NhanVien/add";
    }

    @PostMapping("/save")
    public String add_post(@ModelAttribute NhanVien nhanVien){
        nhanVienService.save(nhanVien);
        return "redirect:/nhan-vien";
    }

    @GetMapping("/sua/{id}")
    public String sua(Model model,@PathVariable Long id){
        model.addAttribute("NhanVien", nhanVienService.findById(id));
        model.addAttribute("Active", "Sửa id "+ id);
        return "NhanVien/add";
    }


    @RequestMapping("/xoa/{id}")
    public String xoa(@PathVariable Long id){
        nhanVienService.deleteById(id);
        return "redirect:/nhan-vien";
    }


    @RequestMapping("/diem-danh")
    public String diemDanh(Model model){
        LocalDate ngayDD = LocalDate.now();
        Date ngayDDDate = Date.from(ngayDD.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        List<DiemDanhNhanVien> diemDanhNhanViens = diemDanhService.findByNgayDiemDanh(ngayDDDate);

        if(diemDanhNhanViens.size()>0){
            System.out.println("=====================đã điểm danh==============================");
            model.addAttribute("DaDiemDanh", true);
        }
        System.out.println("=====================chưa điểm danh==============================");


        model.addAttribute("DanhSach", nhanVienService.findAll());
        return "NhanVien/diem-danh";
    }

    @PostMapping("/api/diem-danh/{diemDanh}")
    public ResponseEntity<List<DiemDanh>> diemDanhPost(@PathVariable String diemDanh) {
        return new ResponseEntity<>(nhanVienService.chamCong(diemDanh), HttpStatus.OK);
    }

    @PostMapping("/api/diem-danh-update/{diemDanh}")
    public ResponseEntity<List<DiemDanh>> diemDanhUpdate(@PathVariable String diemDanh){
        return new ResponseEntity<>(nhanVienService.diemDanhUpdate(diemDanh), HttpStatus.OK);
    }

    @GetMapping("/danh-sach-diem-danh")
    public String danhsachDiemdanh(
            @RequestParam(name = "ngay", required = false, defaultValue = "0") String ngay, Model model
    ){
        model.addAttribute("DanhSach", nhanVienService.danhSachDiemDanh(ngay));
        model.addAttribute("ngay", ngay);
        return "NhanVien/danh-sach-diem-danh";
    }
}
