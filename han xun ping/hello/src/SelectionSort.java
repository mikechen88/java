import java.util.*;
public class SelectionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int  len=200000;
		int arr [] =new int [len];
		for (int i=0;i<len;i++)
		{
			//�ó����������һ��1--100000����
			//Math.random() �����һ��0��1 ֮�����,*10000�󣬾ͻ�õ� һ��0��10000 ֮�������
			int  t=(int )(Math.random()*10000);
			arr[i]=t;
		}
		
		Select select =new Select();
		//������ǰ��ӡϵͳʱ��
		Calendar     cal=Calendar.getInstance();
		System.out.println("before  sort:   "+cal.getTime());
		select.sort(arr);
		//���µõ�һ��ʵ����������Զ����ͬ�ġ���Ϊ���ǵ�̬�ġ�
			    cal=Calendar.getInstance();
		System.out.println("after  sort:   "+cal.getTime());
		//�������������Ϊǰ�漺���˺ܶ�ʱ�䣬���Ҵ�ӡ�Ứ�����ʱ�䣬���� ʡ�Ե���
		/*		for (int i=0;i<arr.length;i++)
				{
					System.out.print(arr[i]+ " ");		
				}*/
	}
}

class Select
{//ѡ������
	public void sort (int   arr[])
	{
		int   temp=0;
		for ( int j=0;j<arr.length-1;j++)
		{
			//��Ϊ��һ����������С��
			int min=arr[0];
			//��¼��С�����±�
			int minIndex=0;
			
			for (int k=j+1;k<arr.length;k++)
			{
				if (min>arr[k])
				{
					min=arr[k];
					minIndex=k;
				}				
			}
			//���˳����for ���ҵ���ε���Сֵ
			temp=arr[j];
			arr[j]=arr[minIndex];
			arr[minIndex]=temp;
		}
	}
	
	
	
}