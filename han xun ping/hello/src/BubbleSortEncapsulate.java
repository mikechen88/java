
public class BubbleSortEncapsulate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr [] ={1,6,0,-1,9};
		//����һ��Bubble ����
		Bubble  bubble=new Bubble();
		bubble.sort(arr);// �˴�������arr�������µ�stack�У�  �����Ǵ���һ���µ�arr���顣��ˣ��κθı䶼��ı��ⲿarr������
					/*��� ���ⲿ����  һ���򵥱���               int     a=1;
					 * ����Ϊ������                                               bubble.test ( a );     
					 * �ڷ�������ִ��			a++
					 * �������ⲿ��ӡ  a ʱ��ֵ����䣬����   a      
					 */
		
		
		//�����������
		for (int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+ " ");		
		}
	}
}
	
	
	class Bubble {
		//���򷽷�
		public  void  sort (int    arr[])
		{
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
		}		
	}



