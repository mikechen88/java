
public class ExampleWithFinally {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		m1();
	}
	public static void m1() {
		m2();
	}
	public static void m2() {
		try {
		m3();
		}
		catch(NumberFormatException e){}
		catch(ArithmeticException e) {
			System.out.println("catching a arithmetic exception");
		}
		finally {
			System.out.println("finally in m2");
		}
	}
	public static void m3() {
		try {
		m4();
		} catch(ArrayIndexOutOfBoundsException e) {
			
		}
		finally{
			System.out.println("finally in m3");
		}
	}
	public static void m4() {
		throw new RuntimeException("Problem in m4");
	}

}
