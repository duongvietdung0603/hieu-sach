package com.thuvien.thuvien.repository;

import com.thuvien.thuvien.model.BanSach.BanSach;
import com.thuvien.thuvien.model.MuonSach.MuonSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BanSachRepository extends JpaRepository<BanSach, Long> {

     @Query("SELECT cms FROM BanSach cms \n" +
            "WHERE YEAR(cms.ngayBan) = 2021 AND MONTH(cms.ngayBan) = ?1 AND cms.status = 1")
    List<BanSach> doanhThuNam(int i);

    List<BanSach> findByNgayBan(Date ngayNow);
}
