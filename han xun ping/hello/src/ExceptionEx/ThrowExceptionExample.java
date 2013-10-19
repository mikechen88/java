package ExceptionEx;
import java.io.*;
public class ThrowExceptionExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Father father=new Father();
		father.test1();
	}

}
class Father
{
	private Son son=null;
	public Father()
	{
		son=new Son();
	}
	public void test1()
	{
		System.out.println("1");
		try {
			son.test2();
			//因为son 抛出异常，Father如果不管，必须 把son的异常接着抛出。
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("father");
		}
		
	}
}
class Son{
	public void test2() throws Exception
	//把这个异常抛给调用者去处理，Father 为调用者，所以要对它负责
	{
		FileReader fr=null;
		fr=new FileReader("d:\\dd.txt");
	}
}