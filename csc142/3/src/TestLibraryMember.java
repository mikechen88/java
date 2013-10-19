/**
 * a test of the librarymember and Book classes
 * @author open
 *
 */
public class TestLibraryMember {

	/**
	 * tests the LibraryMember and Book classes
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LibraryMember m=new LibraryMember(123456);
		boolean b;
		b=m.returnBook();
		System.out.println("b= " +b+ "( should be false).");
		b=m.borrowBook("seattle restaurant guide");
		System.out.println("b= " +b+ "( should be true).");
		b=m.borrowBook("java");
		System.out.println("b= " +b+ "( should be false).");
		b=m.returnBook();
		System.out.println("b= " +b+ "( should be true).");
	}

}
