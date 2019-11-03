import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTask implements Callable<Integer> {

	CountDownLatch countDownLatch;
	
	
	public CountDownLatchTask(CountDownLatch countDownLatch) {
		this.countDownLatch=countDownLatch;
	}

	@Override
	public Integer call() {
		try {
			Thread.sleep(new Random().nextInt(2000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int returnValue = new Random().nextInt(10);
		System.out.println("Thread arrived : "+Thread.currentThread().getId()+" with return value : "+returnValue);
		countDownLatch.countDown();
		
		return returnValue;
	}

}
