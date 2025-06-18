package Geometri.benda2D;

import Geometri.BangunDatar;

public class Trapesium extends BangunDatar{
    public double alasAtas;
    public double alasBawah;
    public double sisiMiring;
    public double tinggi;
    public double luas;
    public double keliling;

    public Trapesium(double alasAtas, double alasBawah, double tinggi) {
        this.alasAtas = alasAtas;
        this.alasBawah = alasBawah;
        this.tinggi = tinggi;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama() {
        return "Trapesium";
    }

    @Override
    public double hitungLuas() {
        luas = 0.5 * (alasAtas + alasBawah) * tinggi;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        if (sisiMiring == 0){
            sisiMiring = Math.sqrt(Math.pow((alasBawah - alasAtas) / 2, 2) + Math.pow(tinggi, 2));
        }
        keliling = alasAtas + alasBawah + 2 * sisiMiring;
        return keliling;
    }

    public double getLuas() {
        return luas;
    }

    public double getKeliling() {
        return keliling;
    }

    public double getAlasAtas() {
        return alasAtas;
    }

    public double getAlasBawah() {
        return alasBawah;
    }

    public double getTinggi() {
        return tinggi;
    }
    
    public double getSisiMiring() {
        return sisiMiring;
    }   
}
