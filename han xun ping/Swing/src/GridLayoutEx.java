/*
 * gridlayout
 * 
 */
import java.awt.*;
import javax.swing.*;
public class GridLayoutEx extends JFrame{

	
	int size=9;
	JButton jbs[]=new JButton[size];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GridLayoutEx gl=new GridLayoutEx();
	}
	//constructor
	public GridLayoutEx()
	{
		for (int i=0;i<size;i++)
		{
			jbs[i]=new JButton(String.valueOf(i));
		}
		
		//set gridlayout    ��һ��3 ��ʾ�У��ڶ���3 ��ʾ��
		//this.setLayout(new GridLayout(3,3));
		//���ü�϶�����̫������,�����ᵼ�°�ť��С�����岻��
		this.setLayout(new GridLayout(3,3,10,10));
		
		
			//add component
		for (int i=0;i<size; i++)
		{
			this.add(jbs[i]);
		}
		
		
		//���ô�������
		this .setTitle("gridlayout example");
		this.setSize(300,200);
		this.setLocation(200,200);
		
		//��ֹ�ı䴰��Ĵ�С
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//��ʾ����
		
		this.setVisible(true);
		
		
	}

	
	
}
