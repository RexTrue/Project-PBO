package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.JajaranGenjang;

public class LimasJajaranGenjang extends JajaranGenjang implements BangunRuang {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiLimas;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double alas, double tinggi, double tinggiLimas, double tinggiAlas, double sisiMiring) {
        if (alas <= MIN_VALUE) {
            throw new IllegalArgumentException("alas " + ERROR_NEGATIVE + ": " + alas);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggi " + ERROR_NEGATIVE + ": " + tinggi);
        }
        if (tinggiLimas <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiLimas " + ERROR_NEGATIVE + ": " + tinggiLimas);
        }
        if (tinggiAlas <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiAlas " + ERROR_NEGATIVE + ": " + tinggiAlas);
        }
        if (sisiMiring <= MIN_VALUE) {
            throw new IllegalArgumentException("sisiMiring " + ERROR_NEGATIVE + ": " + sisiMiring);
        }
    }

    public LimasJajaranGenjang(double alas, double tinggi, double tinggiLimas, double tinggiAlas, double sisiMiring) {
        super(alas, tinggiAlas, sisiMiring);
        try {
            validateInput(alas, tinggi, tinggiLimas, tinggiAlas, sisiMiring);
            this.tinggiLimas = tinggiLimas;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat LimasJajaranGenjang: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Limas Jajar Genjang";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasDasar = hitungLuas();
            double sisiMiring = Math.sqrt((alas / 2) * (alas / 2) + tinggi * tinggi);
            double luasSelimut = 2 * (alas / 2) * sisiMiring + 2 * (tinggi / 2) * sisiMiring;
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
            return String.format("LimasJajaranGenjang{alas=%.2f, tinggi=%.2f, tinggiLimas=%.2f, sisiMiring=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.alas, this.tinggi, this.tinggiLimas, this.sisiMiring, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info limasjajarangenjang: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
