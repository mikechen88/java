/**
 * 用户登录的闪屏效果
 */
package com.myl.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Index extends JWindow implements Runnable{

	paint p;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Index index=new Index();
		Thread t=new Thread(index);
		t.start();
	}
	public Index()
	{
		//创建p
		p=new paint();
		this.add(p);
		this.setSize(400, 250);
		//确定JWindow的初始位置
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, height/2-150);
		this.setVisible(true);
	}
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			//根据计算等我们等待闪屏效果结束跳转到登录界面
			try {
				Thread.sleep(30*500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//跳转到登录界面
		//	System.out.println("闪屏结束");
			
			
			new UserLogin();
			//自动跳转到userLogin页面，同时让自己消失
			this.dispose();
			break;//break 退出线程
		}
	}
}


//闪屏效果
class paint extends JPanel implements Runnable
{
	Thread t;
	int x=10;
	int i=0, j=40, u=10;
	String gg[]={"系","统","正","在","加","载","请","稍","后"};
	int k=0, tt=0;
	String shi[]={"满","汉","楼","容","满","汉","精","华","做","天","下","美","味","招","八","方","食","客","结","四","海","朋","友","|","满","汉","楼","工","作","室"};
	Font f=new Font("隶属",Font.PLAIN, 18);
	
	boolean ifok=true;
	int width=180;
	int height=0;
	int dian=0;
	
	paint ()
	{
		t=new Thread(this);
		t.start();
	}
	
	
	
	public void run() {
		while(true)
		{
			if(x<=380) repaint();
			try {
				Thread.sleep(70);
				i++;
				j=j-6;
				u=u+10;
				
				if(tt==3) width=width-20;
				
				if(i==4)
				{
					tt++;
					if(ifok&&x>120+k*20) k++;
					if(k>=gg.length-1) ifok=false;
					x=x+10;
					i=0;
					j=40;
					u=10;
					dian++;
					if(dian>3) dian=0;	
				}
					
			} catch (InterruptedException e) {
				System.out.println("线程中断");
			}
		
		}
		
	}
	
