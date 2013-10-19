package Tank2plus;

/**
 * ���ܣ�̹����Ϸ��2.0�汾
 * 1������̹��
 * 2:ʵ��̹�����������ƶ������� �������˵�̹��
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class MyTankGame2 extends JFrame{

	MyPanel mp=null;
	public static void main(String[] args) {
		
		MyTankGame2 mtg=new MyTankGame2();
	}
	//���캯��
	public MyTankGame2()
	{
		mp=new MyPanel();
		this.add(mp);
		//ע�����
		this.addKeyListener(mp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
//�ҵ����
class MyPanel extends JPanel implements KeyListener
{
	//����һ���ҵ�̹��
	Hero hero=null;
	//������˵�̹��
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	int enSize=3;
	//���캯��
	public MyPanel()
	{	//�ҵ�̹��
		hero=new Hero(150,150);
		//��ʼ�����˵�̹��
		for(int i=0; i<enSize; i++)
		{	// ����һ������̹��
			EnemyTank  et=new EnemyTank((i+1)*50,0);
			et.setColor(0);
			et.setDirect(2);
			//���뵽�����
			ets.add(et);
		}
	}
	
	//��дpaint
	public void paint(Graphics g)
	{
		super.paint(g);
		//����ͼ��ı���ɫ
		g.fillRect(0, 0, 600, 400);
		this.drawTank(hero.getX(),hero.getY(), g, hero.direct, 1);
		//�������˵�̹��
		for(int i=0; i<ets.size(); i++)
		{
			//get(i)   ����һ��̹��
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 0);
		}
	}
	//����̹�˵ĺ���
	public void drawTank(int x, int y, Graphics g, int direct, int type)
	{
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		// �жϷ���
		switch(direct)
		{
		case 0:   // ����
			
			//�����ҵ�̹�ˣ���ʱ����װ��һ��������
			//1����������ľ���
			//���û��ʵ���ɫ�����ƾ���
			//1��������ߵľ���
			g.fill3DRect(x, y, 5, 30,false);
			//2�������ұߵľ���
			g.fill3DRect(x+15,y, 5, 30,false);
			//3:�����м����
			g.fill3DRect(x+5, y+5,10, 20,false);
			//4:����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//5:������Ͳ
			g.drawLine(x+10, y+15, x+10, y);	
			break;
		case 1:    //  ����
			g.fill3DRect(x, y, 30, 5, false);
			//
			g.fill3DRect(x, y+15, 30, 5, false);
			//
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//
			g.fillOval(x+10, y+5, 10, 10);
			//
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		case 2: // ����
			//�����ҵ�̹�ˣ���ʱ����װ��һ��������
			//1����������ľ���
			//���û��ʵ���ɫ�����ƾ���
			//1��������ߵľ���
			g.fill3DRect(x, y, 5, 30,false);
			//2�������ұߵľ���
			g.fill3DRect(x+15,y, 5, 30,false);
			//3:�����м����
			g.fill3DRect(x+5, y+5,10, 20,false);
			//4:����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//5:������Ͳ
			g.drawLine(x+10, y+15, x+10, y+30);	
			break;
		case 3:  //����
			g.fill3DRect(x, y, 30, 5, false);
			//
			g.fill3DRect(x, y+15, 30, 5, false);
			//
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//
			g.fillOval(x+10, y+5, 10, 10);
			//
			g.drawLine(x+15, y+10, x, y+10);
			break;
			
		}
		
		
	}
	//�����´��� a s w d
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			//���� ǰ��
			this.hero.setDirect(0);
			this.hero.moveUp();
		}
		else if(e.getKeyCode()==KeyEvent.VK_D)
		{
			//����ǰ��
			this.hero.setDirect(1);
			this.hero.moveRight();
		}
		else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			//����ǰ��
			this.hero.setDirect(2);
			this.hero.moveDown();
		}
		else if(e.getKeyCode()==KeyEvent.VK_A)
		{
			//����ǰ��
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		//���»��ƴ���
		this.repaint();
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
	
