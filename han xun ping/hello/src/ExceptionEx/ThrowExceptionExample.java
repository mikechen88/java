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
			//��Ϊson �׳��쳣��Father������ܣ����� ��son���쳣�����׳���
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("father");
		}
		
	}
}
class Son{
	public void test2() throws Exception
	//������쳣�׸�������ȥ����Father Ϊ�����ߣ�����Ҫ��������
	{
		FileReader fr=null;
		fr=new FileReader("d:\\dd.txt");
	}
}