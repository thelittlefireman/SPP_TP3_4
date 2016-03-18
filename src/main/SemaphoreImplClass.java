package main;

import java.util.ArrayList;
import java.util.List;

import interfaces.SemaphoreInterface;

public class SemaphoreImplClass implements SemaphoreInterface {

	private Integer				permits;
	private Integer 			threadWaiting;

	public SemaphoreImplClass() {
		this(0);
	}

	public SemaphoreImplClass(Integer permits) {
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
					threadWaiting--;
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println("Blocage du thread impossible");
				}
			}
			// ok
			permits--;
		}


	@Override
	public int releaseAll() {
		int tmpWaiting = threadWaiting;
		while(threadWaiting != 0)
			up();
		return tmpWaiting;
	}

}
