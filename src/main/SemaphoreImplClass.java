package main;

import interfaces.SemaphoreInterface;

/**
 *
 */
public class SemaphoreImplClass implements SemaphoreInterface {
    /**
     *
     */
    private int permits;

    /**
     *
     */
    public SemaphoreImplClass() {
        this(0);
    }

    /**
     * Constructeur du sémaphore :
     * @param permits : nombre de thread autorisé en même temps
     */
    public SemaphoreImplClass(int permits) {
        this.permits = permits;
    }

    /**
     * Libérer un jeton du Sémaphore
     */
    public synchronized void up() {
        //On libère un jeton : on libère une place
        permits++;
        //si on est au max du nombre de thread passé alors on a forcément des threads en attente que nous devons réveiller
        if (permits <= 0)
            //on réveille les threads en attentes
            notify();
    }

    /**
     * Prendre un jeton du sémaphore
     */

    public synchronized void down() {
        //On prends un jeton : on prends une place
        permits--;
        //si le nombre de places est inférieur à 0, c'est qu'elles ont toutes été utilisées
        redoTest:if (permits < 0) {
            //On doit donc bloqué notre thread
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Blocage du thread impossible");
            }finally {
                //Pour éviter les problèmes de concurence de plusieurs thread réveiller
                // on fait en sorte qu'en sorti de blocage notre thread reteste si elle a bien la permissions de continuer
                break redoTest;
            }
        }
    }

    public synchronized int releaseAll() {
        //Compteur du nombre de thread réveillé
        int tmpNotify = 0;
        //si on est au max du nombre de jeton utilisés alors on a forcément des threads en attente que nous devons réveiller
        if (permits <= 0) {
            //Notre variable permits est donc négative
            // on dois donc prendre son inverse ce qui nous donne le nombre de thread libéré
            tmpNotify += -permits;
            notifyAll();
            permits = 0;
        }
        //on renvoie le nombre de thread réveillé
        return tmpNotify;
    }

}
