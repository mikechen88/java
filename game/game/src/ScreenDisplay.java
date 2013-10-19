import java.awt.*;
import javax.swing.JFrame;

public class ScreenDisplay extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DisplayMode dm=new DisplayMode(800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN );
		// it take 4 parameter, 
		//first two is resolution   x  ,  y
		//third is color     8, 16  or 32
		//refresh rate:   how many time the monitor refresh; REFRESH_RATE_UNKNOWN   means we don't know yet
		
		ScreenDisplay b= new ScreenDisplay();
		b.run(dm);
	}

	public void run(DisplayMode dm){
		setBackground(Color.PINK);
		setForeground(Color.WHITE);
		//set up back foreground
		
		setFont( new Font ("Arial",Font.PLAIN,24));
		
		Screen s =new Screen();
		try{
			s.setFullScreen(dm, this);
			//this is the reference to the object you working on,   here means  Screen
			
			try{
				Thread.sleep(5000);
				//so we can see screen change, otherwise we can't see what change,it will change very fast
				
			}catch(Exception ex){
				
			}
			
		}finally{
			s.restoreScreen();
		}
		
	}
	
	public void paint(Graphics g){
		//whenever we call JFrame , it automatically call paint() method. because it know what it need to paint
		
		
		if (g instanceof Graphics2D){//this is for old version screen ,ANTIALIAS the word
		//   if  g  is graphics 2d  object, store it into    g2
			Graphics2D g2= (Graphics2D )g;
			//type cast    g
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			// whatever it has text,make it smooth
			//RenderingHints.KEY_TEXT_ANTIALIASING     is the key
			//RenderingHints.VALUE_TEXT_ANTIALIAS_ON       is the value
			
			
		}
		g.drawString("this is gona be awesome", 200, 200);
	}
	
}
