package Geometri.benda3D;

public class JuringBola extends Bola {
    private double sudut;

    public JuringBola(double radius, double sudut) {
        super(radius);
        this.sudut = sudut;
    }

    @Override
    public String getNama() {
        return "Juring Bola";
    }

    @Override
    public double hitungLuasPermukaan() {
        return (2 * PI * radius * radius * sudut / 360) + (2 * PI * radius * radius);
    }

    public double hitungLuasPermukaan(double radius, double sudut) {
        this.radius = radius;
        luasPermukaan = (2 * PI * radius * radius * sudut / 360) + (2 * PI * radius * radius);
        return luasPermukaan;
    }

    @Override
    public double hitungVolume() {
        return (2.0 / 3.0) * PI * radius * radius * radius * sudut / 360;
    }

    public double getSudut() {
        return sudut;
    }
    
}
