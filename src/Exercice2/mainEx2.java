package Exercice2;

import java.util.concurrent.Exchanger;

/**
 * Created by Thomas on 29/03/2016.
 */
public class mainEx2 {
   static Exchanger<String> exchanger;
static String mainString="pong";
    public static void main(String[] args) {
        exchanger = new Exchanger<String>();
        Thread t1 = new Thread(new Alice(exchanger,"ping"));
        Thread t2 = new Thread(new Bob(exchanger,"pong"));
        t1.start();
        t2.start();
    }
}
