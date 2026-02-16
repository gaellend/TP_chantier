import java.util.concurrent.Semaphore;

public class Piece {
    private String nom;
    // Sémaphore pour l'ordre (0 = attend l'élec, 1 = élec fini)
    private Semaphore semaphoreElectrique = new Semaphore(0);
    // Sémaphore pour l'occupation (1 = libre, 0 = occupé)
    private Semaphore mutexOccupation = new Semaphore(1);

    private boolean estElectrifiee = false;
    private boolean estPlatree = false;

    public Piece(String nom) {
        this.nom = nom;
    }

    public String getNom() { return nom; }
    public Semaphore getSemaphoreElectrique() { return semaphoreElectrique; }
    public Semaphore getMutexOccupation() { return mutexOccupation; }

    public synchronized boolean isElectrifiee() { return estElectrifiee; }
    public synchronized void setElectrifiee(boolean etat) { this.estElectrifiee = etat; }
    public synchronized boolean isPlatree() { return estPlatree; }
    public synchronized void setPlatree(boolean etat) { this.estPlatree = etat; }
}