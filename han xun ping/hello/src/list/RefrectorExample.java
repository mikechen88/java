package list;

import java.lang.reflect.Method;

public class RefrectorExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Gen<Integer> gen1=new Gen<Integer>("aaa");
		 * ����Ϊinteger ʱ�����Զ���� constructor ���� �Ĳ������������
		 * integer ʱ��ϵͳ�ᱨ��
		 * Gen<Integer> gen1=new Gen<Integer>(1);
		 * �������С�
		 */
		
		
		/*���generic �� �D ��һ����
		 * Gen<Bird> gen1=new Gen<Bird>(1);
		 * ����������1�����ͻᷢ���ⲻ�� Bird ��һ��ʵ����Ҫ��Ϊ
		 * Gen<Bird> gen1=new Gen<Bird>(new  Bird()); 
		 */
		Gen<String> gen1=new Gen<String>("aaa");
		
		gen1.showTypeName();
	}

}
class Gen<T>
{  //���T ��ζ�Ŵ���ȥʲô���ͣ�������ʲô���͡�
	private T o;
	//�õ�T������
	
	public Gen(T a)
	{
		o=a;
	}
	public void showTypeName()
	{
		System.out.println( "type  is    "+o.getClass().getName());
		//ͨ��������ƣ����ǿ���֪�� T ������͵ĺܶ���Ϣ������õ���Ա������......
		Method []m=o.getClass().getMethods();
		//��ӡ
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