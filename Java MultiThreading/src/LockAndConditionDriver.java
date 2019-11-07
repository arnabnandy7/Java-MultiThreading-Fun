import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndConditionDriver {
	public static void main(String[] args) throws InterruptedException {
		int threadSize = 50;
		ExecutorService executorService = Executors.newCachedThreadPool();
	
		//Creating Lock
		Lock lock =new ReentrantLock();
		Condition condition = lock.newCondition();
		//Creating waiting tasks
		LockConditionTask task = new LockConditionTask(condition,lock,true);
		for (int i = 0; i < threadSize; i++) {
			executorService.submit(task);
		}
		
		Thread.sleep(3000);
		//Creating signaling tasks
		task = new LockConditionTask(condition,lock,false);
		executorService.submit(task);
		executorService.shutdown();
		System.out.println("-----Main thread ended---------");
	}
}
