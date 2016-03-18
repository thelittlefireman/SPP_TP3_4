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
	private Integer				actualThread;

	public CustomSemaphore() {
		this(0);
	}

	public CustomSemaphore(Integer permits) {
		blockedThread = new ArrayList<>();
		this.permits = permits;
		actualThread = 0;
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
			synchronized (actualThread) {
				if (actualThread + 1 <= permits) {
					// ok
				}
				else {
					blockedThread.add(this);
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
