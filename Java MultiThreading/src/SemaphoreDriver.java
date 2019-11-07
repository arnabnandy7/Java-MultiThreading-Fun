import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

public class SemaphoreDriver {
	public static final LongAdder results = new LongAdder();

	public static void main(String[] args) throws InterruptedException {
		int threadSize = 10;
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Semaphore semaphore = new Semaphore(3, true);
		SimpaphoreTask task = new SimpaphoreTask(semaphore);
		for (int i = 0; i < threadSize; i++) {
			executorService.submit(task);
		}
		executorService.shutdown();
		System.out.println("-----Main thread ended---------");
	}
}
