package com.thuvien.thuvien.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "the_loai_sach")
public class TheLoaiSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenTheLoai;

    @OneToMany( mappedBy = "theLoaiSach", cascade = CascadeType.ALL)
    private List<Sach> sachList;

    public TheLoaiSach(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public TheLoaiSach() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}
