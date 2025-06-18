package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.JajaranGenjang;

public class PrismaJajaranGenjang extends JajaranGenjang implements BangunRuang {
    private double tinggiPrisma; 
    private double luasPermukaan;
    private double volume;

    public PrismaJajaranGenjang(double alas, double tinggi, double tinggiPrisma, double tinggiAlas, double sisiMiring) {
        super(alas, tinggiAlas, sisiMiring);
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public String getNama() {
        return "Prisma Jajar Genjang";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double luasSelimut = 2 * (alas + tinggi) * tinggiPrisma;
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
