
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
		//�����ҵ��м����
		int midIndex=(rightIndex+leftIndex)/2;
		int midVal=arr[midIndex];
		
		if(rightIndex>=leftIndex){//Ϊ��ֹ���ݹ�
		//���Ҫ�ҵ�������midVal��
			if(midVal>val)
			{
				//��arr �������
				find (leftIndex,midIndex-1,val,arr);
			}else if (midVal<val){
				//��arr���ұ�����ȥ��
				find (midIndex+1,rightIndex,val,arr);
			}else if (midVal==val){
				System.out.println(" got it   "+midIndex);
			}
		}
		
	}
	
}