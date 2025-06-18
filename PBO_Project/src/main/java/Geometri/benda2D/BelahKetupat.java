package Geometri.benda2D;

import Geometri.BangunDatar;

public class BelahKetupat extends BangunDatar {
    public double diagonal1;
    public double diagonal2;
    public double sisi;
    public double luas;
    public double keliling;

    public BelahKetupat(double diagonal1, double diagonal2, double sisi) {
        this.sisi = sisi;
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama() {
        return "Belah Ketupat";
    }

    @Override
    public double hitungLuas() {
        luas = (diagonal1 * diagonal2) / 2;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        if (sisi == 0) {
            keliling = Math.sqrt(Math.pow(diagonal1 / 2, 2) + Math.pow(diagonal2 / 2, 2));
        }else {
            keliling = 4 * sisi;
        }
        return keliling;
    }

    public double getLuas() {
        return luas;
    }

    public double getKeliling() {
        return keliling;
    }

    public double getDiagonal1() {
        return diagonal1;
    }

    public double getDiagonal2() {
        return diagonal2;
    }
    public double getSisi() {
        return sisi;
    }
}
