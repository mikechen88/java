/*
 * JComboxBox,   JList,    JScrollPane
 */
import java.awt.*;
import javax.swing.*;
public class MoreCompont extends JFrame{

	JPanel  jp1,jp2;
	JLabel jl1,jl2;
	
	JComboBox jcb1;
	JList jlist;
	JScrollPane jsp;
	
	public static void main(String[] args) {
		MoreCompont  mc=new MoreCompont();
	}
	// constructor
	public MoreCompont()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		
		jl1=new JLabel("city");
		jl2=new JLabel("address");
		
		//set jcombox 
		String [] jg={"seattle","portland","new york","los ang"};
		jcb1=new JComboBox(jg);
		
		
		String [] zone={"aa","bb","cc","dd","ee"	};
		jlist=new JList(zone);
		
		//if we don't set anything ,default will show all the options,
		//so for don't look so ugly.we need to set jscrollpane
		//再设置你希望显示多少个选项
		jlist.setVisibleRowCount(2);//控制显示2 个
		jsp=new JScrollPane(jlist);//这时给panel 添加的时候就不是jlistb ,而是jsp
		
		
		
		
		//set layout
		this.setLayout(new GridLayout(3,1));
		
		//add component
		jp1.add(jl1);
		jp1.add(jcb1);
		
		jp2.add(jl2);
		jp2.add(jsp);
		
		this.add(jp1);
		this.add(jp2);
		
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
	}
	
	
	

}
