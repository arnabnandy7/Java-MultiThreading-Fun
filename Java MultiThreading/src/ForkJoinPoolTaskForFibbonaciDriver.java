import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTaskForFibbonaciDriver {

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		ForkJoinPoolTaskForFibbonaci fibbonaci = new ForkJoinPoolTaskForFibbonaci(40);
	    
		//submitting task way 1
		forkJoinPool.execute(fibbonaci);
		System.out.println("Resul Fibonacci Number : "+fibbonaci.join());
		
		//submitting task way 2
				forkJoinPool.submit(fibbonaci);
				System.out.println("Resul Fibonacci Number : "+fibbonaci.join());
				
				//submitting task way 3
				System.out.println("Resul Fibonacci Number : "+forkJoinPool.invoke(fibbonaci));
	}
}
