package Tank3;

/**
 * һ����Ʊ����3 ���ֵ���Ʊ
 */


public class Demo10_5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*
		�����Ǹ��������Ե�Ʊ�������ǹ���Ʊ������������Ʊ����3 �������Բ�ʵ�á�
		TicketWindow tw1=new TicketWindow();
		TicketWindow tw2=new TicketWindow();
		TicketWindow tw3=new TicketWindow();
		Thread t1=new Thread(tw1);
		Thread t2=new Thread(tw2);
		Thread t3=new Thread(tw3);
		t1.start();
		t2.start();
		t3.start();
		*/
		
		//����һ����Ʊ����
				TicketWindow tw1=new TicketWindow();
		//����������Ʊ�㣬ͬʱ������ͬʱ����ͬ��Ʊ��
		Thread t1=new Thread(tw1);
		Thread t2=new Thread(tw1);
		Thread t3=new Thread(tw1);
		//��ʼ��Ʊ
		t1.start();
		t2.start();
		t3.start();
	}

}


// ��Ʊ����
class TicketWindow implements Runnable
{
	private int nums=100;
	//һ����ǧ��
	public void run()
	{
		while (true)
		{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
	
			
		// û����ǰ	
			
//			//���ж��Ƿ���Ʊ
//			if(nums>0)
//			{
//				//Thread.currentThread().getName() ��ǰ�̵߳�����
//				System.out.println(Thread.currentThread().getName()+"�����۳���"+nums+"��Ʊ");
//				nums--;
//			}
//			else
//			{
//				//��Ʊ����
//				break;
//			}
//			
			
		//	 ��������
			synchronized(this)    //���� this ����������һ���Ѿ�����Ķ���
			{//if else Ҫ��֤ԭ���ԣ���ͬ�������
				//���ж��Ƿ���Ʊ
				if(nums>0)
				{
					//Thread.currentThread().getName() ��ǰ�̵߳�����
					System.out.println(Thread.currentThread().getName()+"�����۳���"+nums+"��Ʊ");
					nums--;
				}
				else
				{
					//��Ʊ����
					break;
				}
			}
		
		}
	}
}
