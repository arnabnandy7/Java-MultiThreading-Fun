import java.util.concurrent.Callable;

public class ExecutorServiceTask implements Callable<Long> {

	@Override
	public Long call() {
		try {
			Thread.sleep(0, 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Thread.currentThread().getId();
		
		
	}

}
