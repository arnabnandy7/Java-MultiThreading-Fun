import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

public class ClallableHeavyTask implements Callable<Long> {

	@Override
	public Long call() throws Exception {
		Instant start = Instant.now();
		Thread.sleep(3000);
		Instant end = Instant.now();
		return Duration.between(start, end).toMillis();
	}
	
//	public Long getOrder(Long object)  {
//		
//		if(null==object)
//		{
//			System.out.println("Order processing started");
//		}
//		else
//		{
//			System.out.println("Last order processed in "+object+" Milis");
//		}
//		Instant start = Instant.now();
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Instant end = Instant.now();
//		return Duration.between(start, end).toMillis();
//	}

	
}
