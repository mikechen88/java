import java.util.Calendar;


public class QuickSort {

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
		
		Quick select =new Quick();
		//������ǰ��ӡϵͳʱ��
		Calendar     cal=Calendar.getInstance();
		System.out.println("before  sort:   "+cal.getTime());
		select.sort(0,arr.length-1,arr);
		//���µõ�һ��ʵ����������Զ����ͬ�ġ���Ϊ���ǵ�̬�ġ�
			    cal=Calendar.getInstance();
		System.out.println("after  sort:   "+cal.getTime());
				for (int i=0;i<arr.length;i++)
				{
					System.out.print(arr[i]+ " ");		
				}
	}

}

class Quick {
	public void sort (int left, int right , int [] array)
	{// left ������ߵ��±ߣ�  right�����ұߵ��±ꡣ
		int l=left;
		int r=right;
		int pivot=array[(left+right)/2];  //������м����
		int temp=0;
		
		while (l<r)
		{
			while (array[l]<pivot) l++;
			while (array[r]>pivot) r--;
			
			if (l>=r) break;
			
			temp=array[l];
			array[l]=array[r];
			array[r]=temp;
			
			if (array[l]==pivot) --r;
			if (array[r]==pivot) ++l;			
		}
	
		if (l==r){
			l++;
			r--;
		}
		
		if (left<r) sort(left,r,array);
		if (right>l) sort( l,right, array);
	}
	
}