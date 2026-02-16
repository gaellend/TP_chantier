import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Electricien extends Thread {
    private List<Piece> maison;

    public Electricien(String nom, List<Piece> maison) {
        super(nom);
        // Création d'une copie locale pour un parcours aléatoire propre à cet ouvrier
        this.maison = new ArrayList<>(maison);
        Collections.shuffle(this.maison);
    }

    @Override
    public void run() {
        for (Piece piece : maison) {
            synchronized (piece) {
                if (piece.isElectrifiee()) continue;
                piece.setElectrifiee(true);
            }

            try {
                piece.getMutexOccupation().acquire();
                System.out.println("je commence à travailler : " + getName() + " (Électricien) sur " + piece.getNom());
                Thread.sleep(5000);
                System.out.println("j'ai fini mon travail : " + getName() + " (Électricien) sur " + piece.getNom());

                piece.getMutexOccupation().release();
                piece.getSemaphoreElectrique().release();

            } catch (InterruptedException e) {
                System.out.println(getName() + " interrompu");
            }
        }
    }
}