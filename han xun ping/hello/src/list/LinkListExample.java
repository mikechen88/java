package list;
import java.util.*;
public class LinkListExample {
	public static void main(String[] args)throws Exception {
		
		LinkedList li=new LinkedList();
		Emp emp=new Emp("aa01","aa",1.2f);
		Emp emp2=new Emp("bb01","bb",1.2f);
		//addFirst ��ʾ ��emp�����������ǰ��
		li.addFirst(emp);
		li.addFirst(emp2);
		//addLast();   �������
		for (int i=0;i<li.size();i++)
		{
			//System.out.println((Emp)li.getFirst());
			//getFirst �ӵ�һ����ʼȡ
			System.out.println(((Emp)li.get(i)).getName());
			//��Ϊ���ص���һ���࣬����Ҫ�����һ���
		}
	
	}
}
