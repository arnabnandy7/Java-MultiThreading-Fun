
public class DeadLockExample {

	public DeadLockExample() {
	
	}
public static void main(String[] args) {
	String resource1 = "Aniket";
	String resource2 = "Roy";
	
	Runnable r1 = ()->{
		synchronized (resource1) {
			System.out.println("Thread 1 locked on "+resource1);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread 1 trying to locked on "+resource2);
			synchronized (resource2) {
				System.out.println("Thread 1 locked on "+resource2);
			}
		}
	};
	
	Runnable r2 = ()->{
		synchronized (resource2) {
			System.out.println("Thread 2 locked on "+resource2);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread 2 trying to locked on "+resource1);
			synchronized (resource1) {
				System.out.println("Thread 2 locked on "+resource1);
			}
		}
	};
	
	
	new Thread(r1).start();
	new Thread(r2).start();
	
}
}
