import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantDriver {
	public static final LongAdder results = new LongAdder();

	public static void main(String[] args) throws InterruptedException {
		int threadSize = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		
		ReentrantLock lock = new ReentrantLock();
		ReentrantTask task = new ReentrantTask(lock);
		for (int i = 0; i < threadSize; i++) {
			
			executorService.submit(task);
		}
		executorService.shutdown();
		System.out.println("-----Main thread ended---------");
	}
}
