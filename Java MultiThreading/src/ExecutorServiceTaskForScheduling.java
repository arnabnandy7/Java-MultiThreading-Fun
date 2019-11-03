import java.util.Date;

public class ExecutorServiceTaskForScheduling implements  Runnable {

	
	@Override
	public void run() {
		try {
			Thread.sleep(4000);
			System.out.println("Task get executed by thread : "+Thread.currentThread().getId()+" The Current time "+new Date());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
