import java.util.Calendar;


public class InsertSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int  len=20;
		int arr [] =new int [len];
		for (int i=0;i<len;i++)
		{
			//�ó����������һ��1--100000����
			//Math.random() �����һ��0��1 ֮�����,*10000�󣬾ͻ�õ� һ��0��10000 ֮�������
			int  t=(int )(Math.random()*10000);
			arr[i]=t;
		}
		
		Insert select =new Insert();
		//������ǰ��ӡϵͳʱ��
		Calendar     cal=Calendar.getInstance();
		System.out.println("before  sort:   "+cal.getTime());
		select.sort(arr);
		//���µõ�һ��ʵ����������Զ����ͬ�ġ���Ϊ���ǵ�̬�ġ�
			    cal=Calendar.getInstance();
		System.out.println("after  sort:   "+cal.getTime());
				for (int i=0;i<arr.length;i++)
				{
					System.out.print(arr[i]+ " ");		
				}
	}

}
class Insert
{
	
	//��������
	public void sort (int arr[])
	{
		for (int i=1;i<arr.length; i++)
		{
			int  insertVal=arr[i];
			//insertVal ׼����ǰһ�����Ƚϣ�
			int index=i-1;
			while( index>=0&&insertVal<arr[index])
			{
				//�Ͱ�arr[index]����ƶ�һλ��
				arr[index+1]=arr[index];
				//��index��ǰ�ƶ�
				index--;//��Ϊindex ��Ϊ-1���˳�ѭ����������һ�����ͰѲ�����������ں��档		
			}
			//��insertVal���뵽�ʵ�λ��
			arr[index+1]=insertVal;
		}
	}
}
