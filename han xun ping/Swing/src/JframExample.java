import javax.swing.*;
import java.awt.*;

public class JframExample extends JFrame {
	
	//把需要的swing 组件 定义在外头，在方法体内创建组件，添加组件，
	JButton jb1=null;
	public static void main(String[] args){
		JframExample jframExample=new JframExample();

	
	}
	//构造函数，把需要的组件，定义到构造函数更正规
	public JframExample()
	{
		//设置完     extends  JFrame 后，就不用下面这句话了,然后把后面设置了jf的全改为this
		//JFrame jf=new JFrame();	
		
		
		//设置一个button,默认为占据整个窗口
		jb1=new JButton("i'm button");
		
		//给窗体设置标题
		this.setTitle("hello,world");
		//设置大小， 按像素【】是个密度单位
		this.setSize(200,200);
		//默认初始位置是在左上角，想要改变初始位置就
		this.setLocation(100,200);
		
		//添加JBUTTON组件
		this.add(jb1);
		
		//设置当关闭窗口时，保证jvm 也退出，否则多开几次，会占用很多内存。
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示，如何不设置显示，默认是不显示的
		this.setVisible(true);
		
		
		
		
	}
}
