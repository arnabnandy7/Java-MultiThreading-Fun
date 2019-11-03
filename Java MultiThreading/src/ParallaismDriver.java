
public class ParallaismDriver {

	public static void main(String[] args) {
		Parallalism th1 = new Parallalism();
		Parallalism th2 = new Parallalism();
System.out.println(Runtime.getRuntime().availableProcessors());
		th1.start();
		th2.start();
		
		
		
	}
}
