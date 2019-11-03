import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
/**
 * 
 * @author royan
 *
 */
public class AdderAccumulatorDriver extends Thread {

	private static final int WAITING_TIME = 500;
	static final String ACCUMULATOR = "ACCUMULATOR";
	public static final String ADDER = "ADDER";
	public static final String ATOMIC = "ATOMIC";
	public static final int ITERATIONS = 9000000;
	public static final int THREAD_POOL_SIZE = 5;

	private AtomicInteger counter;
	private LongAdder longAdder;
	private LongAccumulator longAccumulator;
	private String controlFlag;

	@Override
	public void run() {
		try {
			callingImplementation(controlFlag, counter, longAdder, longAccumulator, ITERATIONS, THREAD_POOL_SIZE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}

	public AdderAccumulatorDriver(AtomicInteger counter, LongAdder longAdder, LongAccumulator longAccumulator,
			String controlFlag) {
		super();
		this.counter = counter;
		this.longAdder = longAdder;
		this.longAccumulator = longAccumulator;
		this.controlFlag = controlFlag;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		AtomicInteger counter = new AtomicInteger(0);
		LongAdder longAdder = new LongAdder();
		LongAccumulator longAccumulator = new LongAccumulator((x, y) -> (x + y), 0);

		AdderAccumulatorDriver ACCUMULATOR = new AdderAccumulatorDriver(counter, longAdder, longAccumulator,
				AdderAccumulatorDriver.ACCUMULATOR);
		AdderAccumulatorDriver ADDER = new AdderAccumulatorDriver(counter, longAdder, longAccumulator,
				AdderAccumulatorDriver.ADDER);
		AdderAccumulatorDriver ATOMIC = new AdderAccumulatorDriver(counter, longAdder, longAccumulator,
				AdderAccumulatorDriver.ATOMIC);
		ATOMIC.start();
		ADDER.start();
		ACCUMULATOR.start();
		
	}

	public void callingImplementation(String callingType, AtomicInteger counter, LongAdder longAdder,
			LongAccumulator longAccumulator, int inerations, int threadPoolSize) throws InterruptedException {
		switch (callingType) {
		case ATOMIC:
			ExecutorService executorServiceForAtomic = Executors.newFixedThreadPool(threadPoolSize);

			// Calling the ATOMIC IMPLEMENTATION
			for (int i = 0; i < inerations; i++) {
				AdderAccumulatorOverAutomicVariable accumulatorOverAutomicVariable = new AdderAccumulatorOverAutomicVariable(
						counter, longAdder, longAccumulator, ATOMIC);
				executorServiceForAtomic.submit(accumulatorOverAutomicVariable);

			}

			Thread.sleep(WAITING_TIME);
			List<Runnable> pendingAtomincThreads = executorServiceForAtomic.shutdownNow();
			System.out.println("ATOMIC IMPLEMENTATION : Threads not executed : " + pendingAtomincThreads.size()
					+ " Counter value by ATOMIC IMPLEMENTATION : " + counter.get());

			break;
		case ADDER:
			ExecutorService executorServiceForAdder = Executors.newFixedThreadPool(threadPoolSize);

			// Calling the ADDER IMPLEMENTATION
			for (int i = 0; i < inerations; i++) {
				AdderAccumulatorOverAutomicVariable accumulatorOverAutomicVariable = new AdderAccumulatorOverAutomicVariable(
						counter, longAdder, longAccumulator, ADDER);
				executorServiceForAdder.submit(accumulatorOverAutomicVariable);

			}

			Thread.sleep(WAITING_TIME);
			List<Runnable> pendingAdderThreads = executorServiceForAdder.shutdownNow();
			System.out.println(" ADDER IMPLEMENTATION : Threads not executed : " + pendingAdderThreads.size()
					+ " Counter value by ADDER IMPLEMENTATION : " + longAdder.sum());

			break;
		case ACCUMULATOR:
			ExecutorService executorServiceForAccumulator = Executors.newFixedThreadPool(threadPoolSize);

			// Calling the ACCOUMULATOR IMPLEMENTATION

			for (int i = 0; i < inerations; i++) {
				AdderAccumulatorOverAutomicVariable accumulatorOverAutomicVariable = new AdderAccumulatorOverAutomicVariable(
						counter, longAdder, longAccumulator, ACCUMULATOR);
				executorServiceForAccumulator.submit(accumulatorOverAutomicVariable);

			}

			Thread.sleep(WAITING_TIME);
			List<Runnable> pendingaccumThreads = executorServiceForAccumulator.shutdownNow();
			System.out.println("ACCOUMULATOR IMPLEMENTATION : Threads not executed : " + pendingaccumThreads.size()
					+ " Counter value by ACCOUMULATOR IMPLEMENTATION : " + longAccumulator.get());

			break;
		default:
			break;
		}
	}
}
