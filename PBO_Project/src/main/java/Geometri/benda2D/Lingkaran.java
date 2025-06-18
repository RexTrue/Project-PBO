package Geometri.benda2D;

import Geometri.BangunDatar;

public class Lingkaran extends BangunDatar {

    protected double radius;
    protected double luas;
    protected double keliling;
    protected final double PI = Math.PI;
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public Lingkaran(double radius) {
        try {
            validateInput(radius);
            this.radius = radius;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat Lingkaran: " + e.getMessage());
        }
    }
    
    private void validateInput(double radius) {
        if (radius <= MIN_VALUE) {
            throw new IllegalArgumentException("Radius lingkaran " + ERROR_NEGATIVE + ": " + radius);
        }
    }

    @Override
    public String getNama() {
        return "Lingkaran";
    }

    @Override
    public double hitungLuas() {
        try {
            luas = PI * radius * radius;
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas lingkaran: " + e.getMessage());
        }
    }

    @Override
    public double hitungKeliling() {
        try {
            keliling = 2 * PI * radius;
            return keliling;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung keliling lingkaran: " + e.getMessage());
        }
    }
    
    public double hitungLuas(double radiusBaru) {
        try {
            validateInput(radiusBaru);
            luas = PI * radiusBaru * radiusBaru;
            return luas;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas: " + e.getMessage());
        }
    }
    
    public double hitungKeliling(double radiusBaru) {
        try {
            validateInput(radiusBaru);
            keliling = 2 * PI * radiusBaru;
            return keliling;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung keliling: " + e.getMessage());
        }
    }

    public double getDiameter() {
        return 2 * radius;
    }

    public static double getDiameter(double radiusBaru) {
        if (radiusBaru <= MIN_VALUE) {
            throw new IllegalArgumentException("Radius " + ERROR_NEGATIVE + ": " + radiusBaru);
        }
        double hasilDiameter = 2 * radiusBaru;
        return hasilDiameter;
    }

    public boolean isSama(Lingkaran lingkaranLain) {
        if (lingkaranLain == null) return false;
        return Math.abs(this.radius - lingkaranLain.radius) < 0.001;
    }

    public double getPI() {
        return PI;
    }

    public boolean isDiDalam(Lingkaran lingkaranLain) {
        if (lingkaranLain == null) return false;
        return this.radius < lingkaranLain.radius;
    }

    public double getLuas() {
        return luas;
    }

    public double getKeliling() {
        return keliling;
    }

    public double getRadius() {
        return radius;
    }
    
    public String getInfo() {
        try {
            return String.format("Lingkaran{radius=%.2f, diameter=%.2f, luas=%.2f, keliling=%.2f}", 
                               radius, getDiameter(), luas, keliling);
        } catch (Exception e) {
            return "Error mendapatkan info lingkaran: " + e.getMessage();
        }
    }
    
    @Override
    public String toString() {
        return getInfo();
    }
}
