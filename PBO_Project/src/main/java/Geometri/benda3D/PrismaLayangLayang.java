package Geometri.benda3D;

import Geometri.BangunRuang;
import Geometri.benda2D.LayangLayang;

public class PrismaLayangLayang extends LayangLayang implements BangunRuang {
    private double tinggiPrisma; 
    private double luasPermukaan;
    private double volume;

    public PrismaLayangLayang(double diagonal1, double diagonal2, double tinggiPrisma) {
        super(diagonal1, diagonal2);
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public String getNama() {
        return "Prisma Layang-Layang";
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasDasar = hitungLuas();
        double sisiMiring = Math.sqrt((diagonal1 / 2) * (diagonal1 / 2) + tinggiPrisma * tinggiPrisma);
        double luasSelimut = 2 * (diagonal1 / 2) * sisiMiring + 2 * (diagonal2 / 2) * sisiMiring;
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
