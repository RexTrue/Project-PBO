package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Lingkaran;

public class Tabung extends Lingkaran implements BangunRuang {
    private double tinggiTabung;
    private double luasPermukaan;
    private double volume;

    public Tabung(double radius, double tinggiTabung) {
        super(radius);
        this.tinggiTabung = tinggiTabung;
    }

    @Override
    public String getNama() {
        return "Tabung";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double luasSelimut = 2 * PI * radius * tinggiTabung;
        luasPermukaan = 2 * luasDasar + luasSelimut;
        return luasPermukaan;
    }

    @Override
    public double hitungVolume() {
        volume = hitungLuas() * tinggiTabung;
        return volume;
    }

    public double getTinggi() {
        return tinggiTabung;
    }
    
}
