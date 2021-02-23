package com.thuvien.thuvien.service;

import com.thuvien.thuvien.model.BanSach.BanSach;
import com.thuvien.thuvien.model.BanSach.BanSachChiTiet;
import com.thuvien.thuvien.model.MuonSach.MuonSachChiTiet;
import com.thuvien.thuvien.model.NhapSach.NhapSach;
import com.thuvien.thuvien.model.NhapSach.NhapSachChiTiet;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.repository.BanSachRepository;
import com.thuvien.thuvien.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SachService {
    @Autowired
    private SachRepository sachRepository;
    @Autowired
    private NhapSachChiTietService nhapSachChiTietService;
    @Autowired
    private NhapSachService nhapSachService;
    @Autowired
    private BanSachRepository banSachRepository;
    @Autowired
    private BanSachChiTietService banSachChiTietService;
    @Autowired
    private MuonSachChiTietService muonSachChiTietService;

    public List<Sach> index(String search) {
        sach_load();
        List<Sach> saches = new ArrayList<>();
        if(search.equals("")){
            saches = findAll();
        }else {
            saches = sachRepository.findByTenSachContaining(search);
        }
        return saches;
     }

    public List<Sach> findAll() {
        return sachRepository.findAll();
    }

    public void save(Sach sach) {
        double giaMuon = sach.getGiaBia() * 2/100;
        sach.setGiaMuon(giaMuon);
        sachRepository.save(sach);
    }

    public Sach findById(Long id) {
        return sachRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id sách"));
    }

    public void deleteById(Long id) {
        sachRepository.deleteById(id);
    }

    public int tongSach() {
        List<Sach> dichVus = findAll();
        Integer tong=dichVus.size();
        return  tong;
    }

    public void sach_load(){
        List<Sach> saches = findAll();
        for ( Sach ele : saches) {
            ele.setSoLuong(0);
            save(ele);
        }

        List<NhapSachChiTiet> nhapSaches = nhapSachChiTietService.findAll();
        for ( NhapSachChiTiet ele : nhapSaches) {
            if(ele.getNhapSach().getStatus() == 1){
                Sach sach = findById(ele.getSach().getId());
                sach.setSoLuong(sach.getSoLuong() + ele.getSoLuong());
                save(sach);
            }
        }

        List<MuonSachChiTiet> muonSachChiTiets = muonSachChiTietService.findAll();
        for ( MuonSachChiTiet ele : muonSachChiTiets) {
            if(ele.getStatus()==0){
                Sach sach = findById(ele.getSach().getId());
                sach.setSoLuong(sach.getSoLuong() - ele.getSoLuong());
                save(sach);
            }
        }

        List<BanSachChiTiet> banSachChiTiets = banSachChiTietService.findAll();
        for ( BanSachChiTiet ele : banSachChiTiets) {
            if ((ele.getBanSach().getStatus() == 1)){
                Sach sach = findById(ele.getSach().getId());
                sach.setSoLuong(sach.getSoLuong() - ele.getSoLuong());
                save(sach);
            }
        }
    }


    public List<Sach> findBySoLuongGreaterThan() {
        return sachRepository.findBySoLuongGreaterThan(0);
    }
}
