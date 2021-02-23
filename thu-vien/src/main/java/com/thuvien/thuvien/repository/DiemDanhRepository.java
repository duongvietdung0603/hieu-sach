package com.thuvien.thuvien.repository;

import com.thuvien.thuvien.model.DiemDanhNhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DiemDanhRepository extends JpaRepository<DiemDanhNhanVien, Long> {
    List<DiemDanhNhanVien> findByNgayDiemDanhOrderByNhanVienIdAsc(Date ngayDDDate);
}
