package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaces.SemaphoreInterface;

public class CustomSemaphore implements SemaphoreInterface {

	private static List<Thread>	blockedThread;
	private final Lock			lock	= new ReentrantLock();
	private Integer				permits;

	public CustomSemaphore() {
		this(0);
	}

	public CustomSemaphore(Integer permits) {
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
				// bloqué
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
		// TODO Auto-generated method stub
		return 0;
	}

}
