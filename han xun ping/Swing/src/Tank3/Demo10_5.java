package Tank3;

/**
 * 一个售票点由3 个分点卖票
 */


public class Demo10_5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*
		这样是各自卖各自的票，而不是共享票，卖出的是总票数的3 倍，所以不实用。
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
		
		//定义一个售票中心
				TicketWindow tw1=new TicketWindow();
		//创建三个售票点，同时启动，同时卖共同的票。
		Thread t1=new Thread(tw1);
		Thread t2=new Thread(tw1);
		Thread t3=new Thread(tw1);
		//开始售票
		t1.start();
		t2.start();
		t3.start();
	}

}


// 售票窗口
class TicketWindow implements Runnable
{
	private int nums=100;
	//一共两千张
	public void run()
	{
		while (true)
		{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
	
			
		// 没上锁前	
			
//			//先判断是否有票
//			if(nums>0)
//			{
//				//Thread.currentThread().getName() 当前线程的名字
//				System.out.println(Thread.currentThread().getName()+"正在售出第"+nums+"张票");
//				nums--;
//			}
//			else
//			{
//				//售票结束
//				break;
//			}
//			
			
		//	 加了上锁
			synchronized(this)    //上锁 this 可以是任意一个已经定义的对象，
			{//if else 要保证原子性，即同步代码块
				//先判断是否有票
				if(nums>0)
				{
					//Thread.currentThread().getName() 当前线程的名字
					System.out.println(Thread.currentThread().getName()+"正在售出第"+nums+"张票");
					nums--;
				}
				else
				{
					//售票结束
					break;
				}
			}
		
		}
	}
}
