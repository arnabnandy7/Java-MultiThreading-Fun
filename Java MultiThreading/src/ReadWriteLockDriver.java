import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriteLockDriver {

	public static void main(String[] args) throws InterruptedException {
		int threadSize = 50;
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		ReadLock readLock = readWriteLock.readLock();
		WriteLock writeLock = readWriteLock.writeLock();

		ReadWriteLockTask readTask = new ReadWriteLockTask(true, readLock, writeLock);
		ReadWriteLockTask writeTask = new ReadWriteLockTask(false, readLock, writeLock);
		for (int i = 0; i < threadSize; i++) {
			executorService.submit(readTask);
			executorService.submit(writeTask);
		}
		executorService.shutdown();
		System.out.println("-----Main thread ended---------");
	}
}
