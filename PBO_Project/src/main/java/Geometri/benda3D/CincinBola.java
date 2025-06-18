package Geometri.benda3D;

public class CincinBola extends Bola{
    protected double radiusDalam;
    protected double luasPermukaanLuar;
    protected double luasPermukaanDalam;
    protected double volumeCincin;
    protected double volumeLuar;
    protected double volumeDalam;
    protected double luasPermukaanCincin;
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public CincinBola(double radius, double radiusDalam) {
        super(radius);
        try {
            validateCincinBolaInput(radius, radiusDalam);
            this.radiusDalam = radiusDalam;
            this.luasPermukaanLuar = hitungLuasPermukaanLuar();
            this.luasPermukaanDalam = hitungLuasPermukaanDalam();
            this.volumeLuar = hitungVolumeLuar();
            this.volumeDalam = hitungVolumeDalam();
            this.volumeCincin = hitungVolume();
            this.luasPermukaanCincin = hitungLuasPermukaan();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat CincinBola: " + e.getMessage());
        }
    }
    
    private void validateCincinBolaInput(double radius, double radiusDalam) {
        if (radius <= MIN_VALUE) {
            throw new IllegalArgumentException("Radius luar cincin bola " + ERROR_NEGATIVE + ": " + radius);
        }
        if (radiusDalam <= MIN_VALUE) {
            throw new IllegalArgumentException("Radius dalam cincin bola " + ERROR_NEGATIVE + ": " + radiusDalam);
        }
        if (radiusDalam >= radius) {
            throw new IllegalArgumentException("Radius dalam harus lebih kecil dari radius luar");
        }
    }

    @Override
    public String getNama() {
        return "Cincin Bola";
    }

    public double hitungLuasPermukaanLuar() {
        try {
            luasPermukaanLuar = 4 * PI * radius * radius;
            return luasPermukaanLuar;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan luar: " + e.getMessage());
        }
    }
    
    public double hitungLuasPermukaanDalam() {
        try {
            luasPermukaanDalam = 4 * PI * radiusDalam * radiusDalam;
            return luasPermukaanDalam;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan dalam: " + e.getMessage());
        }
    }

    public double hitungVolumeLuar() {
        try {
            volumeLuar = (4.0 / 3.0) * PI * radius * radius * radius;
            return volumeLuar;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume luar: " + e.getMessage());
        }
    }
    
    public double hitungVolumeDalam() {
        try {
            volumeDalam = (4.0 / 3.0) * PI * radiusDalam * radiusDalam * radiusDalam;
            return volumeDalam;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume dalam: " + e.getMessage());
        }
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            luasPermukaanCincin = luasPermukaanLuar - luasPermukaanDalam;
            return luasPermukaanCincin;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan cincin bola: " + e.getMessage());
        }
    }

    public double hitungLuasPermukaan(double radiusBaru, double radiusDalamBaru) {
        try {
            validateCincinBolaInput(radiusBaru, radiusDalamBaru);
            luasPermukaanCincin = 4 * PI * (radiusBaru * radiusBaru - radiusDalamBaru * radiusDalamBaru);
            return luasPermukaanCincin;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volumeCincin = volumeLuar - volumeDalam;
            return volumeCincin;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume cincin bola: " + e.getMessage());
        }
    }

    public double hitungVolume(double radiusBaru, double radiusDalamBaru) {
        try {
            validateCincinBolaInput(radiusBaru, radiusDalamBaru);
            volumeCincin = (4.0 / 3.0) * PI * (radiusBaru * radiusBaru * radiusBaru - radiusDalamBaru * radiusDalamBaru * radiusDalamBaru);
            return volumeCincin;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung volume: " + e.getMessage());
        }
    }
    
    public double getKetebalan() {
        try {
            return radius - radiusDalam;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung ketebalan: " + e.getMessage());
        }
    }

    public static double getKetebalan(double radiusBaru, double radiusDalamBaru) {
        try {
            if (radiusBaru <= MIN_VALUE || radiusDalamBaru <= MIN_VALUE) {
                throw new IllegalArgumentException("Radius " + ERROR_NEGATIVE);
            }
            if (radiusDalamBaru >= radiusBaru) {
                throw new IllegalArgumentException("Radius dalam harus lebih kecil dari radius luar");
            }
            return radiusBaru - radiusDalamBaru;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung ketebalan: " + e.getMessage());
        }
    }

    public String getInfo() {
        try {
            return String.format("CincinBola{radius=%.2f, radiusDalam=%.2f, ketebalan=%.2f, volume=%.2f, luasPermukaan=%.2f}", 
                               radius, radiusDalam, getKetebalan(), volumeCincin, luasPermukaanCincin);
        } catch (Exception e) {
            return "Error mendapatkan info cincin bola: " + e.getMessage();
        }
    }
    
    @Override
    public String toString() {
        return getInfo();
    }

    public double getRadius() {
        return radius;
    }

    public double getRadiusDalam() {
        return radiusDalam;
    }

    public double getVolumeCincin() {
        return volumeCincin;
    }

    public double getLuasPermukaanCincin() {
        return luasPermukaanCincin;
    }
}
