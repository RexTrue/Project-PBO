package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.LayangLayang;

public class LimasLayangLayang extends LayangLayang implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiLimas;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double diagonal1, double diagonal2, double tinggiLimas) {
        if (diagonal1 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal1 " + ERROR_NEGATIVE + ": " + diagonal1);
        }
        if (diagonal2 <= MIN_VALUE) {
            throw new IllegalArgumentException("diagonal2 " + ERROR_NEGATIVE + ": " + diagonal2);
        }
        if (tinggiLimas <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiLimas " + ERROR_NEGATIVE + ": " + tinggiLimas);
        }
    }

    public LimasLayangLayang(double diagonal1, double diagonal2, double tinggiLimas) {
        super(diagonal1, diagonal2);
        try {
            validateInput(diagonal1, diagonal2, tinggiLimas);
            this.tinggiLimas = tinggiLimas;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat LimasLayangLayang: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Limas Layang-Layang";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double sisiMiring = Math.sqrt((diagonal1 / 2) * (diagonal1 / 2) + tinggiLimas * tinggiLimas);
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
            return String.format("LimasLayangLayang{diagonal1=%.2f, diagonal2=%.2f, tinggiLimas=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.diagonal1, this.diagonal2, this.tinggiLimas, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info limaslayanglayang: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
