package Geometri.benda3D;

public class TemberengBola extends Bola {
    private double tinggiTembereng;
    private double radiusDasar;

    public TemberengBola(double radius, double tinggiTembereng) {
        super(radius);
        this.tinggiTembereng = tinggiTembereng;
        this.radiusDasar = Math.sqrt(radius * radius - tinggiTembereng * tinggiTembereng);
    }

    @Override
    public String getNama() {
        return "Tembereng Bola";
    }

    @Override
    public double hitungLuasPermukaan() {
        return 2 * PI * radius * tinggiTembereng + PI * radiusDasar * radiusDasar;
    }

    @Override
    public double hitungVolume() {
        return (1.0 / 3.0) * PI * tinggiTembereng * (radiusDasar * radiusDasar + radiusDasar * tinggiTembereng + tinggiTembereng * tinggiTembereng);
    }

    public double getTinggiTembereng() {
        return tinggiTembereng;
    }

    public double getRadiusDasar() {
        return radiusDasar;
    }
    
}
