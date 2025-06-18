package Geometri.threading;

import Geometri.benda3D.Bola;

public class HitungBolaRunnable implements Runnable {
    private Bola bola;

    public HitungBolaRunnable(Bola bola) {
        this.bola = bola;
    }

    @Override
    public void run() {
        synchronized (bola) {
            System.out.println(Thread.currentThread().getName() +
                " - Bola: r=" + bola.getRadius());
            System.out.println("  Volume: " + bola.hitungVolume());
            System.out.println("  Luas Permukaan: " + bola.hitungLuasPermukaan());
        }
    }
}
