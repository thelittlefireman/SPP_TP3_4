package Exercice2;

import java.util.concurrent.Exchanger;

import static java.lang.Thread.sleep;

/**
 * Created by Thomas on 29/03/2016.
 */
public class AliceBobBaseRunnable implements Runnable {
    public int nbIteration=0;
    Exchanger<String> exchanger;
    public String name;
    private String stringExchange;
    public  AliceBobBaseRunnable ( Exchanger<String> ex, String stringEx){
        exchanger =ex;
        stringExchange =stringEx;
    }
    @Override
    public void run() {
        while (nbIteration<3) {

                System.out.println("iteration : " + nbIteration +" "+name+ " speak : " + stringExchange );
                System.out.println("iteration : " + nbIteration +" "+name + " 'I go to sleep !'");
                try {
                    sleep((long) Math.random() * (5000),0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("iteration : " + nbIteration +" "+name + " 'I exchange string !'" );
                try {

                    stringExchange = exchanger.exchange(stringExchange);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("iteration : " + nbIteration +" "+name + " 'Exchange done !'");
            nbIteration++;

        }
    }
}
