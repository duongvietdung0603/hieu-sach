package com.thuvien.thuvien.service;

import com.thuvien.thuvien.model.BanSach.BanSach;
import com.thuvien.thuvien.model.BanSach.BanSachChiTiet;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.repository.BanSachChiTietRepository;
import com.thuvien.thuvien.repository.BanSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanSachChiTietService {
    @Autowired
    private BanSachChiTietRepository banSachChiTietRepository;
    @Autowired
    private SachService sachService;



    public List<BanSachChiTiet> findAll() {
        return banSachChiTietRepository.findAll();
    }

    public void save(BanSachChiTiet banSachChiTiet) {
        banSachChiTietRepository.save(banSachChiTiet);
    }


    public List<BanSachChiTiet> findByBanSachId(Long id) {
        return banSachChiTietRepository.findByBanSachId(id);
    }

    public void save_chi_tiet(BanSachChiTiet banSachChiTiet) {
        Sach sach = sachService.findById(banSachChiTiet.getSach().getId());
        banSachChiTiet.setThanhTien(sach.getGiaBia()*banSachChiTiet.getSoLuong());
        banSachChiTietRepository.save(banSachChiTiet);
    }

    public void deleteById(Long id_chiTiet) {
        banSachChiTietRepository.deleteById(id_chiTiet);
    }

    public BanSachChiTiet findById(Long id_chiTiet) {
        return banSachChiTietRepository.findById(id_chiTiet).orElseThrow(()->new IllegalArgumentException("k có id bán sách chi tiết này"));
    }
}
