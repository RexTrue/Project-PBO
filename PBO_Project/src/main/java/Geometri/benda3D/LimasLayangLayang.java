package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.LayangLayang;

public class LimasLayangLayang extends LayangLayang implements BangunRuang {
    private double tinggiLimas; 
    private double luasPermukaan;
    private double volume;

    public LimasLayangLayang(double diagonal1, double diagonal2, double tinggiLimas) {
        super(diagonal1, diagonal2);
        this.tinggiLimas = tinggiLimas;
    }

    @Override
    public String getNama() {
        return "Limas Layang-Layang";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double sisiMiring = Math.sqrt((diagonal1 / 2) * (diagonal1 / 2) + tinggiLimas * tinggiLimas);
        double luasSelimut = 2 * (diagonal1 / 2) * sisiMiring + 2 * (diagonal2 / 2) * sisiMiring;
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
