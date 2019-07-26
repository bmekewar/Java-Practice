package defog;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public static void main(String[] args) throws InterruptedException {

		LockConditionTest test = new LockConditionTest();
		
		new Thread(() ->{
			try {
				test.method1();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		new Thread(() ->{
			try {
				test.method2();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public void method1() throws InterruptedException {
		lock.lock();
		try {
			condition.await();
			System.out.println("Awaiting");
		} finally {
			lock.unlock();
		}
	}

	public void method2() throws InterruptedException {
		lock.lock();
		try {
			System.out.println("Signaling");
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
