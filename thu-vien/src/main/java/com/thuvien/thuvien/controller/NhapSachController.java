package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.model.NhapSach.NhapSach;
import com.thuvien.thuvien.model.NhapSach.NhapSachChiTiet;
import com.thuvien.thuvien.service.NhaCungCapService;
import com.thuvien.thuvien.service.NhapSachChiTietService;
import com.thuvien.thuvien.service.NhapSachService;
import com.thuvien.thuvien.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class NhapSachController {
    @Autowired
    private SachService sachService;
    @Autowired
    private NhaCungCapService nhaCungCapService;
    @Autowired
    private NhapSachService nhapSachService;
    @Autowired
    private NhapSachChiTietService nhapSachChiTietService;



    @RequestMapping("nhap-sach")
    public String index(Model model, @RequestParam(name = "search", required = false, defaultValue = "") String search){
        model.addAttribute("DanhSach", nhapSachService.index(search));
        model.addAttribute("NhaCC", nhaCungCapService.findAll());
        model.addAttribute("search", search);
        model.addAttribute("NhapSach", new NhapSach());

        return "NhapSach/danh_sach";
    }

    @RequestMapping("nhap-sach/xoa/{id}")
    public String xoa(@PathVariable Long id){
        nhapSachService.deleteById(id);
        return "redirect:/nhap-sach";
    }

    @GetMapping("nhap-sach/thanh-toan/{id}")
    public String thanhToan(Model model,@PathVariable Long id){
        nhapSachService.thanhToan(id);
        return "redirect:/nhap-sach";
    }

    @PostMapping("nhap-sach/save")
    public String add_post(@ModelAttribute NhapSach nhapSach){
        Long id =  nhapSachService.save_new(nhapSach);
        return "redirect:/nhap-sach/"+id+"/nhap-sach-chi-tiet";
    }

    @GetMapping("nhap-sach/{id}/nhap-sach-chi-tiet")
    public String hoaDonId(Model model,@PathVariable Long id){
        model.addAttribute("HoaDon", nhapSachService.findById(id));
        model.addAttribute("Sach", sachService.findAll());
        model.addAttribute("NhapSachChiTiet", new NhapSachChiTiet());
        List<NhapSachChiTiet> nhapSachChiTiets = nhapSachChiTietService.findByNhapSachId(id);
        model.addAttribute("NhapSachChiTiet_List", nhapSachChiTiets);

        return "NhapSach/chi_tiet_nhap";
    }


    //    nhap sách chi tiết
    @PostMapping("nhap-sach-chi-tiet/save/nhap-sach/{id}")
    public String add_chi_tiet_new(@ModelAttribute NhapSachChiTiet nhapSachChiTiet, @PathVariable Long id ){
        nhapSachChiTiet.setNhapSach(nhapSachService.findById(id));

        nhapSachChiTietService.save_new(nhapSachChiTiet);
        return "redirect:/nhap-sach/"+id+"/nhap-sach-chi-tiet";
    }

    @GetMapping("nhap-sach/{id}/nhap-sach-chi-tiet/xoa/{id_chiTiet}")
    public String xoa_chiTiet(Model model,@PathVariable Long id, @PathVariable Long id_chiTiet){
        nhapSachChiTietService.deleteById(id_chiTiet);
        return "redirect:/nhap-sach/"+id+"/nhap-sach-chi-tiet";
    }

    @GetMapping("nhap-sach/{id}/nhap-sach-chi-tiet/{id_chiTiet}")
    public String sua_chiTiet(Model model,@PathVariable Long id, @PathVariable Long id_chiTiet){
        model.addAttribute("HoaDon", nhapSachService.findById(id));
        model.addAttribute("Sach", sachService.findAll());
        model.addAttribute("NhapSachChiTiet", nhapSachChiTietService.findById(id_chiTiet));

        return "NhapSach/chi_tiet_nhap_edit";
    }

    @GetMapping("nhap-sach/hoa-don/{id}")
    public String hoaDon(Model model,@PathVariable Long id){
        model.addAttribute("HoaDon", nhapSachService.findById(id));
        model.addAttribute("BanSachChiTiet", nhapSachChiTietService.findByNhapSachId(id));
        return "NhapSach/hoa_don_nhap_sach";
    }
}
