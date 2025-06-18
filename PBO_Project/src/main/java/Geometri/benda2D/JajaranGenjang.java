package Geometri.benda2D;
import Geometri.BangunDatar;

public class JajaranGenjang extends BangunDatar {
    protected double alas;
    protected double tinggi;
    protected double luas;
    protected double keliling;
    protected double sisiMiring;
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public JajaranGenjang(double alas, double tinggi, double sisiMiring) {
        try {
            validateInput(alas, tinggi);
            this.alas = alas;
            this.tinggi = tinggi;
            this.sisiMiring = sisiMiring;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat JajaranGenjang: " + e.getMessage());
        }
    }

    private void validateInput(double alas, double tinggi) {
        if (alas <= MIN_VALUE) {
            throw new IllegalArgumentException("Alas jajaran genjang " + ERROR_NEGATIVE + ": " + alas);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("Tinggi jajaran genjang " + ERROR_NEGATIVE + ": " + tinggi);
        }
    }

    @Override
    public String getNama() {
        return "Jajaran Genjang";
    }
    @Override
    public double hitungLuas() {
        try {
            luas = alas * tinggi;
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas jajaran genjang: " + e.getMessage());
        }
    }
    @Override
    public double hitungKeliling() {
        try {
            keliling = 2 * (alas + sisiMiring);
            return keliling;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung keliling jajaran genjang: " + e.getMessage());
        }
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

    public String getInfo() {
        try {
            return String.format("JajaranGenjang{alas=%.2f, tinggi=%.2f, luas=%.2f, keliling=%.2f}",
                     alas, tinggi, luas, keliling);
        } catch (Exception e) {
            return "Error mendapatkan info jajaran genjang: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
