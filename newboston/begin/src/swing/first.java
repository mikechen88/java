package swing;

import javax.swing.JOptionPane;

public class first {
//showInputDialog     only accept string
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fn=JOptionPane.showInputDialog("enter first number");
		String sn=JOptionPane.showInputDialog("enter second number");

		int num1=Integer.parseInt(fn);
		int  num2=Integer.parseInt(sn);
		int sum=num1+num2;
		
		JOptionPane.showMessageDialog(null, "the answer is " +sum,"the title",JOptionPane.PLAIN_MESSAGE);
		//null    is the position
	}
}
