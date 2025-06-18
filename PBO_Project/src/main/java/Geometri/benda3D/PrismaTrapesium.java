package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Trapesium;

public class PrismaTrapesium extends Trapesium implements BangunRuang {
    private double tinggiPrisma; 
    private double luasPermukaan;
    private double volume;

    public PrismaTrapesium(double alasAtas, double alasBawah, double tinggi, double tinggiPrisma) {
        super(alasAtas, alasBawah, tinggi);
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public String getNama() {
        return "Prisma Trapesium";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double kelilingDasar = hitungKeliling();
        double luasSelimut = kelilingDasar * tinggiPrisma;
        luasPermukaan = 2 * luasDasar + luasSelimut;
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
