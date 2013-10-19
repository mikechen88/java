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
			//让程序随机产生一个1--100000的数
			//Math.random() 会产生一个0到1 之间的数,*10000后，就会得到 一个0到10000 之间的数。
			int  t=(int )(Math.random()*10000);
			arr[i]=t;
		}
		
		Quick select =new Quick();
		//在排序前打印系统时间
		Calendar     cal=Calendar.getInstance();
		System.out.println("before  sort:   "+cal.getTime());
		select.sort(0,arr.length-1,arr);
		//重新得到一个实例，否则永远是相同的。因为它是单态的。
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
	{// left 是最左边的下边，  right是最右边的下标。
		int l=left;
		int r=right;
		int pivot=array[(left+right)/2];  //这个是中间变量
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