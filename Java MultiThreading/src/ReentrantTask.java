import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTask implements Runnable {

	ReentrantLock lock;
	int count = 5;

	public ReentrantTask(ReentrantLock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		
		lockMethod();

	}

	private void lockMethod() {
		if(lock.tryLock()) {
		try {
			
			if(count>0)
			{
				count--;
				System.out.println("---Holding Flag----"+lock.getHoldCount());
				lockMethod();
			}
			System.out.println("Thread accuquired lock: " + Thread.currentThread().getId());
			Thread.sleep(new Random().nextInt(2000));
			System.out.println("Thread  : " + Thread.currentThread().getId() + " release lock");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
		}
		else
		{
			System.out.println("Thread  : " + Thread.currentThread().getId() + " passing by");
		}
	}
	
//	private void lockMethod() {
//	try {
//		
//		lock.lock();
//		System.out.println("Thread accuquired lock: " + Thread.currentThread().getId());
//		Thread.sleep(new Random().nextInt(2000));
//		//this.wait();
//		System.out.println("Thread  : " + Thread.currentThread().getId() + " release lock");
//		
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	finally {
//		lock.unlock();
//	}
//	
//}
}
