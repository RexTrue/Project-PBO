package Geometri.benda3D;

import Geometri.benda2D.Lingkaran;
import Geometri.BangunRuang;

public class Kerucut extends Lingkaran implements BangunRuang {
    public double tinggi;
    public double luasPermukaan;
    public double volume;

    public Kerucut(double radius, double tinggi) {
        super(radius);
        this.tinggi = tinggi;
    }

    @Override
    public String getNama() {
        return "Kerucut";
    }

    @Override
    public double hitungLuasPermukaan() {
        double sisiMiring = Math.sqrt(radius * radius + tinggi * tinggi);
        luasPermukaan = PI * radius * (radius + sisiMiring);
        return luasPermukaan;
    }

    @Override
    public double hitungVolume() {
        volume = (1.0 / 3.0) * PI * radius * radius * tinggi;
        return volume;
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
    
}
