package Tank2plus;

/**
 * ��Runnable ��ʵ��һ���߳�
 * 1��һ�������ʵ��һ��Runnable �ӿ� ʵ�� run()����
 * 2��������ඨ��������������Ը� Thread ��������Ȼ����� Thread���е�start���������߳�
 */


public class Demo10_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog dog=new Dog();
		//����һ���̶߳���
		Thread t=new Thread(dog);  // ���붨���һ�̶߳��� Ȼ��� ʵ�� Runnable�ӿڵĶ����������
		//Ȼ����ö���t��star��������
		t.start();
		
	}

}
class Dog implements Runnable
{
	int times=0;
	//��дrun ����
	public void run()
	{
		while(true)
		{	//����һ��   �Ժ���Ϊ��λ
			try {
				// sleep�ͻ����߳̽��뵽����״̬�����ͷ���Դ
				Thread.sleep(1000);    //����̻߳��Զ���Ϣһ����Ϣ���� �̻߳��Զ�����
			} catch (Exception e) {
				// TODO: handle exception
			}
			times++;
			System.out.println("hello,word"+times);
			if(times==10)
			{
				break;
			}
		}
	}
}