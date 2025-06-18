package Geometri.benda2D;

import Geometri.BangunDatar;

public class Segitiga extends BangunDatar {
    public double alas;
    public double tinggi;
    public double luas;
    public double keliling;

    public Segitiga(double alas, double tinggi) {
        this.alas = alas;
        this.tinggi = tinggi;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama() {
        return "Segitiga";
    }

    @Override
    public double hitungLuas() {
        luas = 0.5 * alas * tinggi;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        keliling = 3 * alas;
        return keliling;
    }

    public double getLuas() {
        return luas;
    }

    public double getKeliling() {
        return keliling;
    }

    public double getAlas() {
        return alas;
    }

    public double getTinggi() {
        return tinggi;
    }
    
}
