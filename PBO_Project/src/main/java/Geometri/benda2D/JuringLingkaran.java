package Geometri.benda2D;

import Geometri.BangunDatar;

public class JuringLingkaran extends BangunDatar {
    public double radius;
    public double diameter;
    public double sudut;
    public double luas;
    public double keliling;
    public final double PI = 3.14;

    public JuringLingkaran(double radius, double sudut) {
        this.radius = radius;
        this.sudut = sudut;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama() {
        return "Juring Lingkaran";
    }

    @Override
    public double hitungLuas() {
        if (radius == 0) {
            luas = (PI * (diameter / 2) * (diameter / 2) * sudut) / 360;
        } else {
            luas = (PI * radius * radius * sudut) / 360;
        }
        return luas;
    }
    
    @Override
    public double hitungKeliling() {
        if (radius == 0) {
            keliling = (2 * PI * (diameter / 2) * sudut) / 360 + diameter;
        } else {
            keliling = (2 * PI * radius * sudut) / 360 + 2 * radius;
        }
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

    public double getSudut() {
        return sudut;
    }

}
