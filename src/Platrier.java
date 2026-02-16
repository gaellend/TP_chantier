import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Platrier extends Thread {
    private List<Piece> maison;

    public Platrier(String nom, List<Piece> maison) {
        super(nom);
        this.maison = new ArrayList<>(maison);
        Collections.shuffle(this.maison);
    }

    @Override
    public void run() {
        for (Piece piece : maison) {
            try {
                piece.getSemaphoreElectrique().acquire();

                synchronized (piece) {
                    if (piece.isPlatree()) {
                        piece.getSemaphoreElectrique().release();
                        continue;
                    }
                    piece.setPlatree(true);
                }

                piece.getMutexOccupation().acquire();
                System.out.println("je commence à travailler : " + getName() + " (Plâtrier) sur " + piece.getNom());
                Thread.sleep(10000);
                System.out.println("j'ai fini mon travail : " + getName() + " (Plâtrier) sur " + piece.getNom());

                piece.getMutexOccupation().release();
                piece.getSemaphoreElectrique().release();

            } catch (InterruptedException e) {
                System.out.println(getName() + " interrompu");
            }
        }
    }
}