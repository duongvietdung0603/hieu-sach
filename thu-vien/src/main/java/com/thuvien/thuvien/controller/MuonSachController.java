package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.model.MuonSach.MuonSach;
import com.thuvien.thuvien.model.MuonSach.MuonSachChiTiet;
import com.thuvien.thuvien.service.KhachHangService;
import com.thuvien.thuvien.service.MuonSachChiTietService;
import com.thuvien.thuvien.service.MuonSachService;
import com.thuvien.thuvien.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
 public class MuonSachController {
    @Autowired
    private MuonSachService muonSachService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private MuonSachChiTietService muonSachChiTietService;
    @Autowired
    private SachService sachService;



    @GetMapping("muon-sach")
    public String index(Model model,
                        @RequestParam(name = "search", required = false, defaultValue = "") String search){
        model.addAttribute("DanhSach", muonSachService.index(search));
        model.addAttribute("KhachHang", khachHangService.findAll());
        model.addAttribute("search", search);
        model.addAttribute("MuonSach", new MuonSach());

        return "MuonSach/danh_sach";
    }


    @PostMapping("muon-sach/save")
    public String add_post(@ModelAttribute MuonSach muonSach){
        Long id =  muonSachService.save_new(muonSach);
        return "redirect:/muon-sach/"+id+"/muon-sach-chi-tiet";
    }

    @GetMapping("muon-sach/xoa/{id}")
    public String xoa(Model model,@PathVariable Long id){
        muonSachService.deleteById(id);
        return "redirect:/muon-sach";
    }

    @GetMapping("muon-sach/{id}/muon-sach-chi-tiet")
    public String hoaDonId(Model model,@PathVariable Long id){
         model.addAttribute("HoaDon", muonSachService.findById(id));
        model.addAttribute("Sach", sachService.findBySoLuongGreaterThan());
        model.addAttribute("MuonSachChiTiet", new MuonSachChiTiet());
        List<MuonSachChiTiet> muonSachChiTiets = muonSachChiTietService.findByMuonSachId(id);
        model.addAttribute("MuonSachChiTiet_List", muonSachChiTiets);

        return "MuonSach/chi_tiet_muon";
    }

    //    mượn sách chi tiết
    @PostMapping("muon-sach-chi-tiet/save/muon-sach/{id}")
    public String add_chi_tiet_new(@ModelAttribute MuonSachChiTiet muonSachChiTiet, @PathVariable Long id ){
        muonSachChiTiet.setMuonSach(muonSachService.findById(id));
        System.out.println("======="+muonSachChiTiet.getMuonSach().getId()+"================");
         sachService.sach_load();
        if(muonSachChiTiet.getSach().getSoLuong() > muonSachChiTiet.getSoLuong()){
            muonSachChiTietService.save_new(muonSachChiTiet);
            return "redirect:/muon-sach/"+id+"/muon-sach-chi-tiet";
        }else {
            return "redirect:/muon-sach/thong-bao/"+id;
        }
     }

    @GetMapping("muon-sach/thong-bao/{id}")
    public String thongBao(Model model,@PathVariable Long id){
        model.addAttribute("id", id);
        return "MuonSach/thong_bao";
    }


    @GetMapping("muon-sach/{id}/muon-sach-chi-tiet/tra-sach/{id_chiTiet}")
    public String tra_sach(Model model,@PathVariable Long id, @PathVariable Long id_chiTiet){
        muonSachChiTietService.traSach(id_chiTiet);
        return "redirect:/muon-sach/"+id+"/muon-sach-chi-tiet";
    }

    @GetMapping("muon-sach/{id}/muon-sach-chi-tiet/xoa/{id_chiTiet}")
    public String xoa_chiTiet(Model model,@PathVariable Long id, @PathVariable Long id_chiTiet){
        muonSachChiTietService.deleteById(id_chiTiet);
        return "redirect:/muon-sach/"+id+"/muon-sach-chi-tiet";
    }

    @GetMapping("muon-sach/{id}/muon-sach-chi-tiet/{id_chiTiet}")
    public String sua_chiTiet(Model model,@PathVariable Long id, @PathVariable Long id_chiTiet){
        model.addAttribute("HoaDon", muonSachService.findById(id));
        model.addAttribute("Sach", sachService.findBySoLuongGreaterThan());
        model.addAttribute("MuonSachChiTiet", muonSachChiTietService.findById(id_chiTiet));
        return "MuonSach/chi_tiet_muon_edit";
    }

    //    lịch sử trả sách


    @GetMapping("lich-su-tra-sach")
    public String LSTraSach(Model model,
                        @RequestParam(name = "search", required = false, defaultValue = "") String search){
        model.addAttribute("DanhSach", muonSachService.traSach(search));
        model.addAttribute("search", search);
        return "MuonSach/lich_su_tra_sach";
    }

    @GetMapping("lich-su-tra-sach/{id}/chi-tiet")
    public String LSTraSachId(Model model,@PathVariable Long id){
        model.addAttribute("HoaDon", muonSachService.findById(id));
        List<MuonSachChiTiet> muonSachChiTiets = muonSachChiTietService.findByMuonSachId(id);
        model.addAttribute("MuonSachChiTiet_List", muonSachChiTiets);

        return "MuonSach/lich_su_tra_sach_chi_tiet";
    }
}
