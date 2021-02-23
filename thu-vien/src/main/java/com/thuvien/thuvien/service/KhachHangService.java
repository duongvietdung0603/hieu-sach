package com.thuvien.thuvien.service;

import com.thuvien.thuvien.model.KhachHang;
import com.thuvien.thuvien.model.Sach;
import com.thuvien.thuvien.repository.KhachHangRepository;
import com.thuvien.thuvien.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> index(String search) {
        List<KhachHang> khachHangs = new ArrayList<>();
        if(search.equals("")){
            khachHangs = findAll();
        }else {
            khachHangs = khachHangRepository.findByTenContaining(search);
        }
        return khachHangs;
     }

    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    public void save(KhachHang khachHangs) {
        khachHangRepository.save(khachHangs);
    }

    public KhachHang findById(Long id) {
        return khachHangRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id sách"));
    }

    public void deleteById(Long id) {
        khachHangRepository.deleteById(id);
    }

    public void themMoi(KhachHang khachHang) {
        save(khachHang);
    }


    public int tongKhachHang() {
        List<KhachHang> dichVus = findAll();
        Integer tong=dichVus.size();
        return  tong;
    }
}
