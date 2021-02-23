package com.thuvien.thuvien.service;

import com.thuvien.thuvien.model.DiemDanhNhanVien;
import com.thuvien.thuvien.repository.DiemDanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiemDanhService {
    @Autowired
    private DiemDanhRepository diemDanhRepository;


    public void save(DiemDanhNhanVien diemDanhNhanVien) {
        diemDanhRepository.save(diemDanhNhanVien);
    }

    public List<DiemDanhNhanVien> findAll() {
            return diemDanhRepository.findAll();
    }

    public List<DiemDanhNhanVien> findByNgayDiemDanh(Date ngayDDDate) {
        return diemDanhRepository.findByNgayDiemDanhOrderByNhanVienIdAsc(ngayDDDate);
    }

    public DiemDanhNhanVien findById(Long id) {
            return diemDanhRepository.findById(id).orElseThrow(()->new IllegalArgumentException("kh tìm thấy id đuiểm đánh"));
    }
}
