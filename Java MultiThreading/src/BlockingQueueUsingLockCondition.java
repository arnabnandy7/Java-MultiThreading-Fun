import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueUsingLockCondition<E> {

	//Quere and it's size
	private Queue<E> queue;
	private int size;
	
	//Lock and Condition
	private Lock lock;
	private Condition notFull;
	private Condition notEmpty;
	
	
	
	public BlockingQueueUsingLockCondition(int size) {
		super();
		this.size = size;
		queue = new LinkedList<>();
		lock = new ReentrantLock();
		notEmpty = lock.newCondition();// lock bound condition
		notFull = lock.newCondition(); //lock bound condition
	}

	public void put(E e)
	{
		lock.lock();
		try {
			//block the thread
			while(queue.size()==size)
			{
			notFull.await();//Thread release the lock and go to wait state st=ays queue is not full
			}
			queue.add(e);
			notEmpty.signalAll();//telling all consumers for consuming
		}catch (InterruptedException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		finally {
			lock.unlock();	
		}
		
		
	}
	
	public E get()
	{
		lock.lock();
		try {
			while(queue.size()==0)
			{
			notEmpty.await(); // Thread release lock and go to wait stateto say when queue is not empty
			}
			E e = queue.remove();
			notFull.signalAll();//telling all producers to produce
			return  e;	
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			lock.unlock();	
		}
		return null;
	
		
		
	}
	
}
