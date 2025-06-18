package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Persegi;

public class LimasPersegi extends Persegi implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiLimas;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double sisi, double tinggiLimas) {
        if (sisi <= MIN_VALUE) {
            throw new IllegalArgumentException("sisi " + ERROR_NEGATIVE + ": " + sisi);
        }
        if (tinggiLimas <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiLimas " + ERROR_NEGATIVE + ": " + tinggiLimas);
        }
    }

    public LimasPersegi(double sisi, double tinggiLimas) {
        super(sisi);
        try {
            validateInput(sisi, tinggiLimas);
            this.tinggiLimas = tinggiLimas;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat LimasPersegi: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Limas Persegi";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double sisiMiring = Math.sqrt((sisi / 2) * (sisi / 2) + tinggiLimas * tinggiLimas);
            double luasSelimut = 4 * (sisi / 2) * sisiMiring;
            luasPermukaan = luasDasar + luasSelimut;
            return luasPermukaan;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volume = (1.0 / 3.0) * hitungLuas() * tinggiLimas;
            return volume;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume: " + e.getMessage());
        }
    }

    public double getTinggiLimas() {
        return tinggiLimas;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double getVolume() {
        return volume;
    }

    public String getInfo() {
        try {
            return String.format("LimasPersegi{sisi=%.2f, tinggiLimas=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.sisi, this.tinggiLimas, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info limaspersegi: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
