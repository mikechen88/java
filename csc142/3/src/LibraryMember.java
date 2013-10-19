/**
 * a simple model of a library member
 * @author open
 *
 */
public class LibraryMember {
	private int ssn;
	private Book book;
	
	/**
	 * Creates a library member given its ssn
	 * member doesn't have a book.
	 * @param ssn   is the social secutiry number of the member
	 */
	public LibraryMember( int ssn){
		this.ssn=ssn;
		
		//book=null;  // no book initially
		// instance fields are automatically initialized with default values
		//0 for numbers , false for booleans, null for references
		//book is a reference, thus it's set to null automatically
		
	}
	
	/**
	 * checks out a book given its title
	 * the member can check out a book if he doexn't already have one.
	 * 
	 * @param title  the title of the book to check out
	 * @param true if successful, and false if not
	 */
	public boolean borrowBook( String title ){
		if (book==null){
			book=new Book(title);
			System.out.println("you have check out the book \""+title+"\".");
			return true;
		}else{
			System.out.println("you can't check out a book.you already have one");
			return false;
		}
	}
	
	/**
	 * returns the book if there is a book to return.
	 * return true if successful, and false if not
	 */
	public boolean returnBook(){
		if (book!=null){			
			System.out.println("you have returned \" "+book.getTitle()+"\".");
			book=null;
			return true;
		}else{
			System.out.println("you don't have any book to return.");
			return false;			
		}
	}
	
	
	
	
}
