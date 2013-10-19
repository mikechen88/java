package list;
import java.util.*;
public class HashMapExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建一个hashmap 对象
		HashMap hm=new HashMap();
		Emp emp1=new Emp("aa01","aa",1.2f);
		Emp emp2=new Emp("bb01","bb",1.5f);
		Emp emp3=new Emp("cc01","cc",1.7f);
		//将emloyee放到hashmap 中
		hm.put("aa01", emp1);
		hm.put("bb01", emp2);
		hm.put("cc01", emp3);
		//到时候，只要通过 aa01 这个key value 找到它。 速度就会很快。
		
		/*
		 * hm.put("bb01", emp2);
		 * hm.put("bb01", emp3);
		 * 如果输入的 key value 是相同的，最后输入 的会覆盖掉前面的key value
		 */
		
		//如果你要查找编号是s002 的这个人，
		if(hm.containsKey("bb01"))
		{
			System.out.println("yes it has");
			//取出 数据，就不需要遍历， 直接用get 方法直接输入key value,应能得到那个值
			Emp emp=(Emp)hm.get("bb01");
			System.out.println("name: "+emp.getName());
		}else{
			System.out.println("no  it doesn't has");
		}
		
		//遍历hashmap 中所有的  key and  value
		//  hm.get(key) 可以遍历其中的元素，但必须输入key value
		//因为我们不知道 key value, 所以不能用for loop 循环
		//所以要用iterator 迭代器
		Iterator it =hm.keySet().iterator();
		while (it.hasNext())
		{//hasNext返回一个boolean,因为hasmap 含有多少东西，它是不知道的
		//如果有则取出key   
		// 因为本例中的key 是String,所以要转换成 String
			//因为 it.next() 返回的是object, 所以要加 .tostring()转换为字符串
			String key =it.next().toString();
			//通过key 取出     value
			Emp emp=(Emp)hm.get(key);
			//因为hm.get(key)  返回的也是object ,所以要将其强制转换为Emp
			
			System.out.println("name  :"+emp.getName()+"      salary : "+emp.getSal());
			//hasmap 的缺点是他的规律性较差，有时候无法按正常顺序排序
		}
		
		
		
	}

}
