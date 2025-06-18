package Geometri.benda2D;

import Geometri.BangunDatar;

public class Lingkaran extends BangunDatar {
    public double radius;
    public double luas;
    public double keliling;
    public final double PI = 3.14;

    public Lingkaran(double radius) {
        this.radius = radius;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama() {
        return "Lingkaran";
    }

    @Override
    public double hitungLuas() {
        luas = PI * radius * radius;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        keliling = 2 * PI * radius;
        return keliling;
    }

    public double getLuas() {
        return luas;
    }

    public double getKeliling() {
        return keliling;
    }

    public double getRadius() {
        return radius;
    }
    
}
