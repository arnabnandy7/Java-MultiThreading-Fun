import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * 
 * @author Aniket Roy
 *
 */
public class ExecutorServiceExample {

	public static void main(String[] args) throws InterruptedException {
//Identifying the Number of cores in System
		int cpuCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executorService = Executors.newFixedThreadPool(cpuCores);
		List<Callable<?>> tasks = new ArrayList<Callable<?>>();
		int taskSize =0;// 100000;
		
		for (int i = 0; i < taskSize; i++) {
			ExecutorServiceTask executorServiceTask = new ExecutorServiceTask();
			tasks.add(executorServiceTask);
		}

		// Calling executor service and wait for result
		Instant start = Instant.now();
		executorService.invokeAll(tasks);
		Instant end = Instant.now();
		System.out.println(
				"Total CPU Cores = " + cpuCores + " Total time elapsed : " + Duration.between(start, end).getSeconds());
		executorService.shutdown();

		ExecutorService executorServiceRandomPool = Executors.newFixedThreadPool(16);
		

		// Calling executor service and wait for result
		Instant startRandom = Instant.now();
		executorServiceRandomPool.invokeAll(tasks);
		Instant endRandom = Instant.now();
		System.out.println("RANDOM POOL SIZE  = " + 16 + " Total time elapsed : "
				+ Duration.between(startRandom, endRandom).getSeconds());
		executorServiceRandomPool.shutdown();

		ExecutorService executorServiceCached = Executors.newCachedThreadPool();
		
//Calling executor service and wait for result
		Instant startCached = Instant.now();
		executorServiceCached.invokeAll(tasks);
		Instant endCached = Instant.now();
		System.out.println("CACHED POOL SIZE  = " + 1 + " Total time elapsed First time : "
				+ Duration.between(startCached, endCached).getNano());
		
		 startCached = Instant.now();
		executorServiceCached.invokeAll(tasks);
		 endCached = Instant.now();
		System.out.println("CACHED POOL SIZE  = " + 1 + " Total time elapsed Second time : "
				+ Duration.between(startCached, endCached).getNano());
		executorServiceCached.shutdown();
		
		
		//For scheduling Executor
		ScheduledExecutorService schedulingExecutorService = Executors.newScheduledThreadPool(2);
		
		//Sample task for this
		ExecutorServiceTaskForScheduling executorServiceTask = new ExecutorServiceTaskForScheduling();
		//schedulingExecutorService.schedule(executorServiceTask, 15, TimeUnit.SECONDS);
		
		
		schedulingExecutorService.scheduleAtFixedRate(executorServiceTask, 5, 3, TimeUnit.SECONDS);
		schedulingExecutorService.scheduleWithFixedDelay(executorServiceTask, 3, 5, TimeUnit.SECONDS);
		
	
		

	}
}
