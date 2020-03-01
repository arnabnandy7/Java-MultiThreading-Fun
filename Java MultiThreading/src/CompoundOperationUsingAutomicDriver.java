
import java.util.concurrent.atomic.AtomicInteger;

public class CompoundOperationUsingAutomicDriver implements Runnable {

	private boolean isIncrementor;
	private CompoundOperationUsingAutomic compoundOperationUsingAutomic;

	// Constructor Using filed
	public CompoundOperationUsingAutomicDriver(boolean isIncrementor,
			CompoundOperationUsingAutomic compoundOperationUsingAutomic) {
		super();
		this.isIncrementor = isIncrementor;
		this.compoundOperationUsingAutomic = compoundOperationUsingAutomic;

	}

	// overridden Run method
	@Override
	public void run() {

		while (true) {
			if (isIncrementor) {
				try {
					this.compoundOperationUsingAutomic.incrementVariable(Thread.currentThread().getId());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					this.compoundOperationUsingAutomic.decrementVariable(Thread.currentThread().getId());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {

		CompoundOperationUsingAutomic compoundOperationUsingAutomic = new CompoundOperationUsingAutomic(
				new AtomicInteger(0));
		// Thread to increment the variable
		Thread incrementor = new Thread(new CompoundOperationUsingAutomicDriver(true, compoundOperationUsingAutomic));
		// Thread to decrement the variable
		Thread decrementor = new Thread(new CompoundOperationUsingAutomicDriver(false, compoundOperationUsingAutomic));

		// starting the threads
		incrementor.start();
		decrementor.start();

	}

}
