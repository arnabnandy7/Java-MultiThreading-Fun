import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class LockConditionTask extends Thread {
	
	
	
	private Condition condition;
	private Lock lock;
	private boolean isWaitGuys;
	
	//constructor
	
	public LockConditionTask(Condition condition, Lock lock,boolean isWaitGuys) {
		super();
		this.condition = condition;
		this.lock = lock;
		this.isWaitGuys=isWaitGuys;
	}
	

	@Override
	public void run() {
		if(isWaitGuys)
		{
			doSomeFunTask();
		}
		else
		{
			doSomeOtherTask();
		}
		super.run();
	}
	public void doSomeFunTask()
	{
		try {
			lock.lock();
			//Do some task
			System.out.println("----Wating for a signal----");
			Thread.sleep(new Random().nextInt(2000));
			condition.await();
			System.out.println("----Hurray I am free----");
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}
	
	public void doSomeOtherTask()
	{
		try {
			lock.lock();
			//Do some task
			System.out.println("--Conditions are met now---- ");
			Thread.sleep(new Random().nextInt(2000));
			System.out.println("Lets free them all");
			condition.signalAll();
		
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}


}
