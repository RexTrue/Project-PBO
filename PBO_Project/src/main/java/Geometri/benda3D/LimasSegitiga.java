package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Segitiga;

public class LimasSegitiga extends Segitiga implements BangunRuang {
    private double tinggiLimas; 
    private double luasPermukaan;
    private double volume;

    public LimasSegitiga(double alas, double tinggiSegitiga, double tinggiLimas) {
        super(alas, tinggiSegitiga);
        this.tinggiLimas = tinggiLimas;
    }

    @Override
    public String getNama() {
        return "Limas Segitiga";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double sisiMiring = Math.sqrt((alas / 2) * (alas / 2) + tinggiLimas * tinggiLimas);
        double luasSelimut = 3 * (alas / 2) * sisiMiring;
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
