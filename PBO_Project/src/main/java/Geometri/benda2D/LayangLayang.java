package Geometri.benda2D;

import Geometri.BangunDatar;

public class LayangLayang extends BangunDatar {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    protected double diagonal1;
    protected double diagonal2;
    protected double luas;
    protected double keliling;

    private void validateInput(double diagonal1, double diagonal2) {
        if (diagonal1 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal1 " + ERROR_NEGATIVE + ": " + diagonal1);
        }
        if (diagonal2 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal2 " + ERROR_NEGATIVE + ": " + diagonal2);
        }
    }

    public LayangLayang(double diagonal1, double diagonal2) {
        try {
            validateInput(diagonal1, diagonal2);
            this.diagonal1 = diagonal1;
            this.diagonal2 = diagonal2;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat LayangLayang: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Layang-Layang";
    }

    @Override
    public double hitungLuas() {
        try {
            luas = (diagonal1 * diagonal2) / 2;
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas: " + e.getMessage());
        }
    }

    @Override
    public double hitungKeliling() {
        try {
            keliling = 4 * (Math.sqrt((diagonal1 * diagonal1 + diagonal2 * diagonal2) / 4));
            return keliling;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung keliling: " + e.getMessage());
        }
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

    public String getInfo() {
        try {
            return String.format("LayangLayang{diagonal1=%.2f, diagonal2=%.2f, luas=%.2f, keliling=%.2f}",
                diagonal1, diagonal2, luas, keliling);
        } catch (Exception e) {
            return "Error mendapatkan info layanglayang: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
