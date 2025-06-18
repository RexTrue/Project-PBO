package Geometri.benda2D;

public class JuringLingkaran extends Lingkaran {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";
    
    protected double radius;
    protected double diameter;
    protected double sudut;
    protected double luas;
    protected double keliling;
    protected final double PI = 3.14;
    
    private void validateInput(double radius, double sudut) {
        if (radius <= MIN_VALUE) {
            throw new IllegalArgumentException("radius " + ERROR_NEGATIVE + ": " + radius);
        }
        if (sudut <= MIN_VALUE) {
            throw new IllegalArgumentException("sudut " + ERROR_NEGATIVE + ": " + sudut);
        }
    }

    public JuringLingkaran(double radius, double sudut) {
        super(radius);
        try {
            validateInput(radius, sudut);
            this.radius = radius;
            this.sudut = sudut;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat JuringLingkaran: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Juring Lingkaran";
    }

    @Override
    public double hitungLuas() {
        try {
            if (radius == 0) {
                luas = (PI * (diameter / 2) * (diameter / 2) * sudut) / 360;
            } else {
                luas = (PI * radius * radius * sudut) / 360;
            }
            return luas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas: " + e.getMessage());
        }
    }
    
    @Override
    public double hitungKeliling() {
        try {
            if (radius == 0) {
                keliling = (2 * PI * (diameter / 2) * sudut) / 360 + diameter;
            } else {
                keliling = (2 * PI * radius * sudut) / 360 + 2 * radius;
            }
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

    public double getRadius() {
        return radius;
    }

    public double getSudut() {
        return sudut;
    }

    public String getInfo() {
        try {
            return String.format("JuringLingkaran{radius=%.2f, sudut=%.2f, luas=%.2f, keliling=%.2f}", 
                               radius, sudut, luas, keliling);
        } catch (Exception e) {
            return "Error mendapatkan info juringlingkaran: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public static class JuringLingkaranRunnable implements Runnable {
        private final double radius, sudut;
        private double luas, keliling;

        public JuringLingkaranRunnable(double radius, double sudut) {
            this.radius = radius;
            this.sudut = sudut;
        }

        @Override
        public void run() {
            JuringLingkaran juring = new JuringLingkaran(radius, sudut);
            luas = juring.hitungLuas();
            keliling = juring.hitungKeliling();
        }

        public double getLuas() { return luas; }
        public double getKeliling() { return keliling; }
    }
}
