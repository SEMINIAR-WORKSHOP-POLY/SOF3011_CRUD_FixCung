package com.poly.hangnt169.controller;

import com.poly.hangnt169.entity.SinhVien;
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
        "/sinh-vien/hien-thi", // GET
        "/sinh-vien/detail",  // GET
        "/sinh-vien/delete",  // GET
        "/sinh-vien/view-update",  // GET
        "/sinh-vien/update",   // POST
        "/sinh-vien/view-add",  // GET
        "/sinh-vien/add"    // POST
})
public class SinhVienServlet extends HttpServlet {

    private List<SinhVien> listSinhVien = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chua 5 duong dan
        // Lay gia tri cua duong dan
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            // Hien thi du lieu len table
            this.hienThiDanhSachSinhvien(request, response);
        } else if (uri.contains("detail")) {
            // Hien thi thong tin detail sinh vien
            this.detailSinhVien(request, response);
        } else if (uri.contains("delete")) {
            // Xoa thong tin  sinh vien
            this.deleteSinhVien(request, response);
        } else if (uri.contains("view-update")) {
            // Hien thi trang man hinh update
            this.viewUpdateSinhVien(request, response);
        } else if (uri.contains("view-add")) {
            // Hien thi trang man hinh add
            this.viewAddSinhVien(request, response);
        } else {
            // Neu khong roi vao cac case tren => Hien thi lai trang load len table
            this.hienThiDanhSachSinhvien(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chua 2 duong dan
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            // Add Sinh Vien
            this.addSinhVien(request, response);
        } else {
            // Update Sinh Vien
            this.updateSinhVien(request, response);
        }
    }

    private void updateSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // B1: Lay gia tri tu o input trong form
        String maSV = request.getParameter("mssv");
        String newTen = request.getParameter("tenSV");
        String newTuoi = request.getParameter("tuoi");
        String newGioiTinh = request.getParameter("gioiTinh");
        String newDiaChi = request.getParameter("diaChi");

        // B2: Tim vi tri cua doi tuong muon update
        int viTri = -1;
        for (int i = 0; i < listSinhVien.size(); i++) {
            if (listSinhVien.get(i).getMaSV().equalsIgnoreCase(maSV)) {
                viTri = i;
            }
        }
        SinhVien sv = listSinhVien.get(viTri);

        // B3: Set gia tri thuoc tinh cho doi SV
        sv.setDiaChi(newDiaChi);
        sv.setGioiTinh(Boolean.valueOf(newGioiTinh));
        sv.setTenSV(newTen);
        sv.setTuoi(Integer.valueOf(newTuoi));

        // B4: Update gia tri cho list
        listSinhVien.set(viTri,sv);

        // B5: Chuyen trang quay ve trang hien thi
        response.sendRedirect("/sinh-vien/hien-thi");
    }

    private void addSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // B1: Lay gia tri tu cac o input trong form
        String maSV = request.getParameter("mssv");
        String ten = request.getParameter("ten");
        String tuoi = request.getParameter("tuoi");
        String gioiTinh = request.getParameter("gioiTinh");
        String diaChi = request.getParameter("diaChi");

        // B2: Khoi tao doi tuong
        SinhVien sv = new SinhVien(maSV, ten, Integer.valueOf(tuoi), diaChi, Boolean.valueOf(gioiTinh));

        // B3: Add vao list
        listSinhVien.add(sv);

        // B4: Chuyen sang trang hien thi
        response.sendRedirect("/sinh-vien/hien-thi");
    }

    private void viewAddSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/add-sinh-vien.jsp").forward(request, response);
    }

    private void viewUpdateSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // B1: Lay gia tri tu jsp ve servlet
        String mssv = request.getParameter("mssv");
        // B2: Tim SV vua duoc chon
        int viTri = -1; // -1 : K tim thay hoac chua chon
        for (int i = 0; i < listSinhVien.size(); i++) {
            if (listSinhVien.get(i).getMaSV().equalsIgnoreCase(mssv)) {
                viTri = i;
            }
        }
        SinhVien sv = listSinhVien.get(viTri);
        // B3: Chuyen trang
        request.setAttribute("sinhVien", sv);
        request.getRequestDispatcher("/view/update-sinh-vien.jsp").forward(request, response);
    }

    private void deleteSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // B1: Lay gia tri tu jsp ve servlet
        String mssv = request.getParameter("mssv");
        // B2: Tim SV vua duoc chon
        int viTri = -1; // -1 : K tim thay hoac chua chon
        for (int i = 0; i < listSinhVien.size(); i++) {
            if (listSinhVien.get(i).getMaSV().equalsIgnoreCase(mssv)) {
                viTri = i;
            }
        }
        // B3: Xoa SV
        listSinhVien.remove(viTri);

        // B4: Chuyen ve trang hien thi
        response.sendRedirect("/sinh-vien/hien-thi");
    }

    private void detailSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // B1: Lay gia tri tu jsp ve servlet
        String mssv = request.getParameter("mssv");
        // B2: Tim SV vua duoc chon
        int viTri = -1; // -1 : K tim thay hoac chua chon
        for (int i = 0; i < listSinhVien.size(); i++) {
            if (listSinhVien.get(i).getMaSV().equalsIgnoreCase(mssv)) {
                viTri = i;
            }
        }
        SinhVien sv = listSinhVien.get(viTri);
        // B3: Chuyen trang
        request.setAttribute("sinhVien", sv);
        request.getRequestDispatcher("/view/detail-sinh-vien.jsp").forward(request, response);
    }

    private void hienThiDanhSachSinhvien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (listSinhVien.isEmpty()) {
            listSinhVien.add(new SinhVien("HangNT169", "Nguyen Thuy Hang", 18, "Ha Noi", false));
            listSinhVien.add(new SinhVien("NguyenVV4", "Vu Van Nguyen", 19, "Ha Noi1", true));
            listSinhVien.add(new SinhVien("TienNH21", "Nguyen Hoang Tien", 20, "Ha Noi2", false));
            listSinhVien.add(new SinhVien("KhanhPG", "Pham Gia Khanh", 17, "Ha Noi4", true));
            listSinhVien.add(new SinhVien("DungNA29", "Nguyen Anh Dung", 21, "Ha Noi5", false));
            listSinhVien.add(new SinhVien("PhongTT35", "Tran Tuan Phong", 15, "Ha Noi8", true));
        }

        // Truyen gia tu tu servlet sang controller
        request.setAttribute("sinhViens", listSinhVien);

        // Chuyen trang
        request.getRequestDispatcher("/view/sinh-vien.jsp").forward(request, response);
    }
}
