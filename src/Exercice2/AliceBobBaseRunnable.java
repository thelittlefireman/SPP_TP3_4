package Exercice2;

import java.util.concurrent.Exchanger;

import static java.lang.Thread.sleep;

/**
 * Created by Thomas on 29/03/2016.
 */

/**
 *
 */
public class AliceBobBaseRunnable implements Runnable {
    public int nbIteration = 0;
    Exchanger<String> exchanger;
    public String name;
    private String stringExchange;

    /**
     * @param exchanger : Exchanger permettant d'échanger les données entre les deux threads
     * @param stringEx  : Donnée à échanger
     */
    public AliceBobBaseRunnable(Exchanger<String> exchanger, String stringEx) {
        this.exchanger = exchanger;
        this.stringExchange = stringEx;
    }

    @Override
    public void run() {
        //pour 3 itérations
        while (nbIteration < 3) {

            System.out.println("iteration : " + nbIteration + " " + name + " speak : " + stringExchange);
            System.out.println("iteration : " + nbIteration + " " + name + " 'I go to sleep !'");
            //on l'endore pour 5000 millisecondes * un nombre aléatoire
            try {
                sleep((long) Math.random() * (5000), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("iteration : " + nbIteration + " " + name + " 'I exchange string !'");
            try {
//on échange nos variables : stringReçus = exchanger.exchange(stringADonner)
                stringExchange = exchanger.exchange(stringExchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("iteration : " + nbIteration + " " + name + " 'Exchange done !'");
            //on incrémente le nombre de tours
            nbIteration++;

        }
    }
}
