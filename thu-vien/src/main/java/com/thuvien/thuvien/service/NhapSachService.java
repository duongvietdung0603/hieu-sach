package com.thuvien.thuvien.service;


import com.thuvien.thuvien.model.BanSach.BanSach;
import com.thuvien.thuvien.model.BanSach.BanSachChiTiet;
import com.thuvien.thuvien.model.NhapSach.NhapSach;
import com.thuvien.thuvien.model.NhapSach.NhapSachChiTiet;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.repository.NhapSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class NhapSachService {
    @Autowired
    private NhapSachRepository nhapSachRepository;
    @Autowired
    private SachService sachService;
    @Autowired
    private NhapSachChiTietService nhapSachChiTietService;




    public void save(NhapSach nhapSach) {
        nhapSachRepository.save(nhapSach);
    }


    public List<NhapSach> findAll() {
        return nhapSachRepository.findAll();
    }

    public NhapSach findById(Long id) {
        return nhapSachRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Không có id loại sách"));
    }

    public void deleteById(Long id) {
        NhapSach nhapSach = findById(id);
        nhapSach.getSoLuong();
        nhapSachRepository.deleteById(id);
    }

    public List<NhapSach> index(String search) {
        List<NhapSach> nhapSaches = findAll();
        for ( NhapSach element : nhapSaches) {
            List<NhapSachChiTiet> nhapSachChiTiets = nhapSachChiTietService.findByNhapSachId(element.getId());
            double thanhTien = 0;
            int soLuong = 0;
            byte status =1;
            for ( NhapSachChiTiet ele : nhapSachChiTiets) {
                thanhTien = thanhTien + ele.getThanhTien();
                soLuong = soLuong +1;
            }
            element.setTongTien(thanhTien);
            element.setSoLuong(soLuong);
            save(element);
        }

        List<NhapSach> muonSaches = new ArrayList<>();
        if(search.equals("")){
            muonSaches = nhapSachRepository.findAll();
        }else {
            muonSaches = nhapSachRepository.findByNhaCungCapTenContaining(search);
        }
        return muonSaches;
    }

    public Long save_new(NhapSach nhapSach) {
        LocalDate ngayHienTai = LocalDate.now();
        Date ngay = Date.from(ngayHienTai.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        nhapSach.setNgayNhap(ngay);
        nhapSachRepository.save(nhapSach);
        return nhapSach.getId();
    }

    public void thanhToan(Long id) {
        NhapSach nhapSach = findById(id);
        nhapSach.setStatus((byte) 1);

        List<NhapSachChiTiet> nhapSachChiTiets = nhapSachChiTietService.findByNhapSachId(id);
        for ( NhapSachChiTiet element : nhapSachChiTiets) {
            Sach sach = sachService.findById(element.getSach().getId());

            sach.setSoLuong(sach.getSoLuong() + element.getSoLuong());
            sachService.save(sach);
        }

        save(nhapSach);
    }

    public List<NhapSach> findByNhaCungCapId(Long id) {
        return nhapSachRepository.findByNhaCungCapId(id);
    }

    public List<NhapSach> findByNhaCungCapIdAndStatus(Long id, byte b) {
        return nhapSachRepository.findByNhaCungCapIdAndStatus(id,b);
    }
}
