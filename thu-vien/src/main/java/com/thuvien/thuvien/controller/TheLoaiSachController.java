package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.model.TheLoaiSach;
import com.thuvien.thuvien.service.SachService;
import com.thuvien.thuvien.service.TheLoaiSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("loai-sach")
public class TheLoaiSachController {
    @Autowired
    private TheLoaiSachService theLoaiSachService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("DanhSach", theLoaiSachService.findAll());
         return "Sach/loai_sach";
    }


    @GetMapping("/xoa/{id}")
    public String xoa(Model model,@PathVariable Long id){
        theLoaiSachService.deleteById(id);
        return "redirect:/loai-sach";
    }

    @GetMapping("/them")
    public String add(Model model){
        model.addAttribute("TheLoai", new TheLoaiSach());
        model.addAttribute("Active","Thêm mới");

        return "Sach/loai_sach_add";
    }

    @PostMapping("/save")
    public String add_post(@ModelAttribute TheLoaiSach theLoaiSach){
        theLoaiSachService.save(theLoaiSach);
        return "redirect:/loai-sach";
    }

    @GetMapping("/sua/{id}")
    public String sua(Model model,@PathVariable Long id){
        model.addAttribute("TheLoai", theLoaiSachService.findById(id));
        model.addAttribute("Active","Sửa id "+id);

        return "Sach/loai_sach_add";
    }


}
