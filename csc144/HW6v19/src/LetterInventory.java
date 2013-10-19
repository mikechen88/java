

// This class creates a Letter Inventory
public class LetterInventory {

	final static int ALPHABET_LENGTH = 26;
	final static int ASCII_OFFSET = 97;
	int[] inventory = new int[ALPHABET_LENGTH];
	int size = 0;
    
    
    // Construct an inventory (a count) of the
    //  alphabetic letters in the given string,
    //  ignoring the case of letters and ignoring any
    //  non-alphabetic characters.
    public LetterInventory(String data){
    	// changes to string to all lower case
    	 String lower = data.toLowerCase();
    	 for (int i = 0; i < lower.length(); i++){
    		 char ch = lower.charAt(i);
    		 if(Character.isLetter(ch)){
    			 int index = ch - ASCII_OFFSET;
    			 inventory [index] ++;
    			 size ++;
    			 
    		 }
    	 }       
    }
    //  Return a count of how many of this letter
    //  are in the inventory.  Letter might be
    //  lowercase or uppercase (your method shouldn't care). 
    //  If a nonalphabetic character is passed, your method
    //  should throw an IllegalArgumentException.
   public int get(char letter){
	   char c = Character.toLowerCase(letter);
    	int index = c - ASCII_OFFSET;
    	return inventory[index];
    }
    //  Set the count for the given letter to the given value. 
    //  Letter might be lowercase or uppercase.  If a
    //  nonalphabetic character is passed or if value is negative,
    //  your method should throw an IllegalArgumentException
    public void set(char letter, int value){
    	char c = Character.toLowerCase(letter);
    	System.out.println(c);
    	System.out.println(value);
    	int index = c - ASCII_OFFSET;
    	System.out.println(index);
    	inventory[index] = value;
    	size+=value;
    	
       
    }
   
    //  Return the sum of all of the counts in this inventory. 
    //  This operation should be fast in that it should store
    //  the size rather than having to compute it each time
    //  this method is called.
    public int size(){
        return size;
    }
   
    //  Return true if this inventory is empty (all counts are 0). 
    //  This operation should be fast in that it should not need
    //  to examine each of the 26 counts when it is called.
    public boolean isEmpty(){
        return(size == 0);
       
    }
   
    //  Return a String representation of the inventory with the
    //  letters all in lowercase and in sorted order and surrounded
    //  by square brackets.  The number of occurrences of each letter
    //  should match its count in the inventory.  For example, an
    //  inventory of 4 a's, 1 b, 1 l and 1 m would be represented as
    //  [aaaablm].
    public String toString(){
        String newString = "[";
        for (int i = 0; i < ALPHABET_LENGTH; i++){
        	int count = inventory[i];
        	for (int j = 0; j < count; j++){
        		newString += (char)(i + ASCII_OFFSET); 
        	}
        }
    	newString += "]";
    	return newString;
       
    }
    //  Construct and returns a new LetterInventory object that
    //  represents the sum of this letter inventory and the other
    //  given LetterInventory.  The counts for each letter should
    //  be added together.  The two LetterInventory objects being
    //  added together (this and other) should not be changed by
    //  this method
    public LetterInventory add(LetterInventory other){
    	LetterInventory sum = new LetterInventory(this.toString());
    			for (int i = 0; i < ALPHABET_LENGTH; i++){
    				sum.inventory [i] += other.inventory[i];
    				sum.size += other.inventory[i];
    			}
        return sum;
    }
   
    //  Construct and returns a new LetterInventory object that
    //  represents the result of subtracting the other inventory
    //  from this inventory (i.e., subtracting the counts in the
    //  other inventory from this object's counts).  If any
    //  resulting count would be negative, your method should
    //  return null.  The two LetterInventory objects being
    //  subtracted (this and other) should not be changed by this
    //  method
    public LetterInventory subtract(LetterInventory other){
    	LetterInventory difference = new LetterInventory(this.toString());
		for (int i = 0; i < ALPHABET_LENGTH; i++){
			
			if ((difference.inventory [i] -= other.inventory[i]) < 0){
				return null;
			}
			if ((difference.size -= other.inventory[i]) < 0){
				return null;
			}
		}
		return difference;
       
    }

}
