package com.thuvien.thuvien.service;

import com.thuvien.thuvien.Bean.DiemDanh;
import com.thuvien.thuvien.model.DiemDanhNhanVien;
import com.thuvien.thuvien.model.NhanVien;
import com.thuvien.thuvien.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private DiemDanhService diemDanhService;

    public List<NhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    public void save(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    public NhanVien edit(Long id, NhanVien nhanVien) {
        nhanVien.setId(id);
        nhanVienRepository.save(nhanVien);

        return nhanVien;
    }

    public NhanVien findById(Long id) {
        return nhanVienRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Không có id khách hàng"));
    }

    public NhanVien add(NhanVien nhanVien) {
        save(nhanVien);
        return nhanVien;
    }

    public void deleteById(Long id) {
        nhanVienRepository.deleteById(id);
    }

    public List<DiemDanh> chamCong(String diemDanh) {

        System.out.println("=============================");
        System.out.println(diemDanh);
        String[] ItemDiemDanh = diemDanh.split("-");
        List<DiemDanh> danhList = new ArrayList<>();

        for(String element:ItemDiemDanh){
            System.out.println(element);
            String[] tr = element.split("_");
            System.out.println("=======");
            System.out.println(tr[0]);
            System.out.println(tr[1]);
            Long id = Long.valueOf(tr[0]);
            int status = Integer.parseInt(tr[1]);
            DiemDanh diemDanh1 = new DiemDanh(id,status);
            danhList.add(diemDanh1);
         }

        LocalDate ngayDD = LocalDate.now();
        Date ngayDDDate = Date.from(ngayDD.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        danhList.forEach((element) -> {
            NhanVien nhanVien = findById(element.getId());
            DiemDanhNhanVien diemDanhNhanVien = new DiemDanhNhanVien(ngayDDDate, element.getStatus(),nhanVien );
            diemDanhService.save(diemDanhNhanVien);
        });


        return danhList;
    }

    public List<DiemDanhNhanVien> danhSachDiemDanh(String ngay) {
        if(ngay.equals("0")){
           return diemDanhService.findAll();
        }else {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate ngayDD = LocalDate.parse(ngay,df);
            Date ngayDDDate = Date.from(ngayDD.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
          return   diemDanhService.findByNgayDiemDanh(ngayDDDate);
        }
     }


    public List<DiemDanh> diemDanhUpdate(String diemDanh) {

        System.out.println("=============================");
        System.out.println(diemDanh);
        String[] ItemDiemDanh = diemDanh.split("-");
        List<DiemDanh> danhList = new ArrayList<>();

        for(String element:ItemDiemDanh){
            System.out.println(element);
            String[] tr = element.split("_");
            System.out.println("=======");
            System.out.println(tr[0]);
            System.out.println(tr[1]);
            Long id = Long.valueOf(tr[0]);
            int status = Integer.parseInt(tr[1]);
            DiemDanh diemDanh1 = new DiemDanh(id,status);
            danhList.add(diemDanh1);
        }

        danhList.forEach((element) -> {
             DiemDanhNhanVien diemDanhNhanVien = diemDanhService.findById(element.getId());
             diemDanhNhanVien.setStatus(element.getStatus());
             diemDanhService.save(diemDanhNhanVien);
        });


        return danhList;
    }

    public int tongNhanVien() {
        List<NhanVien> dichVus = findAll();
        Integer tong=dichVus.size();
        return  tong;
    }

    public List<NhanVien> index(String search) {
        List<NhanVien> nhanViens = findAll();
        if(search.equals("")){
            nhanViens = findAll();
        }else {
            nhanViens =  nhanVienRepository.findByHoTenContaining(search);
        }
        return nhanViens;
    }
}
