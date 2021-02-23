package com.thuvien.thuvien.repository;

import com.thuvien.thuvien.model.NhaCungCap;
import com.thuvien.thuvien.model.TheLoaiSach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {

    List<TheLoaiSach> findByTenContaining(String search);
}
