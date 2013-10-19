import java.awt.Color;
import java.awt.Graphics;


public abstract class AbstractShape implements Shape {
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected Color color;
	
	// constructor
	public AbstractShape(int x, int y, int height, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
		this.height = height;
		this.width = height;	
	}
	// returns width
	public int getWidth(){
		return width;
	}
	
	public void setHeight(int height){
		this.height=height;
		//return this.height;
	}
	
	// gets color
	public void setColor(Color color){
		this.color = color;
	}
	
	// gets color
	public Color getColor(){
		return color;
	}
	
	// gets height
	public int getHeight(){
		return height;
	}

	// sets location of x
	public void setX(int x){
		this.x = x;
			
	}
	// returns X
	public int getX(){
		return x;
	}
	
	// sets location of Y
	public void setY(int y){
		this.y = y;
			
	}
	// returns Y
	public int getY(){
		return y;
	}
	public void paintMe(Graphics g){};
}