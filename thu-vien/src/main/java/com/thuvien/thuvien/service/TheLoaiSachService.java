package com.thuvien.thuvien.service;

import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.model.TheLoaiSach;
 import com.thuvien.thuvien.repository.TheLoaiSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheLoaiSachService {
    @Autowired
    private TheLoaiSachRepository theLoaiSachRepository;

    public List<TheLoaiSach> findAll() {
        return theLoaiSachRepository.findAll();
    }

    public void save(TheLoaiSach theLoaiSach) {
        theLoaiSachRepository.save(theLoaiSach);
    }

    public void deleteById(Long id) {
        theLoaiSachRepository.deleteById(id);
    }

    public TheLoaiSach findById(Long id) {
        return theLoaiSachRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id này"));
    }
}
