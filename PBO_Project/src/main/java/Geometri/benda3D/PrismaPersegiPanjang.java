package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.PersegiPanjang;

public class PrismaPersegiPanjang extends PersegiPanjang implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiPrisma;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double panjang, double lebar, double tinggiPrisma) {
        if (panjang <= MIN_VALUE) {
            throw new IllegalArgumentException("panjang " + ERROR_NEGATIVE + ": " + panjang);
        }
        if (lebar <= MIN_VALUE) {
            throw new IllegalArgumentException("lebar " + ERROR_NEGATIVE + ": " + lebar);
        }
        if (tinggiPrisma <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiPrisma " + ERROR_NEGATIVE + ": " + tinggiPrisma);
        }
    }

    public PrismaPersegiPanjang(double panjang, double lebar, double tinggiPrisma) {
        super(panjang, lebar);
        try {
            validateInput(panjang, lebar, tinggiPrisma);
            this.tinggiPrisma = tinggiPrisma;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat PrismaPersegiPanjang: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Prisma Persegi Panjang";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double luasSelimut = 2 * (panjang + lebar) * tinggiPrisma;
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
            return String.format("PrismaPersegiPanjang{panjang=%.2f, lebar=%.2f, tinggiPrisma=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.panjang, this.lebar, this.tinggiPrisma, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info prismapersegipanjang: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
