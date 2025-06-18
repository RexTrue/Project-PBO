package Geometri.benda3D;

import Geometri.benda2D.Lingkaran;
import Geometri.BangunRuang;

public class Bola extends Lingkaran implements BangunRuang {
    protected double volume;
    protected double luasPermukaan;
    protected double radiusBaru;

    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public Bola(double radius) {
        super(radius);
        try {
            validateBolaInput(radius);
            this.volume = hitungVolume();
            this.luasPermukaan = hitungLuasPermukaan();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat Bola: " + e.getMessage());
        }
    }

    private void validateBolaInput(double radius) {
        if (radius <= MIN_VALUE) {
            throw new IllegalArgumentException("Radius bola " + ERROR_NEGATIVE + ": " + radius);
        }
    }

    @Override
    public String getNama() {
        return "Bola";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            luasPermukaan = 4 * PI * radius * radius;
            return luasPermukaan;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan bola: " + e.getMessage());
        }
    }

    public double hitungLuasPermukaan(double radiusBaru) {
        try {
            validateBolaInput(radiusBaru);
            luasPermukaan = 4 * PI * radiusBaru * radiusBaru;
            return luasPermukaan;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volume = (4.0 / 3.0) * PI * radius * radius * radius;
            return volume;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume bola: " + e.getMessage());
        }
    }

    public double hitungVolume(double radiusBaru) {
        try {
            validateBolaInput(radiusBaru);
            volume = (4.0 / 3.0) * PI * radiusBaru * radiusBaru * radiusBaru;
            return volume;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung volume: " + e.getMessage());
        }
    }

    public String getInfo() {
        try {
            return String.format("Bola{radius=%.2f, diameter=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                     radius, getDiameter(), volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info bola: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public double getRadius() {
        return radius;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double getVolume() {
        return volume;
    }
}