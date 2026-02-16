import java.util.List;

public class Electricien extends Thread {
    private List<Piece> maison;

    public Electricien(String nom, List<Piece> maison) {
        super(nom);
        this.maison = maison;
    }

    @Override
    public void run() {
        for (Piece piece : maison) {
            synchronized (piece) {
                if (piece.isElectrifiee()) continue;
                piece.setElectrifiee(true); // Réservation pour éviter les doublons
            }

            try {
                // On attend que la pièce soit libre
                piece.getMutexOccupation().acquire();

                System.out.println("je commence à travailler : " + getName() + " (Électricien) sur " + piece.getNom());
                Thread.sleep(5000);
                System.out.println("j'ai fini mon travail : " + getName() + " (Électricien) sur " + piece.getNom());

                // On libère la pièce et on donne le feu vert au plâtrier
                piece.getMutexOccupation().release();
                piece.getSemaphoreElectrique().release();

            } catch (InterruptedException e) {
                System.out.println(getName() + " interrompu");
            }
        }
    }
}