package Geometri.benda3D;

public class KerucutTerpancung extends Kerucut {
    private double radiusAtas;
    private double luasPermukaan;
    private double volume;

    public KerucutTerpancung(double radiusBawah, double radiusAtas, double tinggi) {
        super(radiusBawah, tinggi);
        this.radiusAtas = radiusAtas;
    }

    @Override
    public String getNama() {
        return "Kerucut Terpancung";
    }

    @Override
    public double hitungLuasPermukaan() {
        double sisiMiring = Math.sqrt((radius - radiusAtas) * (radius - radiusAtas) + tinggi * tinggi);
        double luasSelimut = PI * (radius + radiusAtas) * sisiMiring;
        double luasDasarBawah = PI * radius * radius;
        double luasDasarAtas = PI * radiusAtas * radiusAtas;

        luasPermukaan = luasSelimut + luasDasarBawah + luasDasarAtas;
        return luasPermukaan;
    }

    @Override
    public double hitungVolume() {
        volume = (1.0 / 3.0) * PI * tinggi * (radius * radius + radius * radiusAtas + radiusAtas * radiusAtas);
        return volume;
    }

    public double getRadiusAtas() {
        return radiusAtas;
    }
    
}
