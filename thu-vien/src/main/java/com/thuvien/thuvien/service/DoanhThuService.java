package com.thuvien.thuvien.service;

import com.thuvien.thuvien.Bean.DoanhThuNgay;
import com.thuvien.thuvien.Bean.DoanhThuThang;
import com.thuvien.thuvien.model.BanSach.BanSach;
import com.thuvien.thuvien.model.MuonSach.MuonSach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoanhThuService {
    @Autowired
    private MuonSachService muonSachService;
    @Autowired
    private BanSachService banSachService;

    public List<DoanhThuThang> doanhThuThang_muon() {
        List<DoanhThuThang> doanhThuThangs = new ArrayList<>();

        for (int i = 1; i <= 12 ; i++) {
            List<MuonSach> choMuonSaches = muonSachService.doanhThuNam(i);
            DoanhThuThang thang12 = new DoanhThuThang(i);
            int tong= 0;
            for(MuonSach element: choMuonSaches){
                tong = (int) (element.getThanhTien());
            }
            thang12.setData(thang12.getData()+ tong);
            doanhThuThangs.add(thang12);
        }
        return doanhThuThangs;
    }

    public List<DoanhThuThang> doanhThuThang_ban() {
        List<DoanhThuThang> doanhThuThangs = new ArrayList<>();

        for (int i = 1; i <= 12 ; i++) {
            List<BanSach> choMuonSaches = banSachService.doanhThuNam(i);
            DoanhThuThang thang12 = new DoanhThuThang(i);
            int tong= 0;
            for(BanSach element: choMuonSaches){
                tong = (int) (element.getThanhTien());
            }
            thang12.setData(thang12.getData()+ tong);
            doanhThuThangs.add(thang12);
        }
        return doanhThuThangs;
    }

    public List<DoanhThuNgay> doanhThuNgay() {
        List<DoanhThuNgay> doanhThuNgayArrayList = new ArrayList<>();
        DoanhThuNgay doanhThuNgay = new DoanhThuNgay();
        doanhThuNgay.setName("Mượn sách");
        doanhThuNgay.setData(muonSachService.doanhThuNgay());
        doanhThuNgayArrayList.add(doanhThuNgay);

        DoanhThuNgay doanhThuNgayBan = new DoanhThuNgay();
        doanhThuNgayBan.setName("Bán sách");
        doanhThuNgayBan.setData(banSachService.doanhThuNgay());
        doanhThuNgayArrayList.add(doanhThuNgayBan);


        return doanhThuNgayArrayList;
    }

    public  List<DoanhThuThang> banSachNam(String nam) {
        return null;
    }
}
