
public class PhoneBook {
	private Person[] people;
	
	
	private int numEnteries=0;
	public PhoneBook(int capacity)
	
	{
		people=new Person[capacity];
			
		
	}
	
	public boolean add(String firstName, String lastName, String phoneNumber, String address)
	{//if they 've exce
		if(numEnteries>=people.length) return false;
		
		
		//add person to phone book and increase the size
		people[numEnteries]=new Person(firstName, lastName, phoneNumber,address);
		numEnteries++;
		return true;
		
		
	}
	
	public String[] getList()
	{
		String[] list=new String [numEnteries];
		for (int i=0;i<numEnteries;i++)
		{
			list[i]=people[i].getDetails();
			
			
		}
		
		return list;
		
	}

}
