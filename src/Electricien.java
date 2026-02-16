public class Electricien extends Thread {
    public Electricien(String nom) {
        super(nom);
    }

    public void travailler() {
        try {
            System.out.println("je commence à travailler : " + getName() + " (Électricien)");
            Thread.sleep(5000); // Attente de 5 secondes
            System.out.println("j'ai fini mon travail : " + getName() + " (Électricien)");
        } catch (InterruptedException e) {
            System.out.println(getName() + " a été interrompu");
        }
    }

    @Override
    public void run() {
        travailler();
    }
}