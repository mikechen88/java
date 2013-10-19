package list;
import java.util.*;
public class LinkListExample {
	public static void main(String[] args)throws Exception {
		
		LinkedList li=new LinkedList();
		Emp emp=new Emp("aa01","aa",1.2f);
		Emp emp2=new Emp("bb01","bb",1.2f);
		//addFirst 表示 把emp加在链表的最前面
		li.addFirst(emp);
		li.addFirst(emp2);
		//addLast();   加在最后
		for (int i=0;i<li.size();i++)
		{
			//System.out.println((Emp)li.getFirst());
			//getFirst 从第一个开始取
			System.out.println(((Emp)li.get(i)).getName());
			//因为返回的是一个类，所以要多加上一层包
		}
	
	}
}
