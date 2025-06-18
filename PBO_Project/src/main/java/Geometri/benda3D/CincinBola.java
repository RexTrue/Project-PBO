package Geometri.benda3D;

public class CincinBola extends Bola{
    private double radiusLuar;
    private double radiusDalam;

    public CincinBola(double radiusLuar, double radiusDalam) {
        super(radiusLuar);
        this.radiusDalam = radiusDalam;
    }

    @Override
    public String getNama() {
        return "Cincin Bola";
    }

    @Override
    public double hitungLuasPermukaan() {
        return 4 * PI * (Math.pow(radiusLuar, 2) + Math.pow(radiusDalam, 2));
    }

    @Override
    public double hitungVolume() {
        return (4.0 / 3.0) * PI * (Math.pow(radiusLuar, 3) - Math.pow(radiusDalam, 3));
    }

    public double getRadiusLuar() {
        return radiusLuar;
    }

    public double getRadiusDalam() {
        return radiusDalam;
    }
    
}
