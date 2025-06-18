package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.JajaranGenjang;

public class PrismaJajaranGenjang extends JajaranGenjang implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiPrisma;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double alas, double tinggi, double tinggiPrisma, double tinggiAlas, double sisiMiring) {
        if (alas <= MIN_VALUE) {
            throw new IllegalArgumentException("alas " + ERROR_NEGATIVE + ": " + alas);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggi " + ERROR_NEGATIVE + ": " + tinggi);
        }
        if (tinggiPrisma <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiPrisma " + ERROR_NEGATIVE + ": " + tinggiPrisma);
        }
        if (tinggiAlas <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiAlas " + ERROR_NEGATIVE + ": " + tinggiAlas);
        }
        if (sisiMiring <= MIN_VALUE) {
            throw new IllegalArgumentException("sisiMiring " + ERROR_NEGATIVE + ": " + sisiMiring);
        }
    }

    public PrismaJajaranGenjang(double alas, double tinggi, double tinggiPrisma, double tinggiAlas, double sisiMiring) {
        super(alas, tinggiAlas, sisiMiring);
        try {
            validateInput(alas, tinggi, tinggiPrisma, tinggiAlas, sisiMiring);
            this.tinggiPrisma = tinggiPrisma;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat PrismaJajaranGenjang: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Prisma Jajar Genjang";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double luasSelimut = 2 * (alas + tinggi) * tinggiPrisma;
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
            return String.format("PrismaJajaranGenjang{alas=%.2f, tinggi=%.2f, tinggiPrisma=%.2f, sisiMiring=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.alas, this.tinggi, this.tinggiPrisma, this.sisiMiring, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info prismajajarangenjang: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
