package Geometri.benda3D;

public class JuringBola extends Bola {
    private double sudut;
    private double volumeJuring;
    private double luasPermukaanJuring;
    private double luasSelimut;
    private double luasAlas;
    private double panjangBusur;

    private static final double MIN_VALUE = 0.0;
    private static final double MAX_SUDUT = 360.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";

    public JuringBola(double radius, double sudut) {
        super(radius);
        try {
            validateJuringBolaInput(radius, sudut);
            this.sudut = sudut;
            this.volumeJuring = hitungVolume();
            this.luasPermukaanJuring = hitungLuasPermukaan();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error membuat JuringBola: " + e.getMessage());
        }
    }

    private void validateJuringBolaInput(double radius, double sudut) {
        if (radius <= MIN_VALUE) {
            throw new IllegalArgumentException("Radius juring bola " + ERROR_NEGATIVE + ": " + radius);
        }
        if (sudut <= MIN_VALUE || sudut > MAX_SUDUT) {
            throw new IllegalArgumentException("Sudut juring harus antara 0° dan 360°: " + sudut);
        }
    }

    @Override
    public String getNama() {
        return "Juring Bola";
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            luasSelimut = 2 * PI * radius * radius * sudut / 360;
            luasAlas = PI * radius * radius;
            luasPermukaanJuring = luasSelimut + luasAlas;
            return luasPermukaanJuring;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas permukaan juring bola: " + e.getMessage());
        }
    }

    public double hitungLuasPermukaan(double radiusBaru, double sudutBaru) {
        try {
            validateJuringBolaInput(radiusBaru, sudutBaru);
            luasSelimut = 2 * PI * radiusBaru * radiusBaru * sudutBaru / 360;
            luasAlas = PI * radiusBaru * radiusBaru;
            luasPermukaanJuring = luasSelimut + luasAlas;
            return luasPermukaanJuring;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    @Override
    public double hitungVolume() {
        try {
            volumeJuring = (2.0 / 3.0) * PI * radius * radius * radius * sudut / 360;
            return volumeJuring;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung volume juring bola: " + e.getMessage());
        }
    }

    public double hitungVolume(double radiusBaru, double sudutBaru) {
        try {
            validateJuringBolaInput(radiusBaru, sudutBaru);
            volumeJuring = (2.0 / 3.0) * PI * radiusBaru * radiusBaru * radiusBaru * sudutBaru / 360;
            return volumeJuring;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung volume: " + e.getMessage());
        }
    }

    public double hitungVolume(double volumeBola) {
        try {
            if (volumeBola <= 0) {
                throw new IllegalArgumentException("Volume bola tidak boleh negatif atau nol");
            }
            volumeJuring = (2.0 / 3.0) * volumeBola * sudut / 360;
            return volumeJuring;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung volume: " + e.getMessage());
        }
    }

    public double hitungLuasPermukaan(double luasPermukaanBola) {
        try {
            if (luasPermukaanBola <= 0) {
                throw new IllegalArgumentException("Luas permukaan bola tidak boleh negatif atau nol");
            }
            luasSelimut = (sudut / 360.0) * luasPermukaanBola;
            luasAlas = PI * radius * radius;
            luasPermukaanJuring = luasSelimut + luasAlas;
            return luasPermukaanJuring;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung luas permukaan: " + e.getMessage());
        }
    }

    public double hitungLuasSelimut() {
        try {
            luasSelimut = 2 * PI * radius * radius * sudut / 360;
            return luasSelimut;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas selimut juring: " + e.getMessage());
        }
    }

    public double hitungLuasAlas() {
        try {
            luasAlas = PI * radius * radius;
            return luasAlas;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung luas alas juring: " + e.getMessage());
        }
    }

    public double getPanjangBusur() {
        try {
            double kelilingLingkaran = 2 * PI * radius;
            panjangBusur = (sudut / 360.0) * kelilingLingkaran;
            return panjangBusur;
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung panjang busur: " + e.getMessage());
        }
    }

    public double getPanjangBusur(double radiusBaru, double sudutBaru) {
        try {
            if (radiusBaru <= MIN_VALUE) {
                throw new IllegalArgumentException("Radius " + ERROR_NEGATIVE);
            }
            if (sudutBaru <= MIN_VALUE || sudutBaru > MAX_SUDUT) {
                throw new IllegalArgumentException("Sudut juring harus antara 0° dan 360°");
            }
            double kelilingLingkaran = 2 * PI * radiusBaru;
            panjangBusur = (sudutBaru / 360.0) * kelilingLingkaran;
            return panjangBusur;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error menghitung panjang busur: " + e.getMessage());
        }
    }

    public String getInfo() {
        try {
            return String.format("JuringBola{radius=%.2f, sudut=%.2f°, volume=%.2f, luasPermukaan=%.2f}", 
                               radius, sudut, volumeJuring, luasPermukaanJuring);
        } catch (Exception e) {
            return "Error mendapatkan info juring bola: " + e.getMessage();
        }
    }
    
    @Override
    public String toString() {
        return getInfo();
    }

    public double getRadius() {
        return radius;
    }

    public double getSudut() {
        return sudut;
    }

    public double getVolumeJuring() {
        return volumeJuring;
    }

    public double getLuasPermukaanJuring() {
        return luasPermukaanJuring;
    }

    public double getPanjangBusurValue() {
        return panjangBusur;
    }
}
