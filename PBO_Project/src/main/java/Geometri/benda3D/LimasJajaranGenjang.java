package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.JajaranGenjang;

public class LimasJajaranGenjang extends JajaranGenjang implements BangunRuang {
    private double tinggiLimas; 
    private double luasPermukaan;
    private double volume;

    public LimasJajaranGenjang(double alas, double tinggi, double tinggiLimas, double tinggiAlas, double sisiMiring) {
        super(alas, tinggiAlas, sisiMiring);
        this.tinggiLimas = tinggiLimas;
    }

    @Override
    public String getNama() {
        return "Limas Jajar Genjang";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double sisiMiring = Math.sqrt((alas / 2) * (alas / 2) + tinggi * tinggi);
        double luasSelimut = 2 * (alas / 2) * sisiMiring + 2 * (tinggi / 2) * sisiMiring;
        luasPermukaan = luasDasar + luasSelimut;
        return luasPermukaan;
    }

    @Override
    public double hitungVolume() {
        volume = (1.0 / 3.0) * hitungLuas() * tinggi;
        return volume;
    }

    public double getTinggi() {
        return tinggi;
    }
    
}
