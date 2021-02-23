package com.thuvien.thuvien.repository;

import com.thuvien.thuvien.model.NhapSach.NhapSachChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhapSachChiTietRepository extends JpaRepository<NhapSachChiTiet, Long> {

    List<NhapSachChiTiet> findByNhapSachId(Long id);


    List<NhapSachChiTiet> findBySachId(Long id);
}
