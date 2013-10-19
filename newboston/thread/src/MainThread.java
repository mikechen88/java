
public class MainThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread  t1=new Thread(new Thread1("one"));
		Thread  t2=new Thread(new Thread1("two"));
		Thread  t3=new Thread(new Thread1("three"));
		Thread  t4=new Thread(new Thread1("four"));
		Thread  t5=new Thread(new Thread1("five"));
		
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}

}
