package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.PersegiPanjang;

public class LimasPersegiPanjang extends PersegiPanjang implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiLimas;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double panjang, double lebar, double tinggiLimas) {
        if (panjang <= MIN_VALUE) {
            throw new IllegalArgumentException("panjang " + ERROR_NEGATIVE + ": " + panjang);
        }
        if (lebar <= MIN_VALUE) {
            throw new IllegalArgumentException("lebar " + ERROR_NEGATIVE + ": " + lebar);
        }
        if (tinggiLimas <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiLimas " + ERROR_NEGATIVE + ": " + tinggiLimas);
        }
    }

    public LimasPersegiPanjang(double panjang, double lebar, double tinggiLimas) {
        super(panjang, lebar);
        try {
            validateInput(panjang, lebar, tinggiLimas);
            this.tinggiLimas = tinggiLimas;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat LimasPersegiPanjang: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Limas Persegi Panjang";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double sisiMiring = Math.sqrt((panjang / 2) * (panjang / 2) + tinggiLimas * tinggiLimas);
            double luasSelimut = 2 * (panjang / 2) * sisiMiring + 2 * (lebar / 2) * sisiMiring;
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
            return String.format("LimasPersegiPanjang{panjang=%.2f, lebar=%.2f, tinggiLimas=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.panjang, this.lebar, this.tinggiLimas, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info limaspersegipanjang: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
