
public class Threadrun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1=new Thread(new Threadsex("one"));
		Thread t2=new Thread(new Threadsex("two"));
		Thread t3=new Thread(new Threadsex("three"));
		//three thread run at the same time.
		
		t1.start();
		t2.start();
		t3.start();

	}

}
