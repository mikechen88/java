import java.awt.Color;
import java.awt.Graphics;




public class Plus extends AbstractShape implements Cloneable {

	private int x;
	private int y;
	private int height;
	private int width;
	private Color plusColor=Color.BLUE;
	
	
	

	public Plus(int x, int y, int height, int level){
		super(x, y, height, level);
		 
	}
	
	
	// draws plus +
	public void realPaint(int x,int y,int width,int height,Color color) {		
		int x1 =  x + (width/2);
		int x2 = x - (width/2); 
		int y1 = y + (height/2);
	    int y2 = y - (height/2);
	    g.setColor(plusColor);
		g.drawLine(x2, y, x1, y);
	    g.drawLine(x, y1, x, y2);	    
	}
	

}

	
	

