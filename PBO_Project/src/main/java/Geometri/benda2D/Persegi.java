package Geometri.benda2D;

import Geometri.BangunDatar;

public class Persegi extends BangunDatar {
    protected double sisi;
    protected double luas;
    protected double keliling;
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public Persegi(double sisi) {
        try {
            validateInput(sisi);
            this.sisi = sisi;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat Persegi: " + e.getMessage());
        }
    }
    
    private void validateInput(double sisi) {
        if (sisi <= MIN_VALUE) {
            throw new IllegalArgumentException("Sisi persegi " + ERROR_NEGATIVE + ": " + sisi);
        }
    }

    @Override
    public String getNama() {
        return "Persegi";
    }

    @Override
    public double hitungLuas() {
        try {
            luas = sisi * sisi;
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas persegi: " + e.getMessage());
        }
    }
    
    @Override
    public double hitungKeliling() {
        try {
            keliling = 4 * sisi;
            return keliling;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung keliling persegi: " + e.getMessage());
        }
    }
    
    public double hitungLuas(double sisiBaru) {
        try {
            validateInput(sisiBaru);
            luas = sisiBaru * sisiBaru;
            return luas;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas: " + e.getMessage());
        }
    }

    public double hitungKeliling(double sisiBaru) {
        try {
            validateInput(sisiBaru);
            keliling = 4 * sisiBaru;
            return keliling;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung keliling: " + e.getMessage());
        }
    }

    public boolean isSama(Persegi persegiLain) {
        if (persegiLain == null) return false;
        return Math.abs(this.sisi - persegiLain.sisi) < 0.001;
    }

    public double getDiagonal() {
        double hasilDiagonal = sisi * Math.sqrt(2);
        return hasilDiagonal;
    }

    public static double getDiagonal(double sisiBaru) {
        if (sisiBaru <= MIN_VALUE) {
            throw new IllegalArgumentException("Sisi " + ERROR_NEGATIVE + ": " + sisiBaru);
        }
        double hasilDiagonal = sisiBaru * Math.sqrt(2);
        return hasilDiagonal;
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

    public String getInfo() {
        return String.format("Persegi{sisi=%.2f, luas=%.2f, keliling=%.2f, diagonal=%.2f}", 
                           sisi, luas, keliling, getDiagonal());
    }
    
    @Override
    public String toString() {
        return getInfo();
    }
}
