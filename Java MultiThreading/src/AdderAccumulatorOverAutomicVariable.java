import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class AdderAccumulatorOverAutomicVariable implements Runnable {
	
	static final String ACCUMULATOR = "ACCUMULATOR";
	public static final String ADDER = "ADDER";
	public static final String ATOMIC = "ATOMIC";
	//An atomic variable counter
	private AtomicInteger counter;
	private LongAdder longAdder;
	private LongAccumulator longAccumulator;
	private String controlFlag;
	
	
/**
 * Constructor
 * @param counter
 * @param longAdder
 * @param longAccumulator
 * @param controlFlag
 */
	public AdderAccumulatorOverAutomicVariable(AtomicInteger counter, LongAdder longAdder, LongAccumulator longAccumulator,
			String controlFlag) {
		super();
		this.counter = counter;
		this.longAdder = longAdder;
		this.longAccumulator = longAccumulator;
		this.controlFlag = controlFlag;
	}


	@Override
	public void run() {
		switch (controlFlag) {
		case ATOMIC:
			atomicOperation();
			break;
	     case ADDER:
	    	 adderOperation();
			break;
	     case ACCUMULATOR:
	    	 accumulatorOperation();
				break;	


		default:
			break;
		}
		
	}


	/**
	 * Implementing atomic integer
	 */
	private void atomicOperation() {
		counter.incrementAndGet();
	}
	
	/**
	 * Implementing Adder integer
	 */
	private void adderOperation() {
		longAdder.increment();
	}
	/**
	 * Implementing Accumulator integer
	 */
	private void accumulatorOperation() {
		longAccumulator.accumulate(1);
	}
	
	
	
		

}
