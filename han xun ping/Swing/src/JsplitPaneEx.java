import java.awt.*;
import javax.swing.*;
public class JsplitPaneEx  extends JFrame {

	JSplitPane jsp;
	JList jlist;
	JLabel jl1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsplitPaneEx jl=new JsplitPaneEx();
	}
	public JsplitPaneEx()
	{
		String [] words={"boy","girl","baby"};
		jlist=new JList(words);
		
		jl1=new JLabel(new ImageIcon("Desert.jpg"));
		
		// ��ִ���,���԰Ѵ���ˮƽ��ֻ�ֱ���
				jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jlist, jl1);
				// ���Ա仯 �������ָ���С
				jsp.setOneTouchExpandable(true);
				//���ò��ֹ�����, ��ΪJFRAME�������BORDER ���֡����ԾͲ�������
				
				//������
				this.add(jsp);
				
				this.setSize(500,500);
				this.setLocation(200, 200);
				
				this.setTitle("register new user");
				this.setResizable(false);    // ��֧�����
				//�˳������˳�jvm
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // �˳�ʱ��ر�JVM
				
				//��ʾ
				this.setVisible(true);
	}

}
