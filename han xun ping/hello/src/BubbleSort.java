
public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr [] ={1,6,0,-1,9};
		//sort 
		//���ѭ����һ���߼���
		int temp=0;  //��ʱ���������ڻ�λ
		for (int i=0 ;i<arr.length-1;i++)
		{
			//�ڲ�ѭ������ʼ����Ƚϣ��������ǰһ�����Ⱥ�һ�������򽻻�
			for (int j=0;j<arr.length-1-i;j++)//-i ����Ϊ�ȽϹ���һ������ǰ��������ľͲ��ñȽ���
			{
				if (arr[j]>arr[j+1])
				{
					//��λ
					temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;					
				}			
			}			
		}
		//�����������
		for (int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+ " ");		
		}			
	}
}
