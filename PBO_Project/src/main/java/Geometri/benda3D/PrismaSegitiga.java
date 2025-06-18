package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Segitiga;

public class PrismaSegitiga extends Segitiga implements BangunRuang {
    private double tinggiPrisma; 
    private double luasPermukaan;
    private double volume;

    public PrismaSegitiga(double alas, double tinggi, double tinggiPrisma) {
        super(alas, tinggi);
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public String getNama() {
        return "Prisma Segitiga";
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
