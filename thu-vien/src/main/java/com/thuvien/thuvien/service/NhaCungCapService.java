package com.thuvien.thuvien.service;

import com.thuvien.thuvien.Bean.SachNhapTuNhaCungCap;
import com.thuvien.thuvien.model.NhaCungCap;
import com.thuvien.thuvien.model.NhapSach.NhapSach;
import com.thuvien.thuvien.model.NhapSach.NhapSachChiTiet;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.model.TheLoaiSach;
import com.thuvien.thuvien.repository.NhaCungCapRepository;
import com.thuvien.thuvien.repository.TheLoaiSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NhaCungCapService {
    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;
    @Autowired
    private NhapSachChiTietService nhapSachChiTietService;
    @Autowired
    private NhapSachService nhapSachService;
    @Autowired
    private SachService sachService;



    public List<NhaCungCap> findAll() {
        return nhaCungCapRepository.findAll();
    }

    public void save(NhaCungCap nhaCungCap) {
        nhaCungCapRepository.save(nhaCungCap);
    }

    public void deleteById(Long id) {
        nhaCungCapRepository.deleteById(id);
    }

    public NhaCungCap findById(Long id) {
        return nhaCungCapRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có nhà cung cấp"));
    }

    public int tongNhaCC() {
        List<NhaCungCap> dichVus = findAll();
        Integer tong=dichVus.size();
        return  tong;
    }

    public List<SachNhapTuNhaCungCap> sach(Long id) {
        List<SachNhapTuNhaCungCap> sachList = new ArrayList<>();
        List<NhapSach> nhapSachList = nhapSachService.findByNhaCungCapIdAndStatus(id,(byte) 1);
        for(NhapSach element: nhapSachList){
            List<NhapSachChiTiet> nhapSachChiTietList = nhapSachChiTietService.findByNhapSachId(element.getId());
            for(NhapSachChiTiet ele: nhapSachChiTietList){
                Sach sach = sachService.findById(ele.getSach().getId());
                SachNhapTuNhaCungCap sachNhapTuNhaCungCap = new SachNhapTuNhaCungCap();
                sachNhapTuNhaCungCap.setMaSach(sach.getId());
                sachNhapTuNhaCungCap.setTenSach(sach.getTenSach());
                sachNhapTuNhaCungCap.setTacGia(sach.getTacGia());
                sachNhapTuNhaCungCap.setSoLuong(ele.getSoLuong());
                sachNhapTuNhaCungCap.setNgayNhap(element.getNgayNhap());
                sachNhapTuNhaCungCap.setTongTien(ele.getThanhTien());
                sachList.add(sachNhapTuNhaCungCap);
             }
         }
        return sachList;
    }
}
