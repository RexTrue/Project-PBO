package Geometri.benda3D;

public class TemberengBola extends Bola {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double tinggiTembereng;
    private double radiusDasar;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double radius, double tinggiTembereng) {
        if (radius <= MIN_VALUE) {
            throw new IllegalArgumentException("radius " + ERROR_NEGATIVE + ": " + radius);
        }
        if (tinggiTembereng <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggiTembereng " + ERROR_NEGATIVE + ": " + tinggiTembereng);
        }
    }

    public TemberengBola(double radius, double tinggiTembereng) {
        super(radius);
        try {
            validateInput(radius, tinggiTembereng);
            this.tinggiTembereng = tinggiTembereng;
            this.radiusDasar = Math.sqrt(radius * radius - tinggiTembereng * tinggiTembereng);
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat TemberengBola: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Tembereng Bola";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            luasPermukaan = 2 * PI * radius * tinggiTembereng + PI * radiusDasar * radiusDasar;
            return luasPermukaan;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volume = (1.0 / 3.0) * PI * tinggiTembereng * (radiusDasar * radiusDasar + radiusDasar * tinggiTembereng + tinggiTembereng * tinggiTembereng);
            return volume;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume: " + e.getMessage());
        }
    }

    public double getTinggiTembereng() {
        return tinggiTembereng;
    }

    public double getRadiusDasar() {
        return radiusDasar;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double getVolume() {
        return volume;
    }

    public String getInfo() {
        try {
            return String.format("TemberengBola{radius=%.2f, tinggiTembereng=%.2f, radiusDasar=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                this.radius, this.tinggiTembereng, this.radiusDasar, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info temberengbola: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
