package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.Persegi;

public class PrismaPersegi extends Persegi implements BangunRuang {
    private double tinggiPrisma; 
    private double luasPermukaan;
    private double volume;

    public PrismaPersegi(double sisi, double tinggiPrisma) {
        super(sisi);
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public String getNama() {
        return "Prisma Persegi";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double luasSelimut = 4 * sisi * tinggiPrisma;
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
