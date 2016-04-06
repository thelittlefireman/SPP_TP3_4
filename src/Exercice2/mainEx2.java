package Exercice2;

import java.util.concurrent.Exchanger;

/**
 * Created by Thomas on 29/03/2016.
 */
public class mainEx2 {
   static Exchanger<String> exchanger;
    public static void main(String[] args) {
        //On initialise nos variables
        //Alice commence pas ping
        //Bob par pong
        exchanger = new Exchanger<String>();
        Thread t1 = new Thread(new Alice(exchanger,"ping"));
        Thread t2 = new Thread(new Bob(exchanger,"pong"));
        //On Execute les deux threads
        t1.start();
        t2.start();
    }
}
