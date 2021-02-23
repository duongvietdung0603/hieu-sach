package com.thuvien.thuvien.Bean;

public class DiemDanh {
    private Long id;
    private int status;

    public DiemDanh() {
    }

    public DiemDanh(Long id, int status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
