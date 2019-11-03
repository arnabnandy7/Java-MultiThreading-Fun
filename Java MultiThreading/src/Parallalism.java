import java.sql.Timestamp;
import java.util.Date;

public class Parallalism extends Thread {


	@Override
	public void run() {
		for (int i = 0; i <20; i++) {
			System.out.println("----Doing some Operating -----"+this.getId()+"----Timestamp"+new Timestamp(new Date().getTime()));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		super.run();
	}

}
