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
		//ûʹ��generic ʱ��������ã�Dog)ǿת���ᱨ�� ��Ϊobject ���ܽ������ࡣ
		
		Dog temp=al.get(0);
		//ʹ��generic �󣬾Ͳ���Ҫ ʹ��ǿ��ת���������Ͱ�ȫ��
				
		/*Cat temp=(Cat)al.get(0);
		 * �������������ת��������Ϊ��������һֻ�������õ�����һֻè��
		 * ���ڱ����ʱ��֪�����������е�ʱ���֪�������쳣
		 *   classCastException ����ת���쳣
		 * ����Ҫʹ��generic
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
