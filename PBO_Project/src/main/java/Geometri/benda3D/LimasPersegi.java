package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Persegi;

public class LimasPersegi extends Persegi implements BangunRuang {
    private double tinggiLimas; 
    private double luasPermukaan;
    private double volume;

    public LimasPersegi(double sisi, double tinggiLimas) {
        super(sisi);
        this.tinggiLimas = tinggiLimas;
    }

    @Override
    public String getNama() {
        return "Limas Persegi";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double sisiMiring = Math.sqrt((sisi / 2) * (sisi / 2) + tinggiLimas * tinggiLimas);
        double luasSelimut = 4 * (sisi / 2) * sisiMiring;
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
