/**
 * ��ʾ���ͨ���̳�Thread�������߳�
 * 1����Ҫ�̵߳���̳� Thread �� ����ʵ�� Thread�������е�run()����
 * 2������Thread���е� start() ����������һ���߳�  ����߳��Զ����� run����
 */
package Tank2plus;

public class Demmo10_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat cat=new Cat();
		//�����߳�
		cat.start();   
		//start()�ᵼ��run����������
	}

}
class Cat extends Thread
{
	int times=0;
	//��дrun() ����
	public void run()
	{
		while(true)
		{	//����һ��   �Ժ���Ϊ��λ
			try {
				// sleep�ͻ����߳̽��뵽block״̬�����ͷ���Դ
				Thread.sleep(1000);    //����̻߳��Զ���Ϣһ����Ϣ���� �̻߳��Զ�����,1000����=1��
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