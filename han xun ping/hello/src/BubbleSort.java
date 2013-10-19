
public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr [] ={1,6,0,-1,9};
		//sort 
		//外层循环，一共走几趟
		int temp=0;  //临时变量，用于换位
		for (int i=0 ;i<arr.length-1;i++)
		{
			//内层循环，开始逐个比较，如果发现前一个数比后一个数大，则交换
			for (int j=0;j<arr.length-1-i;j++)//-i 是因为比较过了一个，先前参与排序的就不用比较了
			{
				if (arr[j]>arr[j+1])
				{
					//换位
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;					
				}			
			}			
		}
		//最后，输出结果。
		for (int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+ " ");		
		}			
	}
}
