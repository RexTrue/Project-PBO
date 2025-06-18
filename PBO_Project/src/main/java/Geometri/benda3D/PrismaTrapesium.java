package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Trapesium;

public class PrismaTrapesium extends Trapesium implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiPrisma;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double alasAtas, double alasBawah, double tinggi, double tinggiPrisma) {
        if (alasAtas <= MIN_VALUE) {
            throw new IllegalArgumentException("alasAtas " + ERROR_NEGATIVE + ": " + alasAtas);
        }
        if (alasBawah <= MIN_VALUE) {
            throw new IllegalArgumentException("alasBawah " + ERROR_NEGATIVE + ": " + alasBawah);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggi " + ERROR_NEGATIVE + ": " + tinggi);
        }
        if (tinggiPrisma <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiPrisma " + ERROR_NEGATIVE + ": " + tinggiPrisma);
        }
    }

    public PrismaTrapesium(double alasAtas, double alasBawah, double tinggi, double tinggiPrisma) {
        super(alasAtas, alasBawah, tinggi);
        try {
            validateInput(alasAtas, alasBawah, tinggi, tinggiPrisma);
            this.tinggiPrisma = tinggiPrisma;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat PrismaTrapesium: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Prisma Trapesium";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double kelilingDasar = hitungKeliling();
            double luasSelimut = kelilingDasar * tinggiPrisma;
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
            return String.format("PrismaTrapesium{alasAtas=%.2f, alasBawah=%.2f, tinggi=%.2f, tinggiPrisma=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.alasAtas, this.alasBawah, this.tinggi, this.tinggiPrisma, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info prismatrapesium: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
