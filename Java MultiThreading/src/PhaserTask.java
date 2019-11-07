import java.util.Random;
import java.util.concurrent.Phaser;

public class PhaserTask implements Runnable {
	Phaser phaser;
	boolean isSelfRegistering;

	//constructors
	public PhaserTask(Phaser phaser) {
		this.phaser = phaser;
	}
	
	
	

	public PhaserTask(Phaser phaser, boolean isSelfRegistering) {
		super();
		this.phaser = phaser;
		this.isSelfRegistering = isSelfRegistering;
	}




	@Override
	public void run() {
		try {
			
			if(isSelfRegistering)
			{
				//Thread registering itself to phaser
				phaser.register();
				Thread.sleep(new Random().nextInt(2000));
				int returnValue = new Random().nextInt(10);
				System.out.println(
						"Thread arrived : " + Thread.currentThread().getId() + " with return value : " + returnValue);
				PhaserDriver.results.add(returnValue);
				phaser.arriveAndDeregister();
				System.out.println("Thread  : " + Thread.currentThread().getId() + " crossed barrier has arrived and de registered");
			}
			else
			{
				Thread.sleep(new Random().nextInt(2000));
				int returnValue = new Random().nextInt(10);
				System.out.println(
						"Thread arrived : " + Thread.currentThread().getId() + " with return value : " + returnValue);
				PhaserDriver.results.add(returnValue);
				phaser.arrive();
				System.out.println("Thread  : " + Thread.currentThread().getId() + " has arrived");	
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
