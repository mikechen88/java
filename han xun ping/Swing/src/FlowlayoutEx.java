/*
 * 流式布局
 */
import java.awt.*;
import javax.swing.*;
public class FlowlayoutEx  extends JFrame {

	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FlowlayoutEx fl=new FlowlayoutEx();
		
	}
	public FlowlayoutEx()
	{
		
		jb1=new JButton("aa");
		jb2=new JButton("bb");
		jb3=new JButton("cc");
		jb4=new JButton("dd");
		jb5=new JButton("ee");
		jb6=new JButton("ff");
		
		
		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);
		this.add(jb5);
		this.add(jb6);
		
		//设置布局管理器， 否则默认为borderlayout 
		this.setLayout(new FlowLayout());
		//this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		
		
		//设置窗体属性
				this .setTitle("flowlayout example");
				this.setSize(300,200);
				this.setLocation(200,200);
				
				//禁止改变窗体的大小
				this.setResizable(false);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//显示窗体
				
				this.setVisible(true);
		
	}
}
