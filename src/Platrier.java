public class Platrier extends Thread {
    public Platrier(String nom) {
        super(nom);
    }

    public void travailler() {
        try {
            System.out.println("je commence à travailler : " + getName() + " (Plâtrier)");
            Thread.sleep(10000); // 10 secondes d'attente
            System.out.println("j'ai fini mon travail : " + getName() + " (Plâtrier)");
        } catch (InterruptedException e) {
            System.out.println(getName() + " a été interrompu");
        }
    }

    @Override
    public void run() {
        travailler();
    }
}