package com.thuvien.thuvien.repository;

import com.thuvien.thuvien.model.BanSach.BanSach;
import com.thuvien.thuvien.model.BanSach.BanSachChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BanSachChiTietRepository extends JpaRepository<BanSachChiTiet, Long> {

    List<BanSachChiTiet> findByBanSachId(Long id);
}
