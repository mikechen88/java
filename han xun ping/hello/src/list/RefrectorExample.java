package list;

import java.lang.reflect.Method;

public class RefrectorExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Gen<Integer> gen1=new Gen<Integer>("aaa");
		 * 当设为integer 时，会自动检查 constructor 传入 的参数，如果不是
		 * integer 时，系统会报错。
		 * Gen<Integer> gen1=new Gen<Integer>(1);
		 * 这样才行。
		 */
		
		
		/*如果generic 的 籇 是一个类
		 * Gen<Bird> gen1=new Gen<Bird>(1);
		 * 如果输入的是1，它就会发现这不是 Bird 的一个实例，要改为
		 * Gen<Bird> gen1=new Gen<Bird>(new  Bird()); 
		 */
		Gen<String> gen1=new Gen<String>("aaa");
		
		gen1.showTypeName();
	}

}
class Gen<T>
{  //这个T 意味着传进去什么类型，它就是什么类型。
	private T o;
	//得到T的名称
	
	public Gen(T a)
	{
		o=a;
	}
	public void showTypeName()
	{
		System.out.println( "type  is    "+o.getClass().getName());
		//通过反射机制，我们可以知道 T 这个类型的很多信息（比如得到成员函数名......
		Method []m=o.getClass().getMethods();
		//打印
		for (int i=0;i<m.length;i++)
		{
			System.out.println(m[i].getName());
		}
	}
}
class  Bird
{
	public void test1()
	{
		System.out.println("aa");
	}
	public void count(int a, int b)
	{
		System.out.println(a+b);
	}
}