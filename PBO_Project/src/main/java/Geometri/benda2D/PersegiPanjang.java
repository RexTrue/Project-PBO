package Geometri.benda2D;

import Geometri.BangunDatar;

public class PersegiPanjang extends BangunDatar {
    protected double panjang;
    protected double lebar;
    protected double luas;
    protected double keliling;
    protected double diagonal;
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public PersegiPanjang(double panjang, double lebar) {
        try {
            validateInput(panjang, lebar);
            this.panjang = panjang;
            this.lebar = lebar;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
            this.diagonal = hitungDiagonal();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat PersegiPanjang: " + e.getMessage());
        }
    }

    private void validateInput(double panjang, double lebar) {
        if (panjang <= MIN_VALUE) {
            throw new IllegalArgumentException("Panjang persegi panjang " + ERROR_NEGATIVE + ": " + panjang);
        }
        if (lebar <= MIN_VALUE) {
            throw new IllegalArgumentException("Lebar persegi panjang " + ERROR_NEGATIVE + ": " + lebar);
        }
    }

    @Override
    public String getNama() {
        return "Persegi Panjang";
    }

    @Override
    public double hitungLuas() {
        try {
            luas = panjang * lebar;
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas persegi panjang: " + e.getMessage());
        }
    }

    @Override
    public double hitungKeliling() {
        try {
            keliling = 2 * (panjang + lebar);
            return keliling;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung keliling persegi panjang: " + e.getMessage());
        }
    }
    
    public double hitungLuas(double panjangBaru, double lebarBaru) {
        try {
            validateInput(panjangBaru, lebarBaru);
            luas = panjangBaru * lebarBaru;
            return luas;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas: " + e.getMessage());
        }
    }
    
    public double hitungKeliling(double panjangBaru, double lebarBaru) {
        try {
            validateInput(panjangBaru, lebarBaru);
            keliling = 2 * (panjangBaru + lebarBaru);
            return keliling;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung keliling: " + e.getMessage());
        }
    }
    
    public boolean isSama(PersegiPanjang ppLain) {
        if (ppLain == null) return false;
        return Math.abs(this.panjang - ppLain.panjang) < 0.001 && 
               Math.abs(this.lebar - ppLain.lebar) < 0.001;
    }
    
    public boolean isPersegi() {
        return Math.abs(panjang - lebar) < 0.001;
    }
    
    public double hitungDiagonal() {
        try {
            diagonal = Math.sqrt(panjang * panjang + lebar * lebar);
            return diagonal;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung diagonal persegi panjang: " + e.getMessage());
        }
    }

    public double getDiagonal() {
        return diagonal;
    }

    public double getRasio() {
        double hasilRasio = panjang / lebar;
        return hasilRasio;
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

    public String getInfo() {
        try {
            return String.format("PersegiPanjang{panjang=%.2f, lebar=%.2f, luas=%.2f, keliling=%.2f, diagonal=%.2f}", 
                               panjang, lebar, luas, keliling, diagonal);
        } catch (Exception e) {
            return "Error mendapatkan info persegi panjang: " + e.getMessage();
        }
    }
    
    @Override
    public String toString() {
        return getInfo();
    }
}
