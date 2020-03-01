import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserDriver {
	public static void main(String[] args) throws InterruptedException {
		int threadSize = 5;
		ExecutorService executorService = Executors.newFixedThreadPool(5);	
		boolean isCountDownLatch = false; // change to test
		boolean isCyclicBarrier = true;
		Phaser phaser = new Phaser(5);
		PhaserTask task = new PhaserTask(phaser, isCountDownLatch, isCyclicBarrier);
		for (int i = 0; i < threadSize; i++) {
			executorService.submit(task);
		}
		executorService.shutdown();
		
		if(isCountDownLatch) {
		System.out.println("---now waiting for countdown latch -----"+phaser.getPhase());
		phaser.awaitAdvance(0);
		System.out.println("-----Main thread ended---------");
		}
		if(isCyclicBarrier)
		{
			System.out.println("-----Main thread ended---------");	
		}
	}
}
