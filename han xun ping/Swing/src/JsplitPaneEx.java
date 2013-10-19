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
		
		// 拆分窗格,可以把窗口水平拆分或垂直拆分
				jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jlist, jl1);
				// 可以变化 ，伸缩分割窗体大小
				jsp.setOneTouchExpandable(true);
				//设置布局管理器, 因为JFRAME本身就是BORDER 布局。所以就不设置了
				
				//添加组件
				this.add(jsp);
				
				this.setSize(500,500);
				this.setLocation(200, 200);
				
				this.setTitle("register new user");
				this.setResizable(false);    // 不支持最大化
				//退出窗口退出jvm
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 退出时候关闭JVM
				
				//显示
				this.setVisible(true);
	}

}
