import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 
 * @author Aniket Roy
 *
 */
public class FutureCalableBasicDriver {

	
	public static void main(String[] args) {
		//Creating tasks
		CallableLightTask lightFutureTask = new CallableLightTask();
		ClallableHeavyTask heavyFutureTask = new ClallableHeavyTask();
		//Preparing executor service
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
	
		//Submitting the tasks in queue
		List<Future<?>> futures = new ArrayList<Future<?>>();
	  
		//Submitting the tasks
		for (int i = 0; i <10; i++) {
			Future<Long> futureLight = executorService.submit(lightFutureTask);	
			futures.add(futureLight);
			Future<Long> futureHeavy = executorService.submit(heavyFutureTask);	
			futures.add(futureHeavy);
		}
		
		//CompletableFuture.supplyAsync(supplier)
		
		//If we try  fetch values sequentially -Much more time will be required
		System.out.println("Value retrival started in secuential manner");
		Instant start = Instant.now();
		futures.stream().forEach(future ->{
			try {
				System.out.println("-----Thread executed in time ----"+future.get()+" Milis");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Instant finish = Instant.now();
		System.out.println("Value retiving finished in time : "+Duration.between(start, finish).toMillis()+" Milis");
		
		
		//Now lets use CompletionSerive
		CompletionService<Long> completionService = new ExecutorCompletionService<Long>(executorService);
		
		//Submitting the tasks
				for (int i = 0; i <10; i++) {
					completionService.submit(lightFutureTask);
					completionService.submit(heavyFutureTask);
				}
		//initiating shutdow
		executorService.shutdown();		
		//retriving results 		
		System.out.println("Value retrival started in random manner");
		start = Instant.now();
		while(!executorService.isTerminated())	
		{
		try {
			Future<Long>  result = completionService.take();
			System.out.println("-----Thread executed in time ----"+result.get()+" Milis");
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		 finish = Instant.now();
		System.out.println("Random Value retiving finished in time : "+Duration.between(start, finish).toMillis()+" Milis");
		
	
}
}
