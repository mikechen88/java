import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Tank {
	int x,y;
	public Tank(int x, int y) {
		
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.RED);

		g.fillOval(x, y, 30, 30);
		g.setColor(c);
	}
	
	public void KeyPressed(KeyEvent e){
		int key=e.getKeyCode();
		if (key==KeyEvent.VK_RIGHT);
			switch(key){
			case KeyEvent.VK_LEFT: 
				x-=5;
			case KeyEvent.VK_RIGHT:
				x-=5;
			case KeyEvent.VK_UP:
				y-=5;
			case KeyEvent.VK_DOWN:
				y+=5;
			}
	}
	
	
	
}
