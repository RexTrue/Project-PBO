package Geometri.benda3D;

import Geometri.benda2D.BelahKetupat;
import Geometri.BangunRuang;

public class LimasBelahKetupat extends BelahKetupat implements BangunRuang {
    private double tinggi;
    private double luasPermukaan;
    private double volume;

    public LimasBelahKetupat(double diagonal1, double diagonal2, double tinggi, double sisi) {
        super(diagonal1, diagonal2, sisi);
        this.tinggi = tinggi;
    }

    @Override
    public String getNama() {
        return "Limas Belah Ketupat";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double sisiMiring = Math.sqrt((diagonal1 / 2) * (diagonal1 / 2) + tinggi * tinggi);
        double luasSelimut = 2 * (diagonal1 / 2) * sisiMiring + 2 * (diagonal2 / 2) * sisiMiring;
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
