import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueUsingWaitNotify<E> {

	//Quere and it's size
	private Queue<E> queue;
	private int size;
	
	
	
	
	public BlockingQueueUsingWaitNotify(int size) {
		super();
		this.size = size;
		queue = new LinkedList<>();
	}

	public  void put(E e)
	{
		synchronized(queue) {
		try {
			//block the thread
			while(queue.size()==size)
			{
				queue.wait();//Thread release the lock and go to wait state st=ays queue is not full
			}
			queue.add(e);
			queue.notifyAll();//telling all consumers for consuming
		}catch (InterruptedException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		}
		
		
		
	}
	
	public  E get()
	{
		synchronized(queue) {
		try {
			while(queue.size()==0)
			{
				queue.wait(); // Thread release lock and go to wait state to say when queue is not empty
			}
			E e = queue.remove();
			queue.notifyAll();//telling all producers to produce
			return  e;	
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		return null;
		
	}
	
}
