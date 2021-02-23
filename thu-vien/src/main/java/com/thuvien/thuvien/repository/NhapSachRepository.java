package com.thuvien.thuvien.repository;


 import com.thuvien.thuvien.model.NhapSach.NhapSach;
 import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.List;

public interface NhapSachRepository extends JpaRepository<NhapSach, Long> {
    List<NhapSach> findByNhaCungCapTenContaining(String search);

    List<NhapSach> findByNhaCungCapId(Long id);

    List<NhapSach> findByNhaCungCapIdAndStatus(Long id, byte b);
}
