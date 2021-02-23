package com.thuvien.thuvien.service;


import com.thuvien.thuvien.model.NhapSach.NhapSachChiTiet;
import com.thuvien.thuvien.repository.NhapSachChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NhapSachChiTietService {
    @Autowired
    private NhapSachChiTietRepository nhapSachChiTietRepository;
    @Autowired
    private SachService sachService;


    public void save(NhapSachChiTiet nhapSachChiTiet) {
        nhapSachChiTietRepository.save(nhapSachChiTiet);
    }


    public List<NhapSachChiTiet> findAll() {
        return nhapSachChiTietRepository.findAll();
    }

    public NhapSachChiTiet findById(Long id) {
        return nhapSachChiTietRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Không có id loại sách"));
    }

    public void deleteById(Long id) {
        nhapSachChiTietRepository.deleteById(id);
    }

    public Long save_new(NhapSachChiTiet nhapSachChiTiet) {
        nhapSachChiTietRepository.save(nhapSachChiTiet);
        return nhapSachChiTiet.getId();
    }

    public List<NhapSachChiTiet> findByNhapSachId(Long id) {
        return nhapSachChiTietRepository.findByNhapSachId(id);
     }

    public List<NhapSachChiTiet> findBySachId(Long id) {
        return nhapSachChiTietRepository.findBySachId(id);
    }



}
