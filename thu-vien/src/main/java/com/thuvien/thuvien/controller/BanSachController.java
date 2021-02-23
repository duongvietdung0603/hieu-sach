package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.model.BanSach.BanSach;
import com.thuvien.thuvien.model.BanSach.BanSachChiTiet;
import com.thuvien.thuvien.model.MuonSach.MuonSach;
import com.thuvien.thuvien.model.MuonSach.MuonSachChiTiet;
import com.thuvien.thuvien.service.BanSachChiTietService;
import com.thuvien.thuvien.service.BanSachService;
import com.thuvien.thuvien.service.NhaCungCapService;
import com.thuvien.thuvien.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class BanSachController {
    @Autowired
    private BanSachService banSachService;
    @Autowired
    private BanSachChiTietService banSachChiTietService;
    @Autowired
    private SachService sachService;


    @GetMapping("ban-sach")
    public String index(Model model){
        model.addAttribute("DanhSach", banSachService.findAll());
         model.addAttribute("BanSach", new BanSach());
        return "BanSach/danh_sach";
    }

    @PostMapping("ban-sach/save")
    public String add_post(@ModelAttribute BanSach banSach){
        Long id =  banSachService.save_new(banSach);
        return "redirect:/ban-sach/"+id+"/ban-sach-chi-tiet";
    }

    @GetMapping("ban-sach/xoa/{id}")
    public String xoa(Model model,@PathVariable Long id){
        banSachService.deleteById(id);
        return "redirect:/ban-sach";
    }

    @GetMapping("ban-sach/thanh-toan/{id}")
    public String thanhToan(Model model,@PathVariable Long id){
        banSachService.thanhToan(id);
        return "redirect:/ban-sach";
    }

    @GetMapping("ban-sach/{id}/ban-sach-chi-tiet")
    public String hoaDonId(Model model,@PathVariable Long id){
        model.addAttribute("HoaDon", banSachService.findById(id));
        model.addAttribute("Sach", sachService.findBySoLuongGreaterThan());
        model.addAttribute("BanSachChiTiet", new BanSachChiTiet());
        List<BanSachChiTiet> banSachChiTiet = banSachChiTietService.findByBanSachId(id);
        model.addAttribute("BanSachChiTiet_List", banSachChiTiet);

        return "BanSach/chi_tiet_ban";
    }

    //    bán sách chi tiết
    @PostMapping("ban-sach-chi-tiet/save/ban-sach/{id}")
    public String add_chi_tiet_new(@ModelAttribute BanSachChiTiet banSachChiTiet, @PathVariable Long id ){
        banSachChiTiet.setBanSach(banSachService.findById(id));
         sachService.sach_load();
        if(banSachChiTiet.getSach().getSoLuong() > banSachChiTiet.getSoLuong()){
            banSachChiTietService.save_chi_tiet(banSachChiTiet);
            return "redirect:/ban-sach/"+id+"/ban-sach-chi-tiet";
        }else {
            return "redirect:/ban-sach/thong-bao/"+id;
        }
    }

    @GetMapping("ban-sach/thong-bao/{id}")
    public String thongBao(Model model,@PathVariable Long id){
        model.addAttribute("id", id);
        return "BanSach/thong_bao";
    }

    @GetMapping("ban-sach/{id}/ban-sach-chi-tiet/xoa/{id_chiTiet}")
    public String xoa_chiTiet(Model model,@PathVariable Long id, @PathVariable Long id_chiTiet){
        banSachChiTietService.deleteById(id_chiTiet);
        return "redirect:/ban-sach/"+id+"/ban-sach-chi-tiet";
    }

    @GetMapping("ban-sach/{id}/ban-sach-chi-tiet/{id_chiTiet}")
    public String sua_chiTiet(Model model,@PathVariable Long id, @PathVariable Long id_chiTiet){
        model.addAttribute("HoaDon", banSachService.findById(id));
        model.addAttribute("Sach", sachService.findAll());
        model.addAttribute("BanSachChiTiet", banSachChiTietService.findById(id_chiTiet));
        return "BanSach/chi_tiet_ban_edit";
    }


    @GetMapping("ban-sach/hoa-don/{id}")
    public String hoaDon(Model model,@PathVariable Long id){
        model.addAttribute("HoaDon", banSachService.findById(id));
        model.addAttribute("BanSachChiTiet", banSachChiTietService.findByBanSachId(id));
        return "BanSach/hoa_don_ban_sach";
    }
}
