package Geometri.benda2D;

import Geometri.BangunDatar;

public class Segitiga extends BangunDatar {
    protected double alas;
    protected double tinggi;
    protected double sisi1;
    protected double sisi2;
    protected double sisi3;
    protected double luas;
    protected double keliling;
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public Segitiga(double alas, double tinggi) {
        try {
            validateInput(alas, tinggi);
            this.alas = alas;
            this.tinggi = tinggi;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat Segitiga: " + e.getMessage());
        }
    }

    private void validateInput(double alas, double tinggi) {
        if (alas <= MIN_VALUE) {
            throw new IllegalArgumentException("Alas segitiga " + ERROR_NEGATIVE + ": " + alas);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("Tinggi segitiga " + ERROR_NEGATIVE + ": " + tinggi);
        }
    }

    @Override
    public String getNama() {
        return "Segitiga";
    }

    @Override
    public double hitungLuas() {
        try {
            luas = 0.5 * alas * tinggi;
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas segitiga: " + e.getMessage());
        }
    }

    public double hitungLuas(double alasBaru, double tinggiBaru) {
        try {
            validateInput(alasBaru, tinggiBaru);
            luas = 0.5 * alasBaru * tinggiBaru;
            return luas;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas: " + e.getMessage());
        }
    }

    @Override
    public double hitungKeliling() {
        try {
            if (sisi1 > 0 && sisi2 > 0 && sisi3 > 0) {
                keliling = sisi1 + sisi2 + sisi3;
            } else {
                keliling = 0;
            }
            return keliling;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung keliling segitiga: " + e.getMessage());
        }
    }
    
    public double hitungKeliling(double sisi1Baru, double sisi2Baru, double sisi3Baru) {
        try {
            validateSisiInput(sisi1Baru, sisi2Baru, sisi3Baru);
            keliling = sisi1Baru + sisi2Baru + sisi3Baru;
            return keliling;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung keliling: " + e.getMessage());
        }
    }

    private void validateSisiInput(double sisi1, double sisi2, double sisi3) {
        if (sisi1 <= MIN_VALUE) {
            throw new IllegalArgumentException("Sisi 1 segitiga " + ERROR_NEGATIVE + ": " + sisi1);
        }
        if (sisi2 <= MIN_VALUE) {
            throw new IllegalArgumentException("Sisi 2 segitiga " + ERROR_NEGATIVE + ": " + sisi2);
        }
        if (sisi3 <= MIN_VALUE) {
            throw new IllegalArgumentException("Sisi 3 segitiga " + ERROR_NEGATIVE + ": " + sisi3);
        }
        
        if (sisi1 + sisi2 <= sisi3 || sisi1 + sisi3 <= sisi2 || sisi2 + sisi3 <= sisi1) {
            throw new IllegalArgumentException("Sisi-sisi tidak membentuk segitiga yang valid");
        }
    }
    
    public boolean isSegitigaSamaKaki() {
        return Math.abs(sisi1 - sisi2) < 0.001 || Math.abs(sisi1 - sisi3) < 0.001 || Math.abs(sisi2 - sisi3) < 0.001;
    }

    public boolean isSegitigaSamaSisi() {
        return Math.abs(sisi1 - sisi2) < 0.001 && Math.abs(sisi2 - sisi3) < 0.001;
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

    public String getInfo() {
        try {
            return String.format("Segitiga{alas=%.2f, tinggi=%.2f, luas=%.2f, keliling=%.2f}",
                     alas, tinggi, luas, keliling);
        } catch (Exception e) {
            return "Error mendapatkan info segitiga: " + e.getMessage();
        }
    }
    
    @Override
    public String toString() {
        return getInfo();
    }
}
