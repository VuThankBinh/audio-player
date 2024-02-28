package com.example.sportify;

public class BaiHat {
    public int MaBaiHat;
    public String TenBaiHat;
    public String CaSi;
    public String tenfile;
    public String fileanh;

    public BaiHat() {
    }

    public BaiHat(int maBaiHat, String tenBaiHat, String caSi, String tenfile,String fileanh) {
        MaBaiHat = maBaiHat;
        TenBaiHat = tenBaiHat;
        CaSi = caSi;
        this.tenfile = tenfile;
        this.fileanh=fileanh;
    }

    public int getMaBaiHat() {
        return MaBaiHat;
    }

    public String getFileanh() {
        return fileanh;
    }

    public void setFileanh(String fileanh) {
        this.fileanh = fileanh;
    }

    public void setMaBaiHat(int maBaiHat) {
        MaBaiHat = maBaiHat;
    }

    public String getTenBaiHat() {
        return TenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        TenBaiHat = tenBaiHat;
    }

    public String getCaSi() {
        return CaSi;
    }

    public void setCaSi(String caSi) {
        CaSi = caSi;
    }

    public String getTenfile() {
        return tenfile;
    }

    public void setTenfile(String tenfile) {
        this.tenfile = tenfile;
    }
}
