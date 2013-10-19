package list;
import java.util.*;
public class HashMapExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ��hashmap ����
		HashMap hm=new HashMap();
		Emp emp1=new Emp("aa01","aa",1.2f);
		Emp emp2=new Emp("bb01","bb",1.5f);
		Emp emp3=new Emp("cc01","cc",1.7f);
		//��emloyee�ŵ�hashmap ��
		hm.put("aa01", emp1);
		hm.put("bb01", emp2);
		hm.put("cc01", emp3);
		//��ʱ��ֻҪͨ�� aa01 ���key value �ҵ����� �ٶȾͻ�ܿ졣
		
		/*
		 * hm.put("bb01", emp2);
		 * hm.put("bb01", emp3);
		 * �������� key value ����ͬ�ģ�������� �ĻḲ�ǵ�ǰ���key value
		 */
		
		//�����Ҫ���ұ����s002 ������ˣ�
		if(hm.containsKey("bb01"))
		{
			System.out.println("yes it has");
			//ȡ�� ���ݣ��Ͳ���Ҫ������ ֱ����get ����ֱ������key value,Ӧ�ܵõ��Ǹ�ֵ
			Emp emp=(Emp)hm.get("bb01");
			System.out.println("name: "+emp.getName());
		}else{
			System.out.println("no  it doesn't has");
		}
		
		//����hashmap �����е�  key and  value
		//  hm.get(key) ���Ա������е�Ԫ�أ�����������key value
		//��Ϊ���ǲ�֪�� key value, ���Բ�����for loop ѭ��
		//����Ҫ��iterator ������
		Iterator it =hm.keySet().iterator();
		while (it.hasNext())
		{//hasNext����һ��boolean,��Ϊhasmap ���ж��ٶ��������ǲ�֪����
		//�������ȡ��key   
		// ��Ϊ�����е�key ��String,����Ҫת���� String
			//��Ϊ it.next() ���ص���object, ����Ҫ�� .tostring()ת��Ϊ�ַ���
			String key =it.next().toString();
			//ͨ��key ȡ��     value
			Emp emp=(Emp)hm.get(key);
			//��Ϊhm.get(key)  ���ص�Ҳ��object ,����Ҫ����ǿ��ת��ΪEmp
			
			System.out.println("name  :"+emp.getName()+"      salary : "+emp.getSal());
			//hasmap ��ȱ�������Ĺ����Խϲ��ʱ���޷�������˳������
		}
		
		
		
	}

}
