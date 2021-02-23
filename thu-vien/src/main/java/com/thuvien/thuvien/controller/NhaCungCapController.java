package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.model.NhaCungCap;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.service.NhaCungCapService;
import com.thuvien.thuvien.service.TheLoaiSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("nha-cung-cap")
public class NhaCungCapController {
    @Autowired
    private NhaCungCapService nhaCungCapService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("DanhSach", nhaCungCapService.findAll());
        return "NhaCungCap/danh_sach";
    }

    @GetMapping("/them")
    public String add(Model model){
        model.addAttribute("NhaCungCap", new NhaCungCap());
        model.addAttribute("TheLoai", nhaCungCapService.findAll());
        model.addAttribute("Active","Thêm mới");

        return "NhaCungCap/add";
    }

    @PostMapping("/save")
    public String add_post(@ModelAttribute NhaCungCap nhaCungCap){
        nhaCungCapService.save(nhaCungCap);
        return "redirect:/nha-cung-cap";
    }

    @GetMapping("/sua/{id}")
    public String sua(Model model,@PathVariable Long id){
         model.addAttribute("NhaCungCap", nhaCungCapService.findById(id));
        model.addAttribute("Active","Sửa");

        return "NhaCungCap/add";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(Model model,@PathVariable Long id){
        nhaCungCapService.deleteById(id);
        return "redirect:/nha-cung-cap";
    }

    @GetMapping("/{id}/sach")
    public String sach(Model model,@PathVariable Long id){

        model.addAttribute("DanhSach", nhaCungCapService.sach(id));

        return "NhaCungCap/sach";
    }
}
