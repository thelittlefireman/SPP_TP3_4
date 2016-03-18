package main;

import java.util.ArrayList;
import java.util.List;

import interfaces.SemaphoreInterface;

public class SemaphoreImplClass implements SemaphoreInterface {

	private static List<Thread>	blockedThread;
	private Integer				permits;
	private Integer 			threadWaiting;

	public SemaphoreImplClass() {
		this(0);
	}

	public SemaphoreImplClass(Integer permits) {
		blockedThread = new ArrayList<Thread>();
		this.permits = permits;
		this.threadWaiting = 0;
	}

	@Override
	public synchronized void up() {
			permits++;
			notify();
		}

	@Override
	public synchronized void down() {
			while(permits == 0)
			{
				// bloqué
				try {
					threadWaiting++;
					wait();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				threadWaiting--;
			}
			// ok
			permits--;
		}


	@Override
	public synchronized int releaseAll() {
		notifyAll();
		int tmpWaiting = threadWaiting;
		threadWaiting = 0;
		return tmpWaiting;
	}

}
