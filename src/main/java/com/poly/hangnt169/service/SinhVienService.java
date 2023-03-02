package com.poly.hangnt169.service;

import com.poly.hangnt169.entity.SinhVien;

import java.util.List;

/**
 * @author hangnt169
 */
public interface SinhVienService {

    List<SinhVien> searchVien(List<SinhVien> sinhViens, String name);

    boolean updateSinhVien(String mssv, List<SinhVien> sinhViens, SinhVien newSinhVien);

    boolean deleteSinhVien(String mssv, List<SinhVien> sinhViens);

    void addSinhVien(SinhVien sinhVien, List<SinhVien> sinhViens);

    int findIndexSinhVien(List<SinhVien> sinhViens, String mssv);

}
