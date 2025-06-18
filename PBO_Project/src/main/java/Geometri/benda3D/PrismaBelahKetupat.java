package Geometri.benda3D;

import Geometri.benda2D.BelahKetupat;
import Geometri.BangunRuang;

public class PrismaBelahKetupat extends BelahKetupat implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiPrisma;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double tinggiPrisma, double diagonal1, double diagonal2, double sisi) {
        if (tinggiPrisma <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiPrisma " + ERROR_NEGATIVE + ": " + tinggiPrisma);
        }
        if (diagonal1 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal1 " + ERROR_NEGATIVE + ": " + diagonal1);
        }
        if (diagonal2 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal2 " + ERROR_NEGATIVE + ": " + diagonal2);
        }
        if (sisi <= MIN_VALUE) {
            throw new IllegalArgumentException("sisi " + ERROR_NEGATIVE + ": " + sisi);
        }
    }

    public PrismaBelahKetupat(double tinggiPrisma, double diagonal1, double diagonal2, double sisi) {
        super(diagonal1, diagonal2, sisi);
        try {
            validateInput(tinggiPrisma, diagonal1, diagonal2, sisi);
            this.tinggiPrisma = tinggiPrisma;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat PrismaBelahKetupat: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Prisma Belah Ketupat";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = (diagonal1 * diagonal2) / 2;
            double sisiMiring = Math.sqrt((diagonal1 / 2) * (diagonal1 / 2) + tinggiPrisma * tinggiPrisma);
            double luasSelimut = 4 * (diagonal1 / 2) * sisiMiring;
            luasPermukaan = luasDasar + luasSelimut;
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
            return String.format("PrismaBelahKetupat{diagonal1=%.2f, diagonal2=%.2f, sisi=%.2f, tinggiPrisma=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.diagonal1, this.diagonal2, this.sisi, this.tinggiPrisma, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info prismabelahketupat: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
