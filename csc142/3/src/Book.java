/**
 * a simple model of a book
 * 
 * @author open
 * 
 */
public class Book {
	private String title;

	/**
	 * creates a book given its title
	 * 
	 * @param title
	 *            the title of the book
	 */

	public Book(String title) {
		this .title=title;
	}

	/**
	 * Returns the title of this book
	 */
	public String getTitle(){
		
		return this.title;		
	}
}
