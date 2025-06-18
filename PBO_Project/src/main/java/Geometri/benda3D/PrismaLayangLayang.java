package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.LayangLayang;

public class PrismaLayangLayang extends LayangLayang implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiPrisma;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double diagonal1, double diagonal2, double tinggiPrisma) {
        if (diagonal1 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal1 " + ERROR_NEGATIVE + ": " + diagonal1);
        }
        if (diagonal2 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal2 " + ERROR_NEGATIVE + ": " + diagonal2);
        }
        if (tinggiPrisma <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiPrisma " + ERROR_NEGATIVE + ": " + tinggiPrisma);
        }
    }

    public PrismaLayangLayang(double diagonal1, double diagonal2, double tinggiPrisma) {
        super(diagonal1, diagonal2);
        try {
            validateInput(diagonal1, diagonal2, tinggiPrisma);
            this.tinggiPrisma = tinggiPrisma;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat PrismaLayangLayang: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Prisma Layang-Layang";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double sisiMiring = Math.sqrt((diagonal1 / 2) * (diagonal1 / 2) + tinggiPrisma * tinggiPrisma);
            double luasSelimut = 2 * (diagonal1 / 2) * sisiMiring + 2 * (diagonal2 / 2) * sisiMiring;
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
            return String.format("PrismaLayangLayang{diagonal1=%.2f, diagonal2=%.2f, tinggiPrisma=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.diagonal1, this.diagonal2, this.tinggiPrisma, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info prismalayanglayang: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
