package com.thuvien.thuvien.service;

import com.thuvien.thuvien.Bean.DoanhThuThang;
import com.thuvien.thuvien.model.BanSach.BanSach;
import com.thuvien.thuvien.model.BanSach.BanSachChiTiet;
import com.thuvien.thuvien.model.MuonSach.MuonSach;
import com.thuvien.thuvien.model.MuonSach.MuonSachChiTiet;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.repository.BanSachRepository;
import com.thuvien.thuvien.repository.MuonSachChiTietRepository;
import com.thuvien.thuvien.repository.MuonSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BanSachService {
    @Autowired
    private BanSachRepository banSachRepository;
    @Autowired
    private BanSachChiTietService banSachChiTietService;
    @Autowired
    private SachService sachService;



    public List<BanSach> findAll() {
        List<BanSach> banSaches = banSachRepository.findAll();
        for ( BanSach element : banSaches) {
            List<BanSachChiTiet> banSachChiTiets = banSachChiTietService.findByBanSachId(element.getId());
            double thanhTien = 0;
            int soLuong = 0;
            for ( BanSachChiTiet ele : banSachChiTiets) {
                thanhTien = thanhTien + ele.getThanhTien();
                soLuong = soLuong +1;
            }
            element.setThanhTien(thanhTien);
            element.setSoLuong(soLuong);
            save(element);
        }

        return banSachRepository.findAll();
    }

    public void save(BanSach banSach) {
        banSachRepository.save(banSach);
    }


    public Long save_new(BanSach banSach) {
        LocalDate ngayHienTai = LocalDate.now();
        Date ngay = Date.from(ngayHienTai.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        banSach.setNgayBan(ngay);
        banSachRepository.save(banSach);
        return banSach.getId();
    }

    public void deleteById(Long id) {
        banSachRepository.deleteById(id);
    }

    public BanSach findById(Long id) {
        return banSachRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id muon sách"));
    }

    public void thanhToan(Long id) {
        BanSach banSach = findById(id);
        banSach.setStatus((byte) 1);

        List<BanSachChiTiet> banSachChiTiets = banSachChiTietService.findByBanSachId(id);
        for ( BanSachChiTiet ele : banSachChiTiets) {
            Sach sach = sachService.findById(ele.getSach().getId());
            sach.setSoLuong(sach.getSoLuong() - ele.getSoLuong());
            sachService.save(sach);
        }

        save(banSach);
    }

    public List<BanSach> doanhThuNam(int i) {
        return banSachRepository.doanhThuNam(i);
    }

    public double doanhThuNgay() {
        LocalDate ngayDD = LocalDate.now();
        Date ngayNow = Date.from(ngayDD.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        List<BanSach> muonSachList = banSachRepository.findByNgayBan(ngayNow);

        double tong= 0;
        for(BanSach hoaDon: muonSachList){
            tong += hoaDon.getThanhTien();
        }
        return tong;
    }
}
