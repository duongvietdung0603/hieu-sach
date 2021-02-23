package com.thuvien.thuvien.service;

import com.thuvien.thuvien.model.MuonSach.MuonSachChiTiet;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.repository.MuonSachChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class MuonSachChiTietService {
    @Autowired
    private MuonSachChiTietRepository muonSachChiTietRepository;
    @Autowired
    private SachService sachService;
    @Autowired
    private MuonSachService muonSachService;


    public List<MuonSachChiTiet> findAll() {
        return muonSachChiTietRepository.findAll();
    }

    public void save(MuonSachChiTiet muonSach) {
        muonSachChiTietRepository.save(muonSach);
    }

    public MuonSachChiTiet findById(Long id) {
        return muonSachChiTietRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id muon sách"));
    }

    public void save_new(MuonSachChiTiet muonSachChiTiet) {
        LocalDate ngayHienTai = LocalDate.now();
        Date ngay = Date.from(ngayHienTai.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        muonSachChiTiet.setNgayMuon(ngay);


        Date ngayTra = muonSachChiTiet.getNgayTra();
        LocalDate ngayTraLocalDate = Instant.ofEpochMilli(ngayTra.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        Period soNgayChoMuon = Period.between(ngayHienTai,ngayTraLocalDate);

        Sach sach = sachService.findById(muonSachChiTiet.getSach().getId());
        muonSachChiTiet.setThanhTien(muonSachChiTiet.getSoLuong() * sach.getGiaMuon() * soNgayChoMuon.getDays());

        muonSachChiTietRepository.save(muonSachChiTiet);
    }


    public MuonSachChiTiet test(MuonSachChiTiet muonSachChiTiet) {
        muonSachChiTietRepository.save(muonSachChiTiet);
        return muonSachChiTiet;
    }

    public List<MuonSachChiTiet> findByMuonSachId(Long id) {
        return muonSachChiTietRepository.findByMuonSachId(id);
    }

    public void deleteById(Long id_chiTiet) {
        muonSachChiTietRepository.deleteById(id_chiTiet);
    }

    public void traSach(Long id_chiTiet) {
        MuonSachChiTiet muonSachChiTiet = findById(id_chiTiet);

        //ngày trả đã đăng kí
        Date ngayDuKienTra = muonSachChiTiet.getNgayTra();
        LocalDate ngayDuKienTraLocalDate = Instant.ofEpochMilli(ngayDuKienTra.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        //ngày trả thực tế
        LocalDate ngayTraThucTeLocalDate = LocalDate.now();
        Date ngayTraThucTe = Date.from(ngayTraThucTeLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        //tính số ngày quá hạn
        Period period = Period.between(ngayDuKienTraLocalDate,ngayTraThucTeLocalDate);

        Sach sach = sachService.findById(muonSachChiTiet.getSach().getId());
        double tienPhat = sach.getGiaMuon() * period.getDays();

        if(tienPhat > 0){
            muonSachChiTiet.setTienPhat(tienPhat);
            muonSachChiTiet.setNgayTraThucTe(ngayTraThucTe);
            muonSachChiTiet.setStatus((byte) 1);
        }else  {
            muonSachChiTiet.setTienPhat(0);
            muonSachChiTiet.setNgayTraThucTe(ngayTraThucTe);
            muonSachChiTiet.setStatus((byte) 1);
        }
        save(muonSachChiTiet);
    }
}
