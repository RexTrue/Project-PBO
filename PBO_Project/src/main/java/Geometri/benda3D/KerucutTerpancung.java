package Geometri.benda3D;

public class KerucutTerpancung extends Kerucut {
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    private double radiusAtas;
    private double luasPermukaan;
    private double volume;

    private void validateInput(double radiusBawah, double radiusAtas, double tinggi) {
        if (radiusBawah <= MIN_VALUE) {
            throw new IllegalArgumentException("radiusBawah " + ERROR_NEGATIVE + ": " + radiusBawah);
        }
        if (radiusAtas <= MIN_VALUE) {
            throw new IllegalArgumentException("radiusAtas " + ERROR_NEGATIVE + ": " + radiusAtas);
        }
        if (tinggi <= MIN_VALUE) {
            throw new IllegalArgumentException("tinggi " + ERROR_NEGATIVE + ": " + tinggi);
        }
    }

    public KerucutTerpancung(double radiusBawah, double radiusAtas, double tinggi) {
        super(radiusBawah, tinggi);
        try {
            validateInput(radiusBawah, radiusAtas, tinggi);
            this.radiusAtas = radiusAtas;
            this.luasPermukaan = hitungLuasPermukaan();
            this.volume = hitungVolume();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat KerucutTerpancung: " + e.getMessage());
        }
    }

    @Override
    public String getNama() {
        return "Kerucut Terpancung";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double sisiMiring = Math.sqrt((radius - radiusAtas) * (radius - radiusAtas) + tinggi * tinggi);
            double luasSelimut = PI * (radius + radiusAtas) * sisiMiring;
            double luasDasarBawah = PI * radius * radius;
            double luasDasarAtas = PI * radiusAtas * radiusAtas;
            luasPermukaan = luasSelimut + luasDasarBawah + luasDasarAtas;
            return luasPermukaan;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volume = (1.0 / 3.0) * PI * tinggi * (radius * radius + radius * radiusAtas + radiusAtas * radiusAtas);
            return volume;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume: " + e.getMessage());
        }
    }

    public double getRadiusAtas() {
        return radiusAtas;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double getVolume() {
        return volume;
    }

    public String getInfo() {
        try {
            return String.format("KerucutTerpancung{radius=%.2f, radiusAtas=%.2f, tinggi=%.2f, volume=%.2f, luasPermukaan=%.2f}",
                radius, radiusAtas, tinggi, volume, luasPermukaan);
        } catch (Exception e) {
            return "Error mendapatkan info kerucutterpancung: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
