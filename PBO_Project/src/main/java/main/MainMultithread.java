package main;

import Geometri.benda2D.Lingkaran;
import Geometri.benda3D.Bola;
import Geometri.threading.HitungLingkaranRunnable;
import Geometri.threading.HitungBolaRunnable;

public class MainMultithread {
    public static void main(String[] args) {
        try {
            Lingkaran lingkaran = new Lingkaran(7);
            Bola bola = new Bola(10);

            // Buat tugas runnable
            Runnable tugasLingkaran = new HitungLingkaranRunnable(lingkaran);
            Runnable tugasBola = new HitungBolaRunnable(bola);

            // Jalankan thread
            Thread thread1 = new Thread(tugasLingkaran, "Thread-Lingkaran");
            Thread thread2 = new Thread(tugasBola, "Thread-Bola");

            thread1.start();
            thread2.start();

            // Tunggu sampai selesai
            thread1.join();
            thread2.join();

            System.out.println("Semua perhitungan selesai.");

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
