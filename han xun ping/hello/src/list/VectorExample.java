package list;
import java.util.*;
public class VectorExample {

	public static void main(String[] args)throws Exception {
	
		
		Vector vv=new Vector();
		Emp emp=new Emp("aa01","aa",1.2f);
		vv.add(emp);
		for (int i=0;i<vv.size();i++)
		{
			//System.out.println((Emp)li.getFirst());
			//getFirst �ӵ�һ����ʼȡ,
			System.out.println(((Emp)vv.get(i)).getName());
			//��Ϊ���ص���һ���࣬����Ҫ�����һ���,ҲҪǿת
		}
	
	}
}
