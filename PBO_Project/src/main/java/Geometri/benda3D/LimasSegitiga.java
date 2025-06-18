package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Segitiga;

public class LimasSegitiga extends Segitiga implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiLimas;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double alas, double tinggiSegitiga, double tinggiLimas) {
        if (alas <= MIN_VALUE) {
            throw new IllegalArgumentException("alas " + ERROR_NEGATIVE + ": " + alas);
        }
        if (tinggiSegitiga <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiSegitiga " + ERROR_NEGATIVE + ": " + tinggiSegitiga);
        }
        if (tinggiLimas <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiLimas " + ERROR_NEGATIVE + ": " + tinggiLimas);
        }
    }

    public LimasSegitiga(double alas, double tinggiSegitiga, double tinggiLimas) {
        super(alas, tinggiSegitiga);
        try {
            validateInput(alas, tinggiSegitiga, tinggiLimas);
            this.tinggiLimas = tinggiLimas;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat LimasSegitiga: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Limas Segitiga";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double sisiMiring = Math.sqrt((alas / 2) * (alas / 2) + tinggiLimas * tinggiLimas);
            double luasSelimut = 3 * (alas / 2) * sisiMiring;
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
            return String.format("LimasSegitiga{alas=%.2f, tinggiSegitiga=%.2f, tinggiLimas=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.alas, this.tinggi, this.tinggiLimas, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info limassegitiga: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
