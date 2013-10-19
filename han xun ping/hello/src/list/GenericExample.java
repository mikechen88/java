package list;
import java.util.*;
public class GenericExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Dog> al=new ArrayList<Dog>();
		Dog dog1=new Dog();
		al.add(dog1);
		//Dog temp=(Dog)al.get(0);
		//没使用generic 时，如果不用（Dog)强转，会报错。 因为object 不能交给子类。
		
		Dog temp=al.get(0);
		//使用generic 后，就不需要 使用强行转换，这样就安全了
				
		/*Cat temp=(Cat)al.get(0);
		 * 这样会出现类型转换出错，因为里面存的是一只狗，但得到的是一只猫。
		 * 但在编译的时候不知道出错，在运行的时候才知道出现异常
		 *   classCastException 类型转换异常
		 * 所以要使用generic
		 */	
		
	}

}
class Cat
{
	private String color;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private int  age;
}
class Dog
{
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private int age;
	
	
}
