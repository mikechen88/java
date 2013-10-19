import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	public MyPanel() {
		setBackground(Color.YELLOW);
	}

	protected void paintComponent(Graphics g) {
		//always call super.paintComent on the first line
		super.paintComponent(g);
		//Graphics2D has more functionalities than Graphics
		Graphics2D g2D=(Graphics2D)g;
		//paint a polygon
		
		
		//crewate 15 rando  points in the panel
		int [] xp =new int [15];
		int[] yp=new int [15];
		for ( int i=0;i<xp.length;i++){
			xp[i]=(int)(Math.random()*getWidth());
			yp[i]=(int )(Math.random()*getHeight());
			
		}
		
		//select a random color
		int alpha=(int)( Math.random()*256);//transparency
		int red=(int )(Math.random()*256);
		int blue=(int )(Math.random()*256);
		int green=(int )(Math.random()*256);
		Color color=new Color (alpha, red, green,blue);
		g2D.setColor(color);
		
		
		g2D.fillPolygon(xp, yp, xp.length);
	}
}
