package com.poly.hangnt169.controller;

import com.poly.hangnt169.entity.SinhVien;
import com.poly.hangnt169.service.SinhVienService;
import com.poly.hangnt169.service.impl.SinhVienServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hangnt169
 */
@WebServlet(name = "SinhVienServlet", value = {
        "/sinh-vien/hien-thi",  // GET
        "/sinh-vien/view-add",    // GET
        "/sinh-vien/add",        // POST
        "/sinh-vien/view-update",// GET
        "/sinh-vien/update",    // POST
        "/sinh-vien/delete",    // GET
        "/sinh-vien/detail",    // GET
        "/sinh-vien/search"     // GET
})
public class SinhVienController extends HttpServlet {

    private List<SinhVien> sinhViens = new ArrayList<>();
    private SinhVienService sinhVienService = new SinhVienServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiSinhVien(request, response);
        } else if (uri.contains("detail")) {
            this.detailSinhVien(request, response);
        } else if (uri.contains("view-add")) {
            this.hienThiAdd(request, response);
        } else if (uri.contains("view-update")) {
            this.hienThiUpdate(request, response);
        } else if (uri.contains("delete")) {
            this.deleteSinhVien(request, response);
        } else {
            this.hienThiSinhVien(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            this.addSinhVien(request, response);
        } else {
            this.updateSinhVien(request, response);
        }
    }

    private void addSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mssv = request.getParameter("mssv");
        String ten = request.getParameter("ten");
        String tuoi = request.getParameter("tuoi");
        String diaChi = request.getParameter("diaChi");
        String gioiTinh = request.getParameter("gioiTinh");
        SinhVien sinhVien = new SinhVien(mssv, ten, Integer.valueOf(tuoi), diaChi, Boolean.parseBoolean(gioiTinh));
        sinhVienService.addSinhVien(sinhVien, sinhViens);
        response.sendRedirect("/sinh-vien/hien-thi");
    }

    private void updateSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mssv = request.getParameter("mssv");
        String ten = request.getParameter("ten");
        String tuoi = request.getParameter("tuoi");
        String diaChi = request.getParameter("diaChi");
        String gioiTinh = request.getParameter("gioiTinh");
        SinhVien newSinhVien = new SinhVien(mssv, ten, Integer.valueOf(tuoi), diaChi, Boolean.parseBoolean(gioiTinh));
        sinhVienService.updateSinhVien(mssv, sinhViens, newSinhVien);
        response.sendRedirect("/sinh-vien/hien-thi");
    }

    private void hienThiSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (sinhViens.isEmpty()) {
            sinhViens.add(new SinhVien("HangNT169", "Nguyễn Thuý Hằng", 10, "Hà Nội", false));
            sinhViens.add(new SinhVien("PhongTT35", "Trần Tuấn Phong", 11, "Hà Nội1", true));
            sinhViens.add(new SinhVien("NguyenVV4", "Vũ Văn Nguyên", 12, "Hà Nội2", true));
            sinhViens.add(new SinhVien("KhanhPG", "Phạm Gia Khánh", 13, "Hà Nội", true));
            sinhViens.add(new SinhVien("TienNH21", "Nguyễn Hoàng Tiến", 14, "Hà Nội3", true));
            sinhViens.add(new SinhVien("DungNA29", "Nguyễn Anh Dũng", 15, "Hà Nội4", true));
        }
        request.setAttribute("sinhViens", sinhViens);
        request.getRequestDispatcher("/view/sinh-vien.jsp").forward(request, response);
    }

    private void detailSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mssv = request.getParameter("mssv");
        int index = sinhVienService.findIndexSinhVien(sinhViens, mssv);
        SinhVien sinhVien = sinhViens.get(index);
        request.setAttribute("sinhVien", sinhVien);
        request.getRequestDispatcher("/view/detail-sinh-vien.jsp").forward(request, response);
    }

    private void hienThiAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/add-sinh-vien.jsp").forward(request, response);
    }

    private void hienThiUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mssv = request.getParameter("mssv");
        int index = sinhVienService.findIndexSinhVien(sinhViens, mssv);
        SinhVien sinhVien = sinhViens.get(index);
        request.setAttribute("sinhVien", sinhVien);
        request.getRequestDispatcher("/view/update-sinh-vien.jsp").forward(request, response);
    }

    private void deleteSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mssv = request.getParameter("mssv");
        sinhVienService.deleteSinhVien(mssv, sinhViens);
        response.sendRedirect("/sinh-vien/hien-thi");
//        request.setAttribute("sinhViens", sinhViens);
//        request.getRequestDispatcher("/view/sinh-vien.jsp").forward(request, response);
    }

}
