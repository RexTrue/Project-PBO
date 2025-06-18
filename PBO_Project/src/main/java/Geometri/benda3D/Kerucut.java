package Geometri.benda3D;

import Geometri.benda2D.Lingkaran;
import Geometri.BangunRuang;

public class Kerucut extends Lingkaran implements BangunRuang {
    protected double tinggi;
    protected double volume;
    protected double luasPermukaan;
    protected double sisiMiring;

    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif";

    public Kerucut(double radius, double tinggi) {
        super(radius);
        try {
            validateKerucutInput(radius, tinggi);
            this.tinggi = tinggi;
            this.volume = hitungVolume();
            this.luasPermukaan = hitungLuasPermukaan();
            this.sisiMiring = getSisiMiring();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat Kerucut: " + e.getMessage());
        }
    }

    private void validateKerucutInput(double radius, double tinggi) {
        if (radius <= MIN_VALUE) {
            throw new IllegalArgumentException("Radius kerucut " + ERROR_NEGATIVE + ": " + radius);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("Tinggi kerucut " + ERROR_NEGATIVE + ": " + tinggi);
        }
    }

    @Override
    public String getNama() {
        return "Kerucut";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double sisiMiring = Math.sqrt(radius * radius + tinggi * tinggi);
            luasPermukaan = PI * radius * (radius + sisiMiring);
            return luasPermukaan;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan kerucut: " + e.getMessage());
        }
    }

    public double hitungLuasPermukaan(double radiusBaru, double tinggiBaru) {
        try {
            validateKerucutInput(radiusBaru, tinggiBaru);
            double sisiMiring = Math.sqrt(radiusBaru * radiusBaru + tinggiBaru * tinggiBaru);
            luasPermukaan = PI * radiusBaru * (radiusBaru + sisiMiring);
            return luasPermukaan;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volume = (1.0 / 3.0) * PI * radius * radius * tinggi;
            return volume;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume kerucut: " + e.getMessage());
        }
    }

    public double hitungVolume(double radiusBaru, double tinggiBaru) {
        try {
            validateKerucutInput(radiusBaru, tinggiBaru);
            volume = (1.0 / 3.0) * PI * radiusBaru * radiusBaru * tinggiBaru;
            return volume;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung volume: " + e.getMessage());
        }
    }

    public double getSisiMiring() {
        try {
            sisiMiring = Math.sqrt(radius * radius + tinggi * tinggi);
            return sisiMiring;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung sisi miring: " + e.getMessage());
        }
    }

    public static double getSisiMiring(double radiusBaru, double tinggiBaru, double sisiMiring) {
        try {
            if (radiusBaru <= MIN_VALUE || tinggiBaru <= MIN_VALUE) {
                throw new IllegalArgumentException("Parameter " + ERROR_NEGATIVE);
            }
            sisiMiring = Math.sqrt(radiusBaru * radiusBaru + tinggiBaru * tinggiBaru);
            return sisiMiring;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung sisi miring: " + e.getMessage());
        }
    }

    public String getInfo() {
        try {
            return String.format("Kerucut{radius=%.2f, tinggi=%.2f, diameter=%.2f, volume=%.2f, luasPermukaan=%.2f}", 
                               radius, tinggi, getDiameter(), volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info kerucut: " + e.getMessage();
        }
    }
    
    @Override
    public String toString() {
        return getInfo();
    }

    public double getRadius() {
        return radius;
    }

    public double getTinggi() {
        return tinggi;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double getVolume() {
        return volume;
    }
}
