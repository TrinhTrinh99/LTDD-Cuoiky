package com.example.appdoctruyen.model;

public class Truyen {
    private int ID;
    private String TenTruyen;
    private String NoiDung;
    private String HinhAnh;
    private int ID_TK;

    public Truyen(int ID, String tenTruyen, String noiDung, String hinhAnh, int ID_TK) {
        this.ID = ID;
        TenTruyen = tenTruyen;
        NoiDung = noiDung;
        HinhAnh = hinhAnh;
        this.ID_TK = ID_TK;
    }


    public int getID() {
        return ID;
    }

    public Truyen(String tenTruyen, String noiDung, String hinhAnh, int ID_TK) {
        TenTruyen = tenTruyen;
        NoiDung = noiDung;
        HinhAnh = hinhAnh;
        this.ID_TK = ID_TK;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenTruyen() {
        return TenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        TenTruyen = tenTruyen;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }
}