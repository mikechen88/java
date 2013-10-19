package Homework;

public class SuperExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Son2  son=new Son2(1,"dd");
		
	}

}

class   Base{
	int age;
	String name;
	public Base (int age,String name)
	{
		System.out.println("base");
		this.name=name;
		this.age=age;
	}
}
class Son2 extends Base
{
	public Son2(int age,String name)
	{
		
		super(age,name);//super必须要放在最前面
		System.out.println("son");
		
	}
}
