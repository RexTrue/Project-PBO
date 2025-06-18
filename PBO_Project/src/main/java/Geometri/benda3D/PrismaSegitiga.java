package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Segitiga;

public class PrismaSegitiga extends Segitiga implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiPrisma;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double alas, double tinggi, double tinggiPrisma) {
        if (alas <= MIN_VALUE) {
            throw new IllegalArgumentException("alas " + ERROR_NEGATIVE + ": " + alas);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggi " + ERROR_NEGATIVE + ": " + tinggi);
        }
        if (tinggiPrisma <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiPrisma " + ERROR_NEGATIVE + ": " + tinggiPrisma);
        }
    }

    public PrismaSegitiga(double alas, double tinggi, double tinggiPrisma) {
        super(alas, tinggi);
        try {
            validateInput(alas, tinggi, tinggiPrisma);
            this.tinggiPrisma = tinggiPrisma;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat PrismaSegitiga: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Prisma Segitiga";
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
            return String.format("PrismaSegitiga{alas=%.2f, tinggi=%.2f, tinggiPrisma=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.alas, this.tinggi, this.tinggiPrisma, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info prismasegitiga: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
