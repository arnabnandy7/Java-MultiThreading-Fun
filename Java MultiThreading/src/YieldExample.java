
public class YieldExample {

	public YieldExample() {
	
	}
	public static void main(String[] args) throws InterruptedException {
		
		
		 Runnable r1 = ()->{
			
				System.out.println("Thread 1 Started");
				
					for (int i = 0; i <20; i++) {
						
						
						System.out.println("TH1 - "+i);
						
					}
				
				
				
				System.out.println("Thread 1 ended");
				
			
		};
		
		
		
		
		final Thread th1 = new Thread(r1);
		th1.start();
		
		System.out.println("Thread 2 strted ");
		for (int i = 0; i <20; i++) {
				Thread.yield();
				
			System.out.println("TH2 - "+i);
		}
		System.out.println("Thread 2 ended");
	}
	}
