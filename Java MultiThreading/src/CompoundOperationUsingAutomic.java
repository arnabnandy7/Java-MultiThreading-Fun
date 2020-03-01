
import java.util.concurrent.atomic.AtomicInteger;

public class CompoundOperationUsingAutomic{
	
	//Shared Data 
	private AtomicInteger atomicInteger; 
	
	//constructor 
	public CompoundOperationUsingAutomic(AtomicInteger atomicInteger) {
		super();
		this.atomicInteger = atomicInteger;
	}

	//Increment the variable
	public void incrementVariable(long id) throws InterruptedException
	{
		System.out.println("Current value of Automic Integer after Increment by Thread : "+id+" is : "+atomicInteger.incrementAndGet());
		Thread.sleep(1000);
	}
	
	//Decrement the variable
		public void decrementVariable(long id) throws InterruptedException
		{
			System.out.println("Current value of Automic Integer after Decrement by Thread : "+id+" is : "+atomicInteger.decrementAndGet());
			Thread.sleep(1000);
			
		}
	


}
