import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Images extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DisplayMode dm=new DisplayMode(800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN );
		// it take 4 parameter, 
		//first two is resolution   x  ,  y
		//third is color    16  or 32
		//refresh rate:   how many time the monitor refresh; REFRESH_RATE_UNKNOWN   means we don't know yet
		
		Images i= new Images();
		i.run(dm);
	}
	
	private   Screen s;
	private   Image bg,   pic;
	
	private boolean loaded;
	
	
	//run method start 
	public void run(DisplayMode dm){
		setBackground(Color.pink);
		setForeground(Color.white);
		//set up back foreground
		
		setFont( new Font ("Arial",Font.PLAIN,24));
		loaded=false;
		
		s =new Screen();
		try{
			s.setFullScreen(dm, this);
			//this is the reference to the object you working on,   here means  Screen
			
			
			loadpics();
			
			
			try{
				Thread.sleep(5000);
				//so we can see screen change, otherwise we can't see what change
				
			}catch(Exception ex){
				
			}
			
		}finally{
			s.restoreScreen();
		}
		
	}//run method end
	
	
	
	//loads pictures
	public void loadpics(){
		bg=new ImageIcon("back.jpg").getImage();
		// location of the image
		//getImage()    is built in method
		
		pic=new ImageIcon("face.jpg").getImage();
		
		loaded=true;
		
		repaint();
		//built in method.  it will call paint()  method  again
		
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
		if (loaded){
			g.drawImage(bg,0,0,null);
			g.drawImage(pic, 170,180,null);
		}
	}
	
}
