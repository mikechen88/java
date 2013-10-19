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
			//让程序随机产生一个1--100000的数
			//Math.random() 会产生一个0到1 之间的数,*10000后，就会得到 一个0到10000 之间的数。
			int  t=(int )(Math.random()*10000);
			arr[i]=t;
		}
		
		Select select =new Select();
		//在排序前打印系统时间
		Calendar     cal=Calendar.getInstance();
		System.out.println("before  sort:   "+cal.getTime());
		select.sort(arr);
		//重新得到一个实例，否则永远是相同的。因为它是单态的。
			    cal=Calendar.getInstance();
		System.out.println("after  sort:   "+cal.getTime());
		//最后，输出结果。因为前面己花了很多时间，而且打印会花更多的时间，所以 省略掉。
		/*		for (int i=0;i<arr.length;i++)
				{
					System.out.print(arr[i]+ " ");		
				}*/
	}
}

class Select
{//选择排序
	public void sort (int   arr[])
	{
		int   temp=0;
		for ( int j=0;j<arr.length-1;j++)
		{
			//认为第一个数就是最小的
			int min=arr[0];
			//记录最小数的下标
			int minIndex=0;
			
			for (int k=j+1;k<arr.length;k++)
			{
				if (min>arr[k])
				{
					min=arr[k];
					minIndex=k;
				}				
			}
			//当退出这个for 就找到这次的最小值
			temp=arr[j];
			arr[j]=arr[minIndex];
			arr[minIndex]=temp;
		}
	}
	
	
	
}