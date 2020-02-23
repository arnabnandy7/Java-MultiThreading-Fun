
public class JoinExample {

	public JoinExample() {
	
	}
public static void main(String[] args) throws InterruptedException {
	
	
	 Runnable r1 = ()->{
		
			System.out.println("Thread 1 Started");
			try {
				for (int i = 0; i <20; i++) {
					Thread.sleep(100);
					System.out.println("TH1 - "+i);
				}
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Thread 1 ended");
			
		
	};
	
	 Runnable r2 = ()->{
			System.out.println("Thread 2 strted ");
			try {
				for (int i = 0; i <20; i++) {
					Thread.sleep(1000);
					System.out.println("TH2 - "+i);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread 2 ended");
		
	};
	
	
	final Thread th1 = new Thread(r1);
	final Thread th2 = new Thread(r2);
	th1.start();
	th2.start();
	th1.join();
	th2.join();
	System.out.println("---Main thread ended------");
}
}
