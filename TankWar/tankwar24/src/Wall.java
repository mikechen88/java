import java.awt.*;

public class Wall {
	public void draw(Graphics g){
		g.fillRect(x, y, w, h);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
	public Wall(int x, int y, int w, int h, TankClient tc) {
	
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tc = tc;
	}
	int x,y,w,h;
	TankClient tc;
	
	
	
}
