import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTaskForFibbonaci extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3388455868683302153L;
	long number ;
	
	public ForkJoinPoolTaskForFibbonaci(long number) {
		super();
		this.number = number;
	}

	@Override
	protected Long compute() {
		if(number<=1)
		{
			return number;
		}
		else
		{
			ForkJoinPoolTaskForFibbonaci fibbonaciPart1 = new ForkJoinPoolTaskForFibbonaci(number-1);
			ForkJoinPoolTaskForFibbonaci fibbonaciPart2 = new ForkJoinPoolTaskForFibbonaci(number-2);
			
			fibbonaciPart1.fork();
			fibbonaciPart2.fork();
			
			return fibbonaciPart1.join()+fibbonaciPart2.join();
		}
		
	}

	

}
