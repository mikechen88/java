package list;
//引入一个包给  集合使用
import java.util.*;
public class ArrayList1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList  al=new ArrayList();
		//显示大小
	
		//创建 一个职员
		Clerk  clerk1=new Clerk("aa",50,1000);
		Clerk  clerk2=new Clerk("bb",45,1200);
		Clerk  clerk3=new Clerk("cc",30,1400);
		Clerk  clerk4=new Clerk("dd",45,1600);
		//将clerk1 加入到 al 中
		al.add(clerk1);
		al.add(clerk2);
		al.add(clerk3);
		al.add(clerk4);
		al.remove(1);
		System.out.println("al size is "+ al.size());
		//访问al中对象（数据）
		Clerk temp=(Clerk)al.get(0);
		//把父类 付给子类，要强制转换。？？？？？？？？？？？？为什么是父类
		System.out.println("the first people's name is :  "+temp.getName());
	}

}

class Clerk
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

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}

	private int age;
	private float sal;
	
	public Clerk (String name, int age, float sal)
	{
		this .name=name;
		this .age =age;
		this.sal=sal;
		
	}
	
}