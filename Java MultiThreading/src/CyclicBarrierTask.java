import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTask implements Runnable {

	CyclicBarrier cyclicBarrier;

	public CyclicBarrierTask(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		int i = 0;
		while(i<4) {
			i++;
		try {
			Thread.sleep(new Random().nextInt(2000));
			int returnValue = new Random().nextInt(10);
			System.out.println(
					"Thread arrived : " + Thread.currentThread().getId() + " with return value : " + returnValue);
			CyclicBarrierDriver.results.add(returnValue);
			cyclicBarrier.await();
			System.out.println("Thread  : " + Thread.currentThread().getId() + " crossed barrier");
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}
}
