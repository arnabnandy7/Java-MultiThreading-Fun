

public class VolatileConcurrancyExample  {

	//int count = 0; - visibility problem
	 int count = 0;
	
	/**
	 * Reader methood to Read the value
	 */
	public void readerMethod(long threadId) {
		System.out.println("-----Reading Value of Count ---" + count+" by thread Id : "+threadId);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

	/**
	 * Writer method to write the value
	 */
	public void writerMethod(long threadId) {
		count++;
		System.out.println("-----Updating Value of Count to ---" + count+" by thread Id : "+threadId);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

	

}
