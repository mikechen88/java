import java.awt.*;
import javax.swing.JFrame;

public class Screen {
	
	private GraphicsDevice vc;
	//an interface to video card, control or monitor video card
	
	public Screen(){
		
		GraphicsEnvironment env=GraphicsEnvironment.getLocalGraphicsEnvironment();
		//  env    will be all your graphic object not only screen.
		
		vc=env.getDefaultScreenDevice();
		//access to screen
	}
	
	public void setFullScreen(DisplayMode dm,JFrame window ){
		// displayMode is monitor setting: resolution  refresh rate...
		window.setUndecorated(true);
		//get rid of everything :  title bar ,scroll bar......
		
		window.setResizable(false);
		//can't resize the screen
		
		vc.setFullScreenWindow(window);
		//built in method
		// take the window we just set, and put it into the screen, no matter what the size is
		
		if (dm!=null &&  vc.isDisplayChangeSupported()){
			//dm!=null      means as long as you have setting
			//isDisplayChangeSupported    means   video card is changeable 
			
			try {
				vc.setDisplayMode(dm);
				//default method
			}catch(Exception ex){
				
			}
			
		}		
	}
	
	public Window getFullScreenWindow(){
		return vc.getFullScreenWindow();
	}
	
	public void restoreScreen(){
		//change the screen back to normal
		Window  w = vc.getFullScreenWindow();
		
		if (w!=null){
			// means windows contain something
			
			w.dispose();
			//free the resource.
			
			vc.setFullScreenWindow(null);
			//no more full screen
		}
	}
	
	
	
	
	
	
	
}
