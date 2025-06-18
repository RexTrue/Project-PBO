package Geometri.benda2D;
import Geometri.BangunDatar;

public class JajaranGenjang extends BangunDatar {
    public double alas;
    public double tinggi;
    public double luas;
    public double keliling;
    public double sisiMiring;
    public JajaranGenjang(double alas, double tinggi, double sisiMiring) {
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama() {
        return "Jajaran Genjang";
    }
    @Override
    public double hitungLuas() {
        luas = alas * tinggi;
        return luas;
    }
    @Override
    public double hitungKeliling() {
        keliling = 2 * (alas + sisiMiring);
        return keliling;
    }

    public double getLuas() {
        return luas;
    }
    public double getKeliling() {
        return keliling;
    }
    public double getAlas() {
        return alas;
    }
    public double getTinggi() {
        return tinggi;
    }
    public double getSisiMiring() {
        return sisiMiring;
    }
}
