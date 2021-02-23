package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.service.SachService;
import com.thuvien.thuvien.service.TheLoaiSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("sach")
public class SachController {
    @Autowired
    private SachService sachService;
    @Autowired
    private TheLoaiSachService theLoaiSachService;


    @GetMapping("")
    public String index(Model model,
                        @RequestParam(name = "search", required = false, defaultValue = "") String search){
        model.addAttribute("DanhSach", sachService.index(search));
        model.addAttribute("search", search);
        return "Sach/danh_sach";
    }

    @GetMapping("/them")
    public String add(Model model){
        model.addAttribute("Sach", new Sach());
        model.addAttribute("TheLoai", theLoaiSachService.findAll());
        model.addAttribute("Active","Thêm mới");

        return "Sach/add";
    }

    @PostMapping("/save")
    public String add_post(@ModelAttribute Sach sach){
         sachService.save(sach);
        return "redirect:/sach";
    }

    @GetMapping("/sua/{id}")
    public String sua(Model model,@PathVariable Long id){
        model.addAttribute("Sach", sachService.findById(id));
        model.addAttribute("TheLoai", theLoaiSachService.findAll());
        model.addAttribute("Active","Sửa id "+id);

        return "Sach/add";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(Model model,@PathVariable Long id){
         sachService.deleteById(id);
        return "redirect:/sach";
    }
}
