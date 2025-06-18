package Geometri.benda2D;

import Geometri.BangunDatar;

public class BelahKetupat extends BangunDatar {
    protected double diagonal1;
    protected double diagonal2;
    protected double sisi;
    protected double luas;
    protected double keliling;
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public BelahKetupat(double diagonal1, double diagonal2, double sisi) {
        try {
            validateInput(diagonal1, diagonal2, sisi);
            this.diagonal1 = diagonal1;
            this.diagonal2 = diagonal2;
            this.sisi = sisi;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat BelahKetupat: " + e.getMessage());
        }
    }

    private void validateInput(double diagonal1, double diagonal2, double sisi) {
        if (diagonal1 <= MIN_VALUE) {
            throw new IllegalArgumentException("Diagonal 1 belah ketupat " + ERROR_NEGATIVE + ": " + diagonal1);
        }
        if (diagonal2 <= MIN_VALUE) {
            throw new IllegalArgumentException("Diagonal 2 belah ketupat " + ERROR_NEGATIVE + ": " + diagonal2);
        }
        if (sisi <= MIN_VALUE) {
            throw new IllegalArgumentException("Sisi belah ketupat " + ERROR_NEGATIVE + ": " + sisi);
        }
    }

    @Override
    public String getNama() {
        return "Belah Ketupat";
    }

    @Override
    public double hitungLuas() {
        try {
            luas = (diagonal1 * diagonal2) / 2;
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas belah ketupat: " + e.getMessage());
        }
    }

    @Override
    public double hitungKeliling() {
        try {
            if (sisi == 0) {
                keliling = 4 * Math.sqrt(Math.pow(diagonal1 / 2, 2) + Math.pow(diagonal2 / 2, 2));
            } else {
                keliling = 4 * sisi;
            }
            return keliling;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung keliling belah ketupat: " + e.getMessage());
        }
    }

    public String getInfo() {
        try {
            return String.format("BelahKetupat{diagonal1=%.2f, diagonal2=%.2f, sisi=%.2f, luas=%.2f, keliling=%.2f}",
                     diagonal1, diagonal2, sisi, luas, keliling);
        } catch (Exception e) {
            return "Error mendapatkan info belah ketupat: " + e.getMessage();
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
