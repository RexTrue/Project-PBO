package Geometri.benda3D;

import Geometri.benda2D.Lingkaran;
import Geometri.BangunRuang;

public class Bola extends Lingkaran implements BangunRuang {
    public double radius;
    public double luasPermukaan;
    public double volume;

    public Bola(double radius) {
        super(radius);
        this.radius = radius;
    }

    @Override
    public String getNama() {
        return "Bola";
    }

    @Override
    public double hitungLuasPermukaan() {
        luasPermukaan = 4 * PI * radius * radius;
        return luasPermukaan;
    }

    // Overload: hitung luas permukaan dengan radius baru (tanpa mengubah atribut radius)
    public double hitungLuasPermukaan(double radiusBaru) {
        return 4 * PI * radiusBaru * radiusBaru;
    }

    @Override
    public double hitungVolume() {
        volume = (4.0 / 3.0) * PI * radius * radius * radius;
        return volume;
    }

    // Overload: hitung volume dengan radius baru (tanpa mengubah atribut radius)
    public double hitungVolume(double radiusBaru) {
        return (4.0 / 3.0) * PI * radiusBaru * radiusBaru * radiusBaru;
    }
    
    public double getRadius() {
        return radius;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double getVolume() {
        return volume;
    }
    
}