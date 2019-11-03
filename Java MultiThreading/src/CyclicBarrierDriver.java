import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class CyclicBarrierDriver {
	public static final LongAdder results = new LongAdder();

	public static void main(String[] args) throws InterruptedException {
		int threadSize = 50;
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		CyclicBarrier cyclicbarrier = new CyclicBarrier(threadSize, new Runnable() {
			@Override
			public void run() {

				System.out.println("----All threadre crossed barrier------------");
				System.out.println("----Comletion thread ended---------with Result : "
						+ CyclicBarrierDriver.results.sum());
			}
		});
		CyclicBarrierTask task = new CyclicBarrierTask(cyclicbarrier);
		for (int i = 0; i < threadSize; i++) {
			executorService.submit(task);
		}
		executorService.shutdown();
		System.out.println("-----Main thread ended---------");
	}
}
