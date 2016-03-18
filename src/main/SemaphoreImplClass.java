package main;

import java.util.ArrayList;
import java.util.List;

import interfaces.SemaphoreInterface;

public class SemaphoreImplClass implements SemaphoreInterface {

	private static List<Thread>	blockedThread;
	private Integer				permits;

	public SemaphoreImplClass() {
		this(0);
	}

	public SemaphoreImplClass(Integer permits) {
		blockedThread = new ArrayList<>();
		this.permits = permits;
	}

	@Override
	public void up() {
		synchronized (permits) {
			permits++;
			notify();
		}
	}

	@Override
	public void down() {
		synchronized (permits) {
			if (permits != 0) {
				// ok
				permits--;
			}
			else {
				// bloqu�
				try {
					wait();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public int releaseAll() {
		notifyAll();
		// TODO Auto-generated method stub
		return 0;
	}

}
