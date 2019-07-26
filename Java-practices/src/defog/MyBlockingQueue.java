package defog;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {

	private Queue<E> queue;
	private int max = 16;
	private ReentrantLock lock = new ReentrantLock(true);
	private Condition notEmtpy = lock.newCondition();
	private Condition notFull = lock.newCondition();

	public MyBlockingQueue(int size) {
		queue = new LinkedList<E>();
		this.max = size;
	}

	public void put(E e) throws InterruptedException {
		lock.lock();
		try {
			while (queue.size() == max) {
				notFull.await();
			}
			queue.add(e);
			notEmtpy.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public E take() throws InterruptedException {
		lock.lock();
		try {
			while (queue.size() == 0) {
				notEmtpy.wait();
			}
			notFull.signalAll();
			return queue.remove();
		} finally {
			lock.unlock();
		}
	}
}
