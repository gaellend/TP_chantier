public class Main {
    public static void main(String[] args) {
        // 1. Création des ouvriers
        Electricien e1 = new Electricien("Electricien 1");
        Electricien e2 = new Electricien("Electricien 2");
        Electricien e3 = new Electricien("Electricien 3");
        Platrier p1 = new Platrier("Platrier 1");
        Platrier p2 = new Platrier("Platrier 2");

        // 2. Lancement des threads
        e1.start(); e2.start(); e3.start();
        p1.start(); p2.start();

        try {
            // 3. On attend que tout le monde termine
            e1.join(); e2.join(); e3.join();
            p1.join(); p2.join();

            System.out.println("Tous les ouvriers ont fini leur travail");
        } catch (InterruptedException e) {
            System.out.println("Le thread principal a été interrompu");
        }
    }
}