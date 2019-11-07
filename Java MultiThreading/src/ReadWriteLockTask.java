import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriteLockTask implements Runnable {

boolean isReader;
ReadLock readLock;
WriteLock writeLock;


//constructor
	public ReadWriteLockTask(boolean isReader, ReadLock readLock, WriteLock writeLock) {
	super();
	this.isReader = isReader;
	this.readLock = readLock;
	this.writeLock = writeLock;
}


public void readResource()
{
	try {
		
		readLock.lock();
		System.out.println("Thread accuquired READLOCK: " + Thread.currentThread().getId());
		Thread.sleep(new Random().nextInt(2000));
		System.out.println("Thread  : " + Thread.currentThread().getId() + " release READLOCK");
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		readLock.unlock();
	}
}

public void writeResource()
{
	try {
		
		writeLock.lock();
		System.out.println("Thread accuquired WRITELOCK: " + Thread.currentThread().getId());
		Thread.sleep(new Random().nextInt(2000));
		System.out.println("Thread  : " + Thread.currentThread().getId() + " release WRITELOCK");
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		writeLock.unlock();
	}
}


	@Override
	public void run() {
		if(isReader)
		{
			readResource();
		}
		else
		{
			writeResource();
		}

	}
}
