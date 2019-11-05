import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.LongAdder;

public class PhaserDriver {
	public static final LongAdder results = new LongAdder();
	public static void main(String[] args) throws InterruptedException {
		int threadSize = 50;
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Phaser phaser = new Phaser(50);
	
		PhaserTask task = new PhaserTask(phaser);
		PhaserTask selfRegisteringTask = new PhaserTask(phaser, true); 
		
		for (int i = 0; i < threadSize; i++) {
			executorService.submit(task);
			executorService.submit(selfRegisteringTask);
		}
		
		phaser.arriveAndAwaitAdvance();//If we do self registration
		phaser.awaitAdvance(50);//For no self registration
		executorService.shutdown();
		System.out.println("-----Main thread ended---------");
	}
}
