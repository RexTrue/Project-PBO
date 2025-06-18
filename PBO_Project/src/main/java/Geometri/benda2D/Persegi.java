package Geometri.benda2D;

import Geometri.BangunDatar;

public class Persegi extends BangunDatar {
    public double sisi;
    public double luas;
    public double keliling;

    public Persegi(double sisi){
        this.sisi = sisi;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama(){
        return "Persegi";
    }

    @Override
    public double hitungLuas() {
        luas = sisi * sisi;
        return luas;
    }
    
    @Override
    public double hitungKeliling() {
        keliling = 4 * sisi;
        return keliling;
    }

    public double getLuas() {
        return luas;
    }
    public double getKeliling() {
        return keliling;
    }
    public double getSisi() {
        return sisi;
    }
}
