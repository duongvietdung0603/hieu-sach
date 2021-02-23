package com.thuvien.thuvien.repository;


 import com.thuvien.thuvien.model.NhanVien;
 import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien,Long> {
    List<NhanVien> findByHoTenContaining(String search);
}
