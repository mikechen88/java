/**
 * java ��ͼԭ��
 */
package Graphic;
import javax.swing.*;
import java.awt.*;
public class Demo9_2 extends JFrame {

	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo9_2 demo=new Demo9_2();
	}
	public Demo9_2()
	{
		MyPanel2 mp=new MyPanel2();
		this.add(mp);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
//����һ��MyPanel  ���ڻ�ͼ��ʵ�ֻ�ͼ������
class MyPanel2 extends JPanel
{
	//���� JPanel��paint ����  
	//Graphics ��ͼ����Ҫ��
	public void paint(Graphics g)  //��������ᱻϵͳ�Զ�����, ������ⴰ�ڵĴ�С�����±����ã�
	{
		//1:���ø��ຯ����ɳ�ʼ������
		super.paint(g);      //���д��벻���٣����˾Ͳ��ܻ�ͼ��  
		//��һ��Բ
//		System.out.println("paint������");
//		g.drawOval(10, 10, 30, 30);   // ��Բ
//		
//		g.drawLine(10, 10, 50, 50);   // ��ֱ��
//		
//		g.drawRect(10,10, 50, 50);

		
		
		
//		//��һ������ɫ�ľ���
//		g.setColor(Color.blue);
//		g.fillRect(50,50, 60, 80);
//		
//		//��һ������ɫ�ľ���
//		g.setColor(Color.red);
//		g.fillRect(150,150, 60, 80);
	
		
		
//		
//		//������ϻ���ͼƬ,��סҪ��ͼƬ���뵽src
	Image im=Toolkit.getDefaultToolkit().getImage
		(Panel.class.getResource("/face.jpg"));
//		//ʵ��ͼƬ,  this ��ʾ  jpanel
	 g.drawImage(im, 10, 10, 100 , 150, this);
		
		
		 
		
		//������
		g.setColor(Color.red);    //����������ɫ
		g.setFont(new Font("���Ĳ���",Font.BOLD, 30));  //���������ʽ
		g.drawString("��ɣ����", 100,100);  //��������
		 
		 
	}
}
