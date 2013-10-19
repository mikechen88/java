
public class BubbleSortEncapsulate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr [] ={1,6,0,-1,9};
		//创建一个Bubble 对象
		Bubble  bubble=new Bubble();
		bubble.sort(arr);// 此处引用了arr数组在新的stack中，  而不是创建一个新的arr数组。因此，任何改变都会改变外部arr的排序。
					/*如果 在外部设置  一个简单变量               int     a=1;
					 * 传入为方法体                                               bubble.test ( a );     
					 * 在方法体中执行			a++
					 * 但是在外部打印  a 时，值不会变，还是   a      
					 */
		
		
		//最后，输出结果。
		for (int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+ " ");		
		}
	}
}
	
	
	class Bubble {
		//排序方法
		public  void  sort (int    arr[])
		{
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
		}		
	}



