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
			//让程序随机产生一个1--100000的数
			//Math.random() 会产生一个0到1 之间的数,*10000后，就会得到 一个0到10000 之间的数。
			int  t=(int )(Math.random()*10000);
			arr[i]=t;
		}
		
		Insert select =new Insert();
		//在排序前打印系统时间
		Calendar     cal=Calendar.getInstance();
		System.out.println("before  sort:   "+cal.getTime());
		select.sort(arr);
		//重新得到一个实例，否则永远是相同的。因为它是单态的。
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
	
	//插入排序法
	public void sort (int arr[])
	{
		for (int i=1;i<arr.length; i++)
		{
			int  insertVal=arr[i];
			//insertVal 准备和前一个数比较；
			int index=i-1;
			while( index>=0&&insertVal<arr[index])
			{
				//就把arr[index]向后移动一位；
				arr[index+1]=arr[index];
				//让index向前移动
				index--;//因为index 变为-1，退出循环，进入下一步，就把插入的数字排在后面。		
			}
			//将insertVal插入到适当位置
			arr[index+1]=insertVal;
		}
	}
}
