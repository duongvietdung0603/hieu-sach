package com.thuvien.thuvien.repository;

  import com.thuvien.thuvien.model.Sach;
 import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.List;

public interface SachRepository  extends JpaRepository<Sach, Long> {

    List<Sach> findByTenSachContaining(String search);

    List<Sach> findBySoLuongGreaterThan(int i);
}
