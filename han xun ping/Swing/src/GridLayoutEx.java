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
		
		//set gridlayout    第一个3 表示行，第二个3 表示列
		//this.setLayout(new GridLayout(3,3));
		//设置间隙，免得太靠近了,这样会导致按钮变小，窗体不变
		this.setLayout(new GridLayout(3,3,10,10));
		
		
			//add component
		for (int i=0;i<size; i++)
		{
			this.add(jbs[i]);
		}
		
		
		//设置窗体属性
		this .setTitle("gridlayout example");
		this.setSize(300,200);
		this.setLocation(200,200);
		
		//禁止改变窗体的大小
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//显示窗体
		
		this.setVisible(true);
		
		
	}

	
	
}
