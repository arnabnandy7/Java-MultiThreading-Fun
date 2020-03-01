

public class VolatileConcurrancyDriver extends Thread {

	
	boolean isReader;
	VolatileConcurrancyExample volatileConcurrancyExample;

	/**
	 * Constructor
	 * 
	 * @param isReader
	 */
	public VolatileConcurrancyDriver(boolean isReader,VolatileConcurrancyExample volatileConcurrancyExample) {
		super();
		this.isReader = isReader;
		this.volatileConcurrancyExample = volatileConcurrancyExample;
		
	}

	
	/**
	 * Overridden Run implementation
	 */
	@Override
	public void run() {

		while (true) {
			if (isReader) {
				// Call the Reader thread
				volatileConcurrancyExample.readerMethod(this.getId());
			} else {
				// Otherwise call the writer thread
				volatileConcurrancyExample.writerMethod(this.getId());
			}
			super.run();
		}
		
	}

	public static void main(String[] args) {
		
		
		VolatileConcurrancyExample volatileConcurrancyExample = new VolatileConcurrancyExample();
		//Create reader and writer threads
		VolatileConcurrancyDriver readerThread = new VolatileConcurrancyDriver(true,volatileConcurrancyExample);
		VolatileConcurrancyDriver writerThread = new VolatileConcurrancyDriver(false,volatileConcurrancyExample);
		
		//starting the threads
		readerThread.start();
		writerThread.start();
		System.out.println("***ended***");
		
	}
}
