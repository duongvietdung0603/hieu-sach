package com.thuvien.thuvien.repository;

import com.thuvien.thuvien.model.KhachHang;
import com.thuvien.thuvien.model.Sach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

    List<KhachHang> findByTenContaining(String search);
}
