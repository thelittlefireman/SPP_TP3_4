package Exercice2;

import java.util.concurrent.Exchanger;

/**
 * Created by Thomas on 29/03/2016.
 */
public class Bob extends AliceBobBaseRunnable {
    public Bob(Exchanger<String> ex,String mainString){
        super(ex,mainString);
        name = "bob  ";
    }

}
