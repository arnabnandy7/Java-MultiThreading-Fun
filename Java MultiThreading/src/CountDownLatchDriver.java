import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CountDownLatchDriver {
public static void main(String[] args) throws InterruptedException {
	ExecutorService executorService = Executors.newFixedThreadPool(10);
	int threadSize = 100;
	CountDownLatch countDownLatch = new CountDownLatch(threadSize);
	CountDownLatchTask task = new CountDownLatchTask(countDownLatch);
	List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
	for (int i = 0; i < threadSize; i++) {
		futures.add(executorService.submit(task));
	}
	
	countDownLatch.await();
	List<Integer> results =  futures.stream().map(temp -> {
       int result = -1;
	try {
		result = (int) temp.get();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
        return result;
    }).collect(Collectors.toList());
	int finalResult = results.stream()
			.filter(current->(current>=0))
			.collect(Collectors.summingInt(Integer::intValue));
	System.out.println("----Main thread ended---------with Result : "+finalResult);
	executorService.shutdown();
	
	
}
}
