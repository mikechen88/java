import javax.swing.*;
import java.awt.*;

public class JframExample extends JFrame {
	
	//����Ҫ��swing ��� ��������ͷ���ڷ������ڴ����������������
	JButton jb1=null;
	public static void main(String[] args){
		JframExample jframExample=new JframExample();

	
	}
	//���캯��������Ҫ����������嵽���캯��������
	public JframExample()
	{
		//������     extends  JFrame �󣬾Ͳ���������仰��,Ȼ��Ѻ���������jf��ȫ��Ϊthis
		//JFrame jf=new JFrame();	
		
		
		//����һ��button,Ĭ��Ϊռ����������
		jb1=new JButton("i'm button");
		
		//���������ñ���
		this.setTitle("hello,world");
		//���ô�С�� �����ء����Ǹ��ܶȵ�λ
		this.setSize(200,200);
		//Ĭ�ϳ�ʼλ���������Ͻǣ���Ҫ�ı��ʼλ�þ�
		this.setLocation(100,200);
		
		//���JBUTTON���
		this.add(jb1);
		
		//���õ��رմ���ʱ����֤jvm Ҳ�˳�������࿪���Σ���ռ�úܶ��ڴ档
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʾ����β�������ʾ��Ĭ���ǲ���ʾ��
		this.setVisible(true);
		
		
		
		
	}
}
