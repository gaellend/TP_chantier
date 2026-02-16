import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Piece> maison = new ArrayList<>();
        String[] noms = {"Salon", "Cuisine", "Chambre1", "Chambre2", "SDB", "WC", "Bureau", "Couloir", "Garage", "Cellier"};
        for (String n : noms) maison.add(new Piece(n));

        Electricien e1 = new Electricien("Bob", maison);
        Electricien e2 = new Electricien("Bobette", maison);
        Electricien e3 = new Electricien("Baub", maison);
        Platrier p1 = new Platrier("Bowb", maison);
        Platrier p2 = new Platrier("Bobbe", maison);

        e1.start(); e2.start(); e3.start();
        p1.start(); p2.start();

        try {
            e1.join(); e2.join(); e3.join();
            p1.join(); p2.join();
            System.out.println("la maison est terminée");
        } catch (InterruptedException e) {
            System.out.println("Erreur main");
        }
    }
}