import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTaskForStringCapitilazation extends RecursiveTask<String> {

	/**
	 * 
	 */
	private static final int THRESHOLD = 20;
	private static final long serialVersionUID = -3388455868683302153L;
	String inputString ;
	
	public ForkJoinPoolTaskForStringCapitilazation(String inputString) {
		super();
		this.inputString = inputString;
	}

	@Override
	protected String compute() {
		if(inputString.length()<THRESHOLD)
		{
			return inputString.toUpperCase();
		}
		else
		{
			List<ForkJoinPoolTaskForStringCapitilazation> tasks = new ArrayList<ForkJoinPoolTaskForStringCapitilazation>();
			ForkJoinPoolTaskForStringCapitilazation strPart1 = new ForkJoinPoolTaskForStringCapitilazation(inputString.substring(0,inputString.length()/2));
			ForkJoinPoolTaskForStringCapitilazation strPart2 = new ForkJoinPoolTaskForStringCapitilazation(inputString.substring(inputString.length()/2,inputString.length()));
			tasks.add(strPart1);
			tasks.add(strPart2);
			ForkJoinTask.invokeAll(tasks); //blocking operation
			StringBuffer buffer = new StringBuffer();
			tasks.stream().forEach(task->{
				buffer.append(task.join());
			});
			return buffer.toString();
		}
		
	}

	

}
