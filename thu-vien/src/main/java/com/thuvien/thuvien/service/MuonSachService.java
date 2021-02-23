package com.thuvien.thuvien.service;

import com.thuvien.thuvien.Bean.DoanhThuThang;
import com.thuvien.thuvien.model.MuonSach.MuonSach;
import com.thuvien.thuvien.model.MuonSach.MuonSachChiTiet;
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
public class MuonSachService {
    @Autowired
    private MuonSachRepository muonSachRepository;
    @Autowired
    private MuonSachChiTietRepository muonSachChiTietRepository;



    public List<MuonSach> findAll() {
        return muonSachRepository.findAll();
    }

    public void save(MuonSach muonSach) {
        muonSachRepository.save(muonSach);
    }

    public List<MuonSach> index(String search) {
        List<MuonSach> muonSachList = findAll();
        for ( MuonSach element : muonSachList) {
            List<MuonSachChiTiet> muonSachChiTiet = muonSachChiTietRepository.findByMuonSachId(element.getId());
            double thanhTien = 0;
            int soLuong = 0;
            byte status =1;
            for ( MuonSachChiTiet ele : muonSachChiTiet) {
                thanhTien = thanhTien + ele.getThanhTien() + ele.getTienPhat();
                soLuong = soLuong +1;
            }
            element.setThanhTien(thanhTien);
            element.setSoLuong(soLuong);

            for ( MuonSachChiTiet ele : muonSachChiTiet) {
                if(ele.getStatus() == 0){
                    status = 0;
                    break;
                }
            }
            element.setStatus(status);
            save(element);
        }

        List<MuonSach> muonSaches = new ArrayList<>();
        if(search.equals("")){
            muonSaches = muonSachRepository.findByStatus((byte) 0);
        }else {
            muonSaches = muonSachRepository.findByKhachHangTenContainingAndStatus(search, (byte) 0);
        }
        return muonSaches;
    }

    public Long save_new(MuonSach muonSach) {
        LocalDate ngayHienTai = LocalDate.now();
        Date ngay = Date.from(ngayHienTai.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        muonSach.setNgayMuon(ngay);
        muonSachRepository.save(muonSach);
        return muonSach.getId();
    }

    public MuonSach findById(Long id) {
        return muonSachRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id muon sách"));
    }

    public void deleteById(Long id) {
        muonSachRepository.deleteById(id);
    }

    public  List<MuonSach>  traSach(String search) {
        List<MuonSach> muonSaches = new ArrayList<>();
        if(search.equals("")){
            muonSaches = muonSachRepository.findByStatus((byte) 1);
        }else {
            muonSaches = muonSachRepository.findByKhachHangTenContainingAndStatus(search, (byte)1);
        }
        return muonSaches;
    }

    public List<DoanhThuThang> doanhThuThang() {
        List<DoanhThuThang> doanhThuThangs = new ArrayList<>();

        for (int i = 1; i <= 12 ; i++) {
            List<MuonSach> choMuonSaches = muonSachRepository.doanhThuNam(i);
            DoanhThuThang thang12 = new DoanhThuThang(i);
            choMuonSaches.forEach(element->{
                int tong = (int) (element.getThanhTien());
                thang12.setData(thang12.getData()+ tong);
            });
            doanhThuThangs.add(thang12);
        }
        return doanhThuThangs;
    }

    public List<MuonSach> doanhThuNam(int i) {
        return muonSachRepository.doanhThuNam(i);
    }

     public Double doanhThuNgay() {
        LocalDate ngayDD = LocalDate.now();
        Date ngayNow = Date.from(ngayDD.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        List<MuonSach> muonSachList = muonSachRepository.findByNgayMuon(ngayNow);

        double tong= 0;
        for(MuonSach hoaDon: muonSachList){
            tong += hoaDon.getThanhTien();
        }
        return tong;
    }

}
