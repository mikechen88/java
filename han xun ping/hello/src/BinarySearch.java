
public class BinarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]={2,5,7,12,25,46,56,76};
		Find find=new Find();
		find.find (0,arr.length,7,arr);
	}

}

class Find
{
	public void find(  int leftIndex,int rightIndex,int val,int arr[])
	{
		//首先找到中间的数
		int midIndex=(rightIndex+leftIndex)/2;
		int midVal=arr[midIndex];
		
		if(rightIndex>=leftIndex){//为防止死递归
		//如果要找的数，比midVal大，
			if(midVal>val)
			{
				//在arr 左边中找
				find (leftIndex,midIndex-1,val,arr);
			}else if (midVal<val){
				//在arr的右边数中去找
				find (midIndex+1,rightIndex,val,arr);
			}else if (midVal==val){
				System.out.println(" got it   "+midIndex);
			}
		}
		
	}
	
}