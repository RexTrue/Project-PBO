package Geometri.benda2D;

import Geometri.BangunDatar;

public class LayangLayang extends BangunDatar{
    public double diagonal1;
    public double diagonal2;
    public double luas;
    public double keliling;

    public LayangLayang(double diagonal1, double diagonal2) {
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama() {
        return "Layang-Layang";
    }

    @Override
    public double hitungLuas() {
        luas = (diagonal1 * diagonal2) / 2;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        keliling = 4 * (Math.sqrt((diagonal1 * diagonal1 + diagonal2 * diagonal2) / 4));
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
}
