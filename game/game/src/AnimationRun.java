import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class AnimationRun {

	public static void main(String[] args) {
		DisplayMode displayMode= new DisplayMode (800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN);
		AnimationRun    b=new AnimationRun();
		b.run (displayMode);
	}

	private Screen screen;
	private Image bg;
	private Animation a;
	
	//loads picture from computer to java and add scene
	public void lodaPics(){
		bg=new ImageIcon("bg.jpg").getImage();
		
		
		Image face1=new ImageIcon("laugh.jpg").getImage();
		Image face2=new ImageIcon("sad.jpg").getImage();
		
		a=new Animation();
		a.addScene(face1, 250);
		a.addScene(face2, 250);
	}
	
	//main engine to run
	public void run (DisplayMode dm){
		screen =new Screen();
		try{
			screen.setFullScreen(dm, new JFrame());
			lodaPics();
			movieLoop();
		}finally{
			screen.restoreScreen();
			//built function
		}
	}
	
	//main movie loop
	public void movieLoop(){
		long startingTime=System.currentTimeMillis();
		//get now time     from computer
		
		long cumTime=startingTime;
		
		while (cumTime  -   startingTime<5000){
			long timePassed=System.currentTimeMillis()-cumTime;
			
			cumTime +=timePassed;
			a.update(timePassed);
			
			Graphics  g=screen.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();
			
			try{
				Thread.sleep(20);
			}catch (Exception ex){
				
			}
			
		}
	}
	
	//draw method
	public void draw( Graphics g){
		g.drawImage(bg, 0, 0, null);
		g.drawImage(a.getImage(),0,0,null);
	}
}
