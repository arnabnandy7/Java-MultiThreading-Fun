import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadExecutorService {

	
	public static void main(String[] args) {
		
		System.out.println("----MAIN thread id ----"+Thread.currentThread().getId());//If we use CallerRunsPolicy() then only this read will execute the task
		customRejectionHandler rejectionHandler = new customRejectionHandler();
		//Creating ExecutorService
		ExecutorService executorServiceNormal = new ThreadPoolExecutor(10,10,1,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5),new ThreadPoolExecutor.CallerRunsPolicy());
		ExecutorService executorServiceSpecial = new ThreadPoolExecutor(10,10,1,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5),rejectionHandler);
		
		
		//trying to add more than accepted tasks
		
		for (int i = 0; i < 25; i++) {
			CustomTask task = new CustomTask();
			executorServiceSpecial.execute(task);
			
		}
		
		try {
			
		for (int i = 0; i < 25; i++) {
			CustomTask task = new CustomTask();
			executorServiceNormal.execute(task);
		}
		
		executorServiceNormal.shutdown();
		executorServiceSpecial.shutdown();
		
		}catch (Exception e) {
			System.out.println("*****Task Rejected******"+e.getMessage());
		}
		
	}
	
	private static class customRejectionHandler implements RejectedExecutionHandler
	{

		@Override
		public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1) {
			System.out.println("------Task rejected -----"+arg0);
			
		}
		
	}
}
