import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SimpaphoreTask implements Runnable {

	Semaphore semaphore;

	public SimpaphoreTask(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			
			semaphore.acquire();
			System.out.println("Thread accuquired semaphore: " + Thread.currentThread().getId());
			Thread.sleep(new Random().nextInt(2000));
			System.out.println("Thread  : " + Thread.currentThread().getId() + " release semaphore");
			semaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
