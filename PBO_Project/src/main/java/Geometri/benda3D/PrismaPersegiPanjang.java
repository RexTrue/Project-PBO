package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.PersegiPanjang;

public class PrismaPersegiPanjang extends PersegiPanjang implements BangunRuang {
    private double tinggiPrisma; 
    private double luasPermukaan;
    private double volume;

    public PrismaPersegiPanjang(double panjang, double lebar, double tinggiPrisma) {
        super(panjang, lebar);
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public String getNama() {
        return "Prisma Persegi Panjang";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double luasSelimut = 2 * (panjang + lebar) * tinggiPrisma;
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
