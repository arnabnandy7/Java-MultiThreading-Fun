import java.util.Date;

public class SimpleProducerConsumerDriver {
	
	//Creating a blocking queue of size 10
	public static void main(String[] args) {
	
	//BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);
		//BlockingQueueUsingLockCondition<String> blockingQueue = new BlockingQueueUsingLockCondition<String>(10);
		BlockingQueueUsingWaitNotify<String> blockingQueue = new BlockingQueueUsingWaitNotify<String>(10);
	
	
	//Producer thread 
	Thread producerThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			while (true) {
				try {
				String item ="New Item"+new Date(); 
				blockingQueue.put(item);
				  System.out.println("Producer inserted item : "+item);
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		}
	});
	
	//Consumer Thread  
		Thread consumerThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						String item = blockingQueue.get();
						System.out.println("--Consumer retrived item : "+item);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		});
		producerThread.start();
		consumerThread.start();
		
		
		
	System.out.println("***Main thread ended*********");
	}
	
	

}
