package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Trapesium;

public class LimasTrapesium extends Trapesium implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiLimas;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double alasAtas, double alasBawah, double tinggiTrapesium, double tinggiLimas) {
        if (alasAtas <= MIN_VALUE) {
            throw new IllegalArgumentException("alasAtas " + ERROR_NEGATIVE + ": " + alasAtas);
        }
        if (alasBawah <= MIN_VALUE) {
            throw new IllegalArgumentException("alasBawah " + ERROR_NEGATIVE + ": " + alasBawah);
        }
        if (tinggiTrapesium <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiTrapesium " + ERROR_NEGATIVE + ": " + tinggiTrapesium);
        }
        if (tinggiLimas <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiLimas " + ERROR_NEGATIVE + ": " + tinggiLimas);
        }
    }

    public LimasTrapesium(double alasAtas, double alasBawah, double tinggiTrapesium, double tinggiLimas) {
        super(alasAtas, alasBawah, tinggiTrapesium);
        try {
            validateInput(alasAtas, alasBawah, tinggiTrapesium, tinggiLimas);
            this.tinggiLimas = tinggiLimas;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat LimasTrapesium: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Limas Trapesium";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double sisiMiring = Math.sqrt((alasBawah - alasAtas) * (alasBawah - alasAtas) / 4 + tinggiLimas * tinggiLimas);
            double luasSelimut = (alasAtas + alasBawah) * sisiMiring;
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
            return String.format("LimasTrapesium{alasAtas=%.2f, alasBawah=%.2f, tinggiTrapesium=%.2f, tinggiLimas=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.alasAtas, this.alasBawah, this.tinggi, this.tinggiLimas, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info limastrapesium: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
