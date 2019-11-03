import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

public class CallableLightTask implements Callable<Long> {

	@Override
	public Long call() throws Exception {
		Instant start = Instant.now();
		Thread.sleep(1000);
		Instant end = Instant.now();
		return Duration.between(start, end).toMillis();
	}
	

	
}