	public void paintComponent(Graphics g)
	{
		Image image;
		image=Toolkit.getDefaultToolkit().getImage("image\\sp.jpg");
		g.drawImage(image, 0, 0, this.getWidth(), 200, this);
		
		int r=(int)(Math.random()*255);
		int b=(int)(Math.random()*255);
		int y=(int)(Math.random()*255);
		
		g.setColor(new Color(253,250,250));
		g.fillRect(x, 210, 390-x, 30);
		g.setColor(new Color(253,250,250));
		if(j>1) g.fillRect(x, 255-(j+20)/2, 10, j+20);
		if(j>25) g.setColor(new Color(r,b,y));
		else g.setColor(new Color(123,194,252));
		g.fillRect(x, 225-j/2, 10, j);
		g.setColor(new Color(123,194,252));
		g.drawRect(10, 210, 380, 30);
		
		if(x<120)
		{
			for(int l=0; l<gg.length; l++)
			{
				g.setColor(new Color(0,0,0));
				g.drawString(gg[l], 120+l*20, 230);
			}
			for(int l=0; l<dian; l++)
			{
				g.setColor(new Color(0,0,0));
				g.drawString("*", 300+l*10 ,235);
			}
			g.drawString("*", 300+10*dian ,235);
		}
			else
			{
				g.setColor(new Color(23,23,230));
				g.drawString(gg[k], 120+k*20, 230);
				for(int l=k+1; l<gg.length; l++)
				{
					g.setColor(new Color(0,0,0));
					g.drawString(gg[l], 120+l*20, 230);
				}
				if(x>300+dian*10) g.setColor(new Color(23,23,230));
				
				for(int l=0; l<dian; l++)
				{
					g.drawString("*", 300+l*10, 235);
				}
				g.drawString("*", 300+10*dian, 235);
			}
			
		//------------逐个写写诗
		if(tt<3)
		{
			for(int rr=0; rr<=tt; rr++)
			{
				g.setColor(new Color(r,b,y));
				g.drawString(shi[rr], 180, 60+rr*20);
			}
			g.drawString(shi[tt], 180, 60+tt*20);
		}
		
		if(tt>=3&tt<7)
		{
			g.setColor(new Color(230,0,0));
			for(int rr=0; rr<3; rr++) g.drawString(shi[rr], 180, 60+rr*20);
			g.setColor(new Color(r, b, y));
			if(tt<8) for(int rr=3; rr<tt; rr++) g.drawString(shi[rr], 150, rr*20-20);
			if(tt>=7) for (int rr=3; rr<8; rr++) g.drawString(shi[rr], 150, rr*20-20);
		}
		if(tt>=7&&tt<13)
		{
			g.setColor(new Color(230,0,0));
			for(int rr=0; rr<3; rr++) g.drawString(shi[rr], 180, 60+rr*20);
			for(int rr=3; rr<=7; rr++) g.drawString(shi[rr], 150, rr*20-20);
			g.setColor(new Color(r, b, y));
			if(tt<13) for(int rr=8; rr<tt; rr++) g.drawString(shi[rr], 120, 150);
			if(tt>=13) for (int rr=8; rr<13; rr++) g.drawString(shi[rr], 120, 150);
		}
		if(tt>=13&&tt<18)
		{
			g.setColor(new Color(230,0,0));
			for(int rr=0; rr<3; rr++) g.drawString(shi[rr], 180, 60+rr*20);
			for(int rr=3; rr<=7; rr++) g.drawString(shi[rr], 150, rr*20-20);
			for(int rr=8; rr<=13; rr++) g.drawString(shi[rr], 120, rr*20-120);
			g.setColor(new Color(r, b, y));
			if(tt<18) for(int rr=13; rr<tt; rr++) g.drawString(shi[rr], 90, rr*20-220);
			if(tt>=18) for (int rr=13; rr<13; rr++) g.drawString(shi[rr], 90, rr*20-220);
		}
		if(tt>=18&&tt<23)
		{
			g.setColor(new Color(230,0,0));
			for(int rr=0; rr<3; rr++) g.drawString(shi[rr], 180, 60+rr*20);
			for(int rr=3; rr<=7; rr++) g.drawString(shi[rr], 150, rr*20-20);
			for(int rr=8; rr<=13; rr++) g.drawString(shi[rr], 120, rr*20-120);
			for(int rr=13; rr<=18; rr++) g.drawString(shi[rr], 90, rr*20-220);
			g.setColor(new Color(r, b, y));
			if(tt<23) for(int rr=18; rr<tt; rr++) g.drawString(shi[rr], 60, rr*20-320);
			if(tt>=23) for (int rr=18; rr<23; rr++) g.drawString(shi[rr], 60, rr*20-320);
		}
		if(tt>=23&&tt<30)
		{
			g.setColor(new Color(230,0,0));
			for(int rr=0; rr<3; rr++) g.drawString(shi[rr], 180, 60+rr*20);
			for(int rr=3; rr<=7; rr++) g.drawString(shi[rr], 150, rr*20-20);
			for(int rr=8; rr<=13; rr++) g.drawString(shi[rr], 120, rr*20-120);
			for(int rr=13; rr<=18; rr++) g.drawString(shi[rr], 90, rr*20-220);
			for(int rr=18; rr<=23; rr++) g.drawString(shi[rr], 60, rr*20-320);
			g.setColor(new Color(r, b, y));
			if(tt<30) for(int rr=23; rr<tt; rr++) g.drawString(shi[rr], 30, rr*20-400);
			if(tt>=30)  for(int rr=23; rr<30; rr++) g.drawString(shi[rr], 60, rr*20-400);
		}
		if(tt>=30)
		{
			g.setColor(new Color(230,0,0));
			for(int rr=0; rr<3; rr++) g.drawString(shi[rr], 180, 60+rr*20);
			for(int rr=3; rr<=7; rr++) g.drawString(shi[rr], 150, rr*20-20);
			for(int rr=8; rr<=13; rr++) g.drawString(shi[rr], 120, rr*20-120);
			for(int rr=13; rr<=18; rr++) g.drawString(shi[rr], 90, rr*20-220);
			for(int rr=18; rr<=23; rr++) g.drawString(shi[rr], 60, rr*20-320);
			for(int rr=23; rr<30; rr++) g.drawString(shi[rr], 30, rr*20-400);
		}
		}
}

