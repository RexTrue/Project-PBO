package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Trapesium;

public class LimasTrapesium extends Trapesium implements BangunRuang {
    private double tinggiLimas; 
    private double luasPermukaan;
    private double volume;

    public LimasTrapesium(double alasAtas, double alasBawah, double tinggiTrapesium, double tinggiLimas) {
        super(alasAtas, alasBawah, tinggiTrapesium);
        this.tinggiLimas = tinggiLimas;
    }

    @Override
    public String getNama() {
        return "Limas Trapesium";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double sisiMiring = Math.sqrt((alasBawah - alasAtas) * (alasBawah - alasAtas) / 4 + tinggiLimas * tinggiLimas);
        double luasSelimut = (alasAtas + alasBawah) * sisiMiring;
        luasPermukaan = luasDasar + luasSelimut;
        return luasPermukaan;
    }

    @Override
    public double hitungVolume() {
        volume = (1.0 / 3.0) * hitungLuas() * tinggiLimas;
        return volume;
    }

    public double getTinggi() {
        return tinggiLimas;
    }
    
}
