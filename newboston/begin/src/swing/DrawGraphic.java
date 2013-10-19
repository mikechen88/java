package swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawGraphic extends JPanel {
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		this.setBackground(Color.white);
		
		g.setColor(Color.blue);
		g.fillRect(25, 25, 100, 30);
		
		g.setColor(new Color(190,81,215));
		//customed color
		g.drawRect(25, 65, 100, 30);
		
		g.setColor(Color.red);
		g.drawString ("this is some text",25,120);
		
		g.setColor(Color .blue);
		g.drawLine(10,25,200,25);
		
		g.setColor(Color .red);
		g.fillOval(10,120,100,30);
		
		g.setColor(Color.orange);
		g.fill3DRect(150, 160, 100, 50, true);
		
		
		
				
	}
}
