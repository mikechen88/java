/**
 * ��������ͬʱ���а���
 * 1:��������̲߳������й��ɵ����ж�����û�й��ɵĽ�������
 */
package Tank2plus;

public class Demo10_3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pig pig=new Pig(10);
		Bird bird=new Bird(10);
		Thread t1=new Thread(pig);
		Thread t2=new Thread(bird);
		t1.start();
		t2.start();
		//��������ǽ���ģ�����Ա�޷�����
	}

}
//��ӡ
class Pig implements Runnable
{
	int n=0;
	int times=0;
	public Pig(int n)
	{
		this.n=n;
	}
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			times++;
			System.out.println("��˵����һ���̣߳��������"+times+"��hello, word");
			if(times==n)
			{
				break;
			}
		}
	}
}
//����ѧ
class Bird implements Runnable
{
	int n=0;
	int res=0;
	int times=0;
	public Bird(int n)
	{
		this.n=n;
	}
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			res+=(++times);
			System.out.println("����ĵ�ǰ�����"+res);
			if(times==n)
			{
				System.out.println("�������"+res);
				break;
			}
		}
	}
}