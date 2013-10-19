
public class Person {
	
	private String firstName;;
	private String lastName;
	private String phoneNumber;	
	private String address;

	public Person(String firstName, String lastName, String phoneNumber, String address)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.address=address;
			
	}
	
		public String getFirstName()
		{
			return firstName;
		
			
		}
			public String getLastName()
			{
				
				
				return lastName;
			}
		public String getPhoneNumber()
		{
			
			
			return phoneNumber;
			
		}
	public String getAddress()
	{
		
		return address;
		
		
	}
	public String getDetails()
	{
		return firstName+" "+lastName+":"+phoneNumber+", "+address;
		
		
	}
	
	
	
	
	
}
