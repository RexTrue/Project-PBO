package Geometri.benda2D;

public class TemberengLingkaran extends Lingkaran {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    protected double taliBusur;
    protected double radius;
    protected double luas;
    protected double keliling;

    private void validateInput(double taliBusur, double radius) {
        if (taliBusur <= MIN_VALUE) {
            throw new IllegalArgumentException("taliBusur " + ERROR_NEGATIVE + ": " + taliBusur);
        }
        if (radius <= MIN_VALUE) {
            throw new IllegalArgumentException("radius " + ERROR_NEGATIVE + ": " + radius);
        }
    }

    public TemberengLingkaran(double taliBusur, double radius) {
        super(radius);
        try {
            validateInput(taliBusur, radius);
            this.taliBusur = taliBusur;
            this.radius = radius;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat TemberengLingkaran: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Tembereng Lingkaran";
    }

    @Override
    public double hitungLuas() {
        try {
            luas = (PI * radius * radius) / 2;
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas: " + e.getMessage());
        }
    }

    @Override
    public double hitungKeliling() {
        try {
            keliling = 2 * Math.PI * radius;
            return keliling;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung keliling: " + e.getMessage());
        }
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

    public double getradius() {
        return radius;
    }

    public String getInfo() {
        try {
            return String.format("TemberengLingkaran{taliBusur=%.2f, radius=%.2f, luas=%.2f, keliling=%.2f}",
                taliBusur, radius, luas, keliling);
        } catch (Exception e) {
            return "Error mendapatkan info temberenglingkaran: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public static class TemberengLingkaranRunnable implements Runnable {
        private final double taliBusur, radius;
        private double luas, keliling;

        public TemberengLingkaranRunnable(double taliBusur, double radius) {
            this.taliBusur = taliBusur;
            this.radius = radius;
        }

        @Override
        public void run() {
            TemberengLingkaran tembereng = new TemberengLingkaran(taliBusur, radius);
            luas = tembereng.hitungLuas();
            keliling = tembereng.hitungKeliling();
        }

        public double getLuas() { return luas; }
        public double getKeliling() { return keliling; }
    }
}
