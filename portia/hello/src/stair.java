import java.awt.Color;
import acm.program.GraphicsProgram;
import acm.graphics.*;
import acm.io.IODialog;
public class stair extends GraphicsProgram
{

	public GRect stair;
	public double x=0;
	public double y=0;
	public Color color1,color2;
	public static final int APP_WIDTH=700;
	public static final int APP_HEIGHT=600;
	public static final int STAIR_WIDTH=70;
	public static final int STAIR_HEIGHT=30;
			public void init()
			{
				this .setSize(APP_WIDTH,APP_HEIGHT);
				
				IODialog dialog =new IODialog();
				int number=dialog.readInt("please enter number of stairs:");
				for (int i=0; i<number;i++)
				{
					GRect stair=new GRect(STAIR_WIDTH,STAIR_HEIGHT);
					x=x+STAIR_WIDTH/2;
					y=y+STAIR_HEIGHT;
					stair.setLocation(x,y);
					add(stair);
							stair.setFilled(true);
							if (i<number/2)
							{
							stair.setFillColor(Color.BLUE);
											
							}
							else
							{
								stair.setFillColor(Color.red);
							}
					
					
				}
				
				
				
				
			}
	
	
}
