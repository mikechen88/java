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
			//getFirst 从第一个开始取,
			System.out.println(((Emp)vv.get(i)).getName());
			//因为返回的是一个类，所以要多加上一层包,也要强转
		}
	
	}
}
