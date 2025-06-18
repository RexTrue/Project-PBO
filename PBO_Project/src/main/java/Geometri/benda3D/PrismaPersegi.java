package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Persegi;

public class PrismaPersegi extends Persegi implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiPrisma;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double sisi, double tinggiPrisma) {
        if (sisi <= MIN_VALUE) {
            throw new IllegalArgumentException("sisi " + ERROR_NEGATIVE + ": " + sisi);
        }
        if (tinggiPrisma <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiPrisma " + ERROR_NEGATIVE + ": " + tinggiPrisma);
        }
    }

    public PrismaPersegi(double sisi, double tinggiPrisma) {
        super(sisi);
        try {
            validateInput(sisi, tinggiPrisma);
            this.tinggiPrisma = tinggiPrisma;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat PrismaPersegi: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Prisma Persegi";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double luasSelimut = 4 * sisi * tinggiPrisma;
            luasPermukaan = 2 * luasDasar + luasSelimut;
            return luasPermukaan;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volume = hitungLuas() * tinggiPrisma;
            return volume;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume: " + e.getMessage());
        }
    }

    public double getTinggiPrisma() {
        return tinggiPrisma;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double getVolume() {
        return volume;
    }

    public String getInfo() {
        try {
            return String.format("PrismaPersegi{sisi=%.2f, tinggiPrisma=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.sisi, this.tinggiPrisma, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info prismapersegi: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
