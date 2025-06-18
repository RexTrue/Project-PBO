package Geometri.benda3D;

import Geometri.benda2D.BelahKetupat;
import Geometri.BangunRuang;

public class PrismaBelahKetupat extends BelahKetupat implements BangunRuang {
    private double diagonal1;
    private double diagonal2;
    private double tinggiPrisma;
    private double luasPermukaan;
    private double volume;

     public PrismaBelahKetupat(double tinggiPrisma, double diagonal1, double diagonal2, double sisi) {
        super(diagonal1, diagonal2, sisi);
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public String getNama() {
        return "Prisma Belah Ketupat";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = (diagonal1 * diagonal2) / 2;
        double sisiMiring = Math.sqrt((diagonal1 / 2) * (diagonal1 / 2) + tinggiPrisma * tinggiPrisma);
        double luasSelimut = 4 * (diagonal1 / 2) * sisiMiring;
        luasPermukaan = luasDasar + luasSelimut;
        return luasPermukaan;
    }

    @Override
    public double hitungVolume() {
        volume = hitungLuas() * tinggiPrisma;
        return volume;
    }

    public double getTinggi() {
        return tinggiPrisma;
    }
    
}
