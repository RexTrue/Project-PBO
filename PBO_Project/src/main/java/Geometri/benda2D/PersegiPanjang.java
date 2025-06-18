package Geometri.benda2D;

import Geometri.BangunDatar;

public class PersegiPanjang extends BangunDatar {
    public double panjang;
    public double lebar;
    public double luas;
    public double keliling;

    public PersegiPanjang(double panjang, double lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama() {
        return "Persegi Panjang";
    }

    @Override
    public double hitungLuas() {
        luas = panjang * lebar;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        keliling = 2 * (panjang + lebar);
        return keliling;
    }

    public double getLuas() {
        return luas;
    }

    
    public double getKeliling() {
        return keliling;
    }

    public double getPanjang() {
        return panjang;
    }

    public double getLebar() {
        return lebar;
    }

}
