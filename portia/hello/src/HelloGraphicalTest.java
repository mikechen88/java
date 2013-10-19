import java.awt.Color;
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.*;
import acm.io.IODialog;

public class HelloGraphicalTest extends GraphicsProgram
{
	
	
	public static final int APP_WIDTH=500;//STAtic don't need initial,  final means can't change
	public static final int APP_HEIGHT=150;
	/*public static final int BOX_WIDTH=300;
	public static final int BOX_HEIGHT=75;	*/
	public void init()
			{
			     this.setSize(APP_WIDTH,APP_HEIGHT);
			     IODialog dialog = new IODialog();
			     int n = dialog.readInt("Enter an x: ");

			     int m = dialog.readInt("Enter an y: ");

				
				//GRect box=new GRect(25,50,300,75);
				GRect box=new GRect(m,n);
				double x=this.getWidth()/2-box.getWidth()/2;
				double y=this.getHeight()/2-box.getHeight()/2;
				box.setLocation(x,y);
				
				box.setFilled(true);				
				box.setColor(Color.GREEN);
				add(box);
				
				
				/* GLabel helloLabel ;
				double x1=this.getWidth()/2-helloLabel.getWidth()/2;
				double y1=this.getHeight()/2-helloLabel.getHeight()/2;*/
	
				GLabel helloLabel= new GLabel("hello world");
				helloLabel.setColor(Color.BLUE);
				helloLabel.setFont(new Font("Serif", Font.BOLD, 40));
				
				
				double helloX=x+box.getWidth()/2-helloLabel.getWidth()/2;
				double helloY=y+box.getHeight()/2+helloLabel.getHeight()/2;
				helloLabel.setLocation(helloX,helloY);
				
				//GRectangle bounds = helloLabel.getBounds(); 
				
				
							add(helloLabel);	
				System.out.println("coordinates:x"+x+"y:"+y);
				//for debug
							
							//dialog.println("hello portia");
			}
}
