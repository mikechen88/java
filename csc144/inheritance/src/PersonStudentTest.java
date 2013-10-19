import junit.framework.TestCase;




/**
 * a test of the person and student class
 * @author open
 *
 */
public class PersonStudentTest  extends TestCase{
	/**
	 * test Person and student
	 */
	
	public static void main ( String[] args){
		Student s =new Student ( "Maria",28,3.8);
		System.out .println(s.speak());//use speak from person
		
		
		Person p =s ;
		//the static type of p is person = the type used to declare the variable p.
		//the dynamic type of p is Student = the type of the object that p points to.
		
		System.out.println( p.speak());
		//the statement compiles because speak is in Person
		//at run time,speak from student is called  (=dynamic binding or dynamic dispatch)
		
		
		//another example of dynamic binding
		String[] names={"mark", "wenbin","zamora","david"};
		Person[] a= new Person[names.length];
		for (int i=0; i<a.length;i++){
			
			int age=(int)(Math.random()*10+10);
			if (Math.random()<0.5){
				a[i]=new Person(names[i],age);
			}else{
				double gpa=Math.random()*0.5+3.5;
				a[i]=new Student(names[i],age,gpa);
			}
		}
		
		//dynamic binding 
		for ( Person   ps:a){
			
			//System.out.println(ps.speak());
			
			//System.out.priontln(ps.toString());
			//same as 
			System.out.println(ps);
			System.out.println();
		}
		
	}
	
	//a unit test method: the method name starts with test 
	public void testPersonStudent(){
		Person p= new Student ( "Uyen",29,3.9);
		assertTrue(p.speak().contains("gpa"));
		assertTrue(((Student)p).getGpa()==3.9);
		assertTrue(p.toString().contains("gpa"));
		
		//check that the dynamic type of p is student
		assertTrue( p.getClass()==Student.class);
		//could also do 
		assertTrue(p instanceof Student );
		
		//would that work? ->yes
		assertTrue(p instanceof Person);
	}
}













