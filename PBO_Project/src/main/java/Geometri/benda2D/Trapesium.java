package Geometri.benda2D;

import Geometri.BangunDatar;

public class Trapesium extends BangunDatar{
    protected double alasAtas;
    protected double alasBawah;
    protected double sisiMiring;
    protected double tinggi;
    protected double luas;
    protected double keliling;
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public Trapesium(double alasAtas, double alasBawah, double tinggi) {
        try {
            validateInput(alasAtas, alasBawah, tinggi);
            this.alasAtas = alasAtas;
            this.alasBawah = alasBawah;
            this.tinggi = tinggi;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat Trapesium: " + e.getMessage());
        }
    }

    private void validateInput(double alasAtas, double alasBawah, double tinggi) {
        if (alasAtas <= MIN_VALUE) {
            throw new IllegalArgumentException("Alas atas trapesium " + ERROR_NEGATIVE + ": " + alasAtas);
        }
        if (alasBawah <= MIN_VALUE) {
            throw new IllegalArgumentException("Alas bawah trapesium " + ERROR_NEGATIVE + ": " + alasBawah);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("Tinggi trapesium " + ERROR_NEGATIVE + ": " + tinggi);
        }
    }

    @Override
    public String getNama() {
        return "Trapesium";
    }

    @Override
    public double hitungLuas() {
        try {
            luas = 0.5 * (alasAtas + alasBawah) * tinggi;
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas trapesium: " + e.getMessage());
        }
    }

    @Override
    public double hitungKeliling() {
        try {
            if (sisiMiring == 0){
                sisiMiring = Math.sqrt(Math.pow((alasBawah - alasAtas) / 2, 2) + Math.pow(tinggi, 2));
            }
            keliling = alasAtas + alasBawah + 2 * sisiMiring;
            return keliling;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung keliling trapesium: " + e.getMessage());
        }
    }

    public String getInfo() {
        try {
            return String.format("Trapesium{alasAtas=%.2f, alasBawah=%.2f, tinggi=%.2f, luas=%.2f, keliling=%.2f}",
                     alasAtas, alasBawah, tinggi, luas, keliling);
        } catch (Exception e) {
            return "Error mendapatkan info trapesium: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
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
