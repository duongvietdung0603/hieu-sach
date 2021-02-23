package com.thuvien.thuvien.controller;

import com.thuvien.thuvien.Bean.DoanhThuNgay;
import com.thuvien.thuvien.Bean.DoanhThuThang;
import com.thuvien.thuvien.service.DoanhThuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("thong-ke")
public class ThongKeController {
    @Autowired
    private DoanhThuService doanhThuService;

    @GetMapping("/ban-sach-nam")
    public ResponseEntity<List<DoanhThuThang>> doanhThuNam(@RequestParam String nam){
        return new ResponseEntity<>(doanhThuService.banSachNam(nam), HttpStatus.OK);
    }
}
