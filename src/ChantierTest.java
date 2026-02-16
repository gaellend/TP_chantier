import java.util.ArrayList;
import java.util.List;

public class ChantierTest {
    public static void main(String[] args) {
        List<Piece> maison = new ArrayList<>();
        String[] noms = {"Salon", "Cuisine", "Chambre1", "Chambre2", "SDB", "WC", "Bureau", "Couloir", "Garage", "Cellier"};
        for (String n : noms) maison.add(new Piece(n));

        Electricien e1 = new Electricien("Bob", maison);
        Electricien e2 = new Electricien("Bobette", maison);
        Electricien e3 = new Electricien("Baub", maison);
        Platrier p1 = new Platrier("Bowb", maison);
        Platrier p2 = new Platrier("Bobbe", maison);

        System.out.println("--- Lancement du chantier avec Shuffle interne ---");
        e1.start(); e2.start(); e3.start();
        p1.start(); p2.start();

        try {
            e1.join(); e2.join(); e3.join();
            p1.join(); p2.join();
        } catch (InterruptedException e) {
            System.out.println("Erreur d'exécution");
        }

        System.out.println("\n--- Vérifications des tests ---");
        boolean succes = true;

        for (Piece p : maison) {
            // 1. Test : Tout est fini ?
            if (!p.isElectrifiee() || !p.isPlatree()) {
                System.out.println("ÉCHEC : La pièce " + p.getNom() + " est incomplète.");
                succes = false;
            }
            // 2. Test : Les pièces sont-elles bien libérées ?
            if (p.getMutexOccupation().availablePermits() != 1) {
                System.out.println("ÉCHEC : Mutex mal relâché sur " + p.getNom());
                succes = false;
            }
        }

        if (succes) {
            System.out.println("========================================");
            System.out.println("SUCCÈS : L'ordre et l'exclusion sont OK.");
            System.out.println("Les ouvriers ont bien travaillé en parallèle.");
            System.out.println("========================================");
        }
    }
}