package defog;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlocks {

	private Lock lockA = new ReentrantLock();
	private Lock lockB = new ReentrantLock();

	private void execute() {
		new Thread(() -> {
			try {
				processThis();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				processThat();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}

	public void processThis() throws InterruptedException {
		lockA.lock();
		lockB.lock();
		System.out.println("This : locks acquired");
		Thread.sleep(10000);
		lockA.unlock();
		lockB.unlock();
		System.out.println("This : locks released");
	}

	public void processThat() throws InterruptedException  {
		lockB.lock();
		lockA.lock();
		System.out.println("That : locks acquired");
		lockB.unlock();
		lockA.unlock();
		System.out.println("That : locks released");
	}

	public static void main(String[] args) {

		Deadlocks dl = new Deadlocks();
		dl.execute();
		
		boolean deadlock = false;
		while(!deadlock) {
			ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
			long[] threadIds = threadBean.findDeadlockedThreads();
			deadlock = threadIds !=null && threadIds.length > 0;
			System.out.println("Deadlocks found: " + deadlock);
		}
	}

}
