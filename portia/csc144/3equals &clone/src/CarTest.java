/**
 * A test of the Car and FancyCar classes
 * 
 * @author open
 * 
 */
public class CarTest {
	public static void main(String[] args) {
		Car c1 = new Car(2000, "Ford",null);  //no owner
		Car c2 = new Car(2000, "Ford",null);
		System.out.println( "whth the Car class  :");
		System.out.println("c1.equals(c1)=   "+c1.equals(c1));
		
		System.out.println("c1.equals(null)=   "+c1.equals(null));
		System.out.println("c1.equals(c2)=   "+c1.equals(c2));
		
		//equals from Object is checking for aliases
		//c1.equals( c2 ) is false since c2 is not an alias of c1
		// Once equals is overridden in  car, then
		//c1.equals( c2)  is true as expected
		
		
		//with fancy cars
		FancyCar fc1 = new FancyCar(2000, "Ford",200,null);
		FancyCar fc2 = new FancyCar(2000, "Ford",200,null);
		
		System.out.println( "\nwhth the FancyCar class  :");
		System.out.println("c1.equals(c1)=   "+fc1.equals(fc1));		
		System.out.println("c1.equals(null)=   "+fc1.equals(null));
		System.out.println("c1.equals(c2)=   "+fc1.equals(fc2));
		
		
		//with a fancy car and a car
		System.out.println( "\nwhth the FancyCar and Carclass  :");
		System.out.println("fc1.equals(c1)=   "+fc1.equals(c1));	
		System.out.println("cc1.equals(fc1)=   "+c1.equals(fc1));	
		
		
		//Test the clone method
		System.out.println( "\nTest of the clone method in Car:");
		Person owner =new Person("Sara",30);
		Car car =new Car ( 2000,"Ford",owner);
		
		//alias
		Car car2=car;
		//shallow copy ( what clone does in Object )
		//clone() is stand for object
		//so we need to cast to Car
		car.setDeepCopy(false);
		Car car3=(Car )car.clone();
		
		
		//deep copy ( what the override of clone does in Car )
		car.setDeepCopy(true);
		Car car4=(Car)car.clone();
		//change the weight of the original
		car.setWeight(2500);
		//only the alias should see the change
		//shallow copy doesn't
		System.out.println(" Alias :  "+car2);
		System.out.println("Shallow copy :"+car3);
		System.out.println("deep  copy :"+car4);
		//change the age of owner
		car.getOwner().setAge(car.getOwner().getAge()+1);
		//both the alias and shallow copy see the change
		System.out.println(" Alias :  "+car2);
		System.out.println("Shallow copy :"+car3);
		System.out.println("deep  copy :"+car4);
		
	
		
		
		
		
		
		
		
		
	}
}
