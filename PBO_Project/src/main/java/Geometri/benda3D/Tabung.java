package Geometri.benda3D;

import Geometri.benda2D.Lingkaran;
import Geometri.BangunRuang;

public class Tabung extends Lingkaran implements BangunRuang {
    protected double tinggi;
    protected double volume;
    protected double luasPermukaan;
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public Tabung(double radius, double tinggi) {
        super(radius);
        try {
            validateTabungInput(radius, tinggi);
            this.tinggi = tinggi;
            this.volume = hitungVolume();
            this.luasPermukaan = hitungLuasPermukaan();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat Tabung: " + e.getMessage());
        }
    }

    private void validateTabungInput(double radius, double tinggi) {
        if (radius <= MIN_VALUE) {
            throw new IllegalArgumentException("Radius tabung " + ERROR_NEGATIVE + ": " + radius);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("Tinggi tabung " + ERROR_NEGATIVE + ": " + tinggi);
        }
    }

    @Override
    public String getNama() {
        return "Tabung";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            luasPermukaan = 2 * PI * radius * (radius + tinggi);
            return luasPermukaan;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan tabung: " + e.getMessage());
        }
    }

    public double hitungLuasPermukaan(double radiusBaru, double tinggiBaru) {
        try {
            validateTabungInput(radiusBaru, tinggiBaru);
            luasPermukaan = 2 * PI * radiusBaru * (radiusBaru + tinggiBaru);
            return luasPermukaan;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volume = PI * radius * radius * tinggi;
            return volume;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume tabung: " + e.getMessage());
        }
    }

    public double hitungVolume(double radiusBaru, double tinggiBaru) {
        try {
            validateTabungInput(radiusBaru, tinggiBaru);
            volume = PI * radiusBaru * radiusBaru * tinggiBaru;
            return volume;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung volume: " + e.getMessage());
        }
    }

    public double hitungVolume(double volumeLingkaran) {
        try {
            if (volumeLingkaran <= 0) {
                throw new IllegalArgumentException("Volume lingkaran tidak boleh negatif atau nol");
            }
            return volumeLingkaran * tinggi;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung volume: " + e.getMessage());
        }
    }

    public double hitungLuasPermukaan(double luasLingkaran) {
        try {
            if (luasLingkaran <= 0) {
                throw new IllegalArgumentException("Luas lingkaran tidak boleh negatif atau nol");
            }
            double kelilingLingkaran = 2 * PI * radius;
            return 2 * luasLingkaran + kelilingLingkaran * tinggi;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    public String getInfo() {
        try {
            return String.format("Tabung{radius=%.2f, tinggi=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                     radius, tinggi, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info tabung: " + e.getMessage();
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
