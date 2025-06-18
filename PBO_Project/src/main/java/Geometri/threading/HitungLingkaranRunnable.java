package Geometri.threading;

import Geometri.benda2D.Lingkaran;

public class HitungLingkaranRunnable implements Runnable {
    private Lingkaran lingkaran;

    public HitungLingkaranRunnable(Lingkaran lingkaran) {
        this.lingkaran = lingkaran;
    }

    @Override
    public void run() {
        synchronized (lingkaran) {
            System.out.println(Thread.currentThread().getName() +
                " - Lingkaran: r=" + lingkaran.getRadius());
            System.out.println("  Luas: " + lingkaran.hitungLuas());
            System.out.println("  Keliling: " + lingkaran.hitungKeliling());
        }
    }
}
