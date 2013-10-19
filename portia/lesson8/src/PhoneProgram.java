

import acm.program.ConsoleProgram;
public class PhoneProgram extends ConsoleProgram
{

	
	public void run()
	{
		PhoneBook phoneBook=new PhoneBook();
		phoneBook.add("Amy", "Smith","345-543-2345","123 Anyshere st.");
		phoneBook.add("Bob","Caft","343-945-4593","34 go ave.");
		phoneBook.add("Carl","Jinkins","452-564-7865","5443 Portia ave");
		phoneBook.add("Dave","Derk","675-345-8585","78 seventy eight st.");
		
		String[] list=phoneBook.getList();
		for (String entry:list)
		{
			println(entry);
		}
		
		
		
		
		
		/*for(int i=0;i<list.length;i++)
			
		{
			println(list[i]);
			
			
		}
		*/
		
		
		
		
		
		/*Person myPerson=new Person("Amy", "Smith","345-543-2345","123 Anyshere st.");
		println(myPerson.getFirstName()+" "+myPerson.getLastName());
		println("Person:"+myPerson.getPhoneNumber());
		println("Address:"+myPerson.getAddress());*/
					/*Person[] people=new Person[5];
					people[0]=new  Person("Amy", "Smith","345-543-2345","123 Anyshere st.");
					people[1]=new Person("Bob","Caft","343-945-4593","34 go ave.");
					people[2]=new Person("Carl","Jinkins","452-564-7865","5443 Portia ave");
					people[3]=new Person("Dave","Derk","675-345-8585","78 seventy eight st.");*/
		//loop through and display each of the people like:
		// amy smith 
		/*for (int i = 0; i < people.length; i++)
		{
			println(people[i]);
		
	}
	*/

					/*for (Person person:people)
						
					{
						//println(person.getFirstName+" "+person.getLastName()+":"+person.getPhoneNumber()+","+getAddress());
						
						println(person.getDetails());
						
					}*/
	
	}}
