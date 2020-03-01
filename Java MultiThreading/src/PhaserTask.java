import java.util.Random;
import java.util.concurrent.Phaser;

public class PhaserTask implements Runnable {
	Phaser phaser;
	boolean isCountDownLatch;
	boolean isCyclicBarrier;
	
	


	public PhaserTask(Phaser phaser, boolean isCountDownLatch, boolean isCyclicBarrier) {
		super();
		this.phaser = phaser;
		this.isCountDownLatch = isCountDownLatch;
		this.isCyclicBarrier = isCyclicBarrier;
	}




	@Override
	public void run() {
		int i = 0;
		try {
			
			if(isCountDownLatch)
			{
				//ACT AS COUNT DOWN LATCH
				System.out.println("Thread arrived: " + Thread.currentThread().getId());
				Thread.sleep(new Random().nextInt(2000));
				phaser.arrive(); // just like countdown()
				System.out.println("Thread going away after countdown: " + Thread.currentThread().getId());
			}
			else if(isCyclicBarrier)
			{
				while(i<4)
				{i++;
				Thread.sleep(new Random().nextInt(2000));
				System.out.println(phaser.getPhase()+"-Phase- Thread arrived: " + Thread.currentThread().getId()+"---arrived party---"+phaser.getArrivedParties());
				phaser.arriveAndAwaitAdvance();// just like await()
				System.out.println("Thread going away after countdown: " + Thread.currentThread().getId());
				}
				}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
