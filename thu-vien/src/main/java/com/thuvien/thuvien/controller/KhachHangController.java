package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.model.KhachHang;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.service.KhachHangService;
import com.thuvien.thuvien.service.SachService;
import com.thuvien.thuvien.service.TheLoaiSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;



    @GetMapping("")
    public String index(Model model,
                        @RequestParam(name = "search", required = false, defaultValue = "") String search){
        model.addAttribute("DanhSach", khachHangService.index(search));
        model.addAttribute("search", search);
        return "KhachHang/danh_sach";
    }

    @GetMapping("/them")
    public String add(Model model){
        model.addAttribute("KhachHang", new KhachHang());
        model.addAttribute("Active","Thêm mới");

        return "KhachHang/add";
    }

    @PostMapping("/save")
    public String add_post(@ModelAttribute KhachHang khachHang){
        khachHangService.save(khachHang);
        return "redirect:/khach-hang";
    }

    @GetMapping("/sua/{id}")
    public String sua(Model model,@PathVariable Long id){
        model.addAttribute("KhachHang", khachHangService.findById(id));
        model.addAttribute("Active","Sửa id "+id);
        return "KhachHang/add";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(Model model,@PathVariable Long id){
        khachHangService.deleteById(id);
        return "redirect:/khach-hang";
    }

}
