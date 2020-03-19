import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;
/**
 * 
 * @author Aniket Roy
 *
 */
public class ThreadLocalDriver {

	private static final 	ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
		
		@Override
		protected SimpleDateFormat initialValue() {
			if(Thread.currentThread().getId()%2==0)
			return new SimpleDateFormat("dd-MMM-yy");
			else
			return new SimpleDateFormat("yyyy-MM-dd");	
		};
		
		
	};


	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		for (int i = 0; i < 10; i++) {
			Thread th = new Thread(new Runnable() {
				
				@Override
				public void run() {
					Random r = new Random();
					try {
						Thread.sleep(r.nextInt(3000)+2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				Date dt = new Date();
				System.out.println("Thread is : "+Thread.currentThread().getId()+"----Date format-----"+threadLocal.get().format(dt));
				System.out.println("Thread id : "+Thread.currentThread().getId()+"-----Retriving again ----"+threadLocal.get().format(dt));
				threadLocal.set(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
				//threadLocal.remove();
				}
			});
			th.start();
		}
	
	}
		
}
