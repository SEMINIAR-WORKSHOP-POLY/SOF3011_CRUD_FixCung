package com.poly.hangnt169.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hangnt169
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SinhVien {

    private String mssv;
    private String ten;
    private Integer tuoi;
    private String diaChi;

    private Boolean gioiTinh;

}
