package Geometri.benda3D;

import Geometri.benda2D.BelahKetupat;
import Geometri.BangunRuang;

public class LimasBelahKetupat extends BelahKetupat implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggi;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double diagonal1, double diagonal2, double tinggi, double sisi) {
        if (diagonal1 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal1 " + ERROR_NEGATIVE + ": " + diagonal1);
        }
        if (diagonal2 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal2 " + ERROR_NEGATIVE + ": " + diagonal2);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggi " + ERROR_NEGATIVE + ": " + tinggi);
        }
        if (sisi <= MIN_VALUE) {
            throw new IllegalArgumentException("sisi " + ERROR_NEGATIVE + ": " + sisi);
        }
    }

    public LimasBelahKetupat(double diagonal1, double diagonal2, double tinggi, double sisi) {
        super(diagonal1, diagonal2, sisi);
        try {
            validateInput(diagonal1, diagonal2, tinggi, sisi);
            this.tinggi = tinggi;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat LimasBelahKetupat: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Limas Belah Ketupat";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double sisiMiring = Math.sqrt((diagonal1 / 2) * (diagonal1 / 2) + tinggi * tinggi);
            double luasSelimut = 2 * (diagonal1 / 2) * sisiMiring + 2 * (diagonal2 / 2) * sisiMiring;
            luasPermukaan = luasDasar + luasSelimut;
            return luasPermukaan;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volume = (1.0 / 3.0) * hitungLuas() * tinggi;
            return volume;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume: " + e.getMessage());
        }
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

    public String getInfo() {
        try {
            return String.format("LimasBelahKetupat{diagonal1=%.2f, diagonal2=%.2f, sisi=%.2f, tinggi=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                diagonal1, diagonal2, sisi, tinggi, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info limasbelahketupat: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
