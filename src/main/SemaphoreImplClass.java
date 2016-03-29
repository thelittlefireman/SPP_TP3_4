package main;

import java.util.ArrayList;
import java.util.List;

import interfaces.SemaphoreInterface;

public class SemaphoreImplClass implements SemaphoreInterface {

    private int permits;

    public SemaphoreImplClass() {
        this(0);
    }

    public SemaphoreImplClass(Integer permits) {
        this.permits = permits;
    }

    @Override
    public synchronized void up() {
        permits++;
        if (permits <= 0)
            notify();
    }

    @Override
    public synchronized void down() {
        permits--;
        if (permits < 0) {
            // bloqué
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Blocage du thread impossible");
            }
        }
    }


    @Override
    public synchronized int releaseAll() {
        int tmpNotify = 0;
        if (permits <= 0) {
            tmpNotify += -permits;
            notifyAll();
            permits = 0;
        }
        return tmpNotify;
    }

}
