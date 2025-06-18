package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.PersegiPanjang;

public class LimasPersegiPanjang extends PersegiPanjang implements BangunRuang {
    private double tinggiLimas; 
    private double luasPermukaan;
    private double volume;

    public LimasPersegiPanjang(double panjang, double lebar, double tinggiLimas) {
        super(panjang, lebar);
        this.tinggiLimas = tinggiLimas;
    }

    @Override
    public String getNama() {
        return "Limas Persegi Panjang";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double sisiMiring = Math.sqrt((panjang / 2) * (panjang / 2) + tinggiLimas * tinggiLimas);
        double luasSelimut = 2 * (panjang / 2) * sisiMiring + 2 * (lebar / 2) * sisiMiring;
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
