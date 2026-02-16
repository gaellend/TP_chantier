import java.util.List;

public class Platrier extends Thread {
    private List<Piece> maison;

    public Platrier(String nom, List<Piece> maison) {
        super(nom);
        this.maison = maison;
    }

    @Override
    public void run() {
        for (Piece piece : maison) {
            try {
                // 1. On attend que l'électricité soit finie
                piece.getSemaphoreElectrique().acquire();

                synchronized (piece) {
                    if (piece.isPlatree()) {
                        piece.getSemaphoreElectrique().release(); // On rend le jeton pour les autres
                        continue;
                    }
                    piece.setPlatree(true);
                }

                // 2. On attend que la pièce soit libre
                piece.getMutexOccupation().acquire();

                System.out.println("je commence à travailler : " + getName() + " (Plâtrier) sur " + piece.getNom());
                Thread.sleep(10000);
                System.out.println("j'ai fini mon travail : " + getName() + " (Plâtrier) sur " + piece.getNom());

                // On libère la pièce et on remet le jeton électrique
                piece.getMutexOccupation().release();
                piece.getSemaphoreElectrique().release();

            } catch (InterruptedException e) {
                System.out.println(getName() + " interrompu");
            }
        }
    }
}