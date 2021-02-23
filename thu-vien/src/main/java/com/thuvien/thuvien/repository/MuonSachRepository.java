package com.thuvien.thuvien.repository;

import com.thuvien.thuvien.model.MuonSach.MuonSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MuonSachRepository extends JpaRepository<MuonSach, Long> {

 List<MuonSach> findByKhachHangTenContainingAndStatus(String search, Byte status);
 List<MuonSach> findByStatus(Byte status);

    @Query("SELECT cms FROM MuonSach cms \n" +
            "WHERE YEAR(cms.ngayMuon) = 2021 AND MONTH(cms.ngayMuon) = ?1 AND cms.status = 1")
     List<MuonSach> doanhThuNam(int i);

    List<MuonSach> findByNgayMuon(Date ngayNow);
}
