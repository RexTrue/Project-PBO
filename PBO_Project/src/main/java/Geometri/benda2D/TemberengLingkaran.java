package Geometri.benda2D;
import Geometri.BangunDatar;

public class TemberengLingkaran extends BangunDatar{
    public double taliBusur;
    public double jariJari;
    public double luas;
    public double keliling;
    
    public TemberengLingkaran(double taliBusur, double jariJari) {
        this.taliBusur = taliBusur;
        this.jariJari = jariJari;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public String getNama() {
        return "Tembereng Lingkaran";
    }

    @Override
    public double hitungLuas() {
        luas = (Math.PI * jariJari * jariJari) / 2; // Asumsi luas tembereng adalah setengah luas lingkaran
        return luas;
    }

    @Override
    public double hitungKeliling() {
        keliling = 2 * Math.PI * jariJari; // Keliling lingkaran penuh
        return keliling;
    }

    public double getLuas() {
        return luas;
    }
    
    public double getKeliling() {
        return keliling;
    }

    public double getTaliBusur() {
        return taliBusur;
    }
}
