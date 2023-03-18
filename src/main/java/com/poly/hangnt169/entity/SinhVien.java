package com.poly.hangnt169.entity;

/**
 * @author hangnt169
 */
public class SinhVien {
//    Tạo class SinhVien gồm các thuộc tính: maSV-String, tenSV-String, tuoi-int, diaChi-String,
//    gioiTinh-boolean và các phương thức contructor, getter, setter.

    private String maSV;
    private String tenSV;
    private int tuoi;
    private String diaChi;
    private boolean gioiTinh;

    public SinhVien() {
    }

    public SinhVien(String maSV, String tenSV, int tuoi, String diaChi, boolean gioiTinh) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.tuoi = tuoi;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
