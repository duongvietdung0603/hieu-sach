package com.thuvien.thuvien.repository;

import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.model.TheLoaiSach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheLoaiSachRepository extends JpaRepository<TheLoaiSach, Long> {

    List<TheLoaiSach> findByTenTheLoaiContaining(String search);
}
