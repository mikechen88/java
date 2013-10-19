import java.awt.Color;

import acm.graphics.*;
import acm.io.IODialog;
import acm.program.DialogProgram;
import acm.program.GraphicsProgram;

public class SquareWidth extends GraphicsProgram{
	
	//defining constants.final means cant'change
	
	private static final int APP_WIDTH=200;
	private static final int APP_HEIGHT=200;

	public void run(){
		
		setSize(APP_WIDTH,APP_HEIGHT);
		IODialog dialog=new IODialog();
		int width=dialog.readInt();
		int height=dialog.readInt();
		
		//draw a rectangle with the specified width and height
		GRect     grect=new  GRect((APP_WIDTH-width)/2,(APP_HEIGHT-height)/2,width,height);
		grect.setColor(Color.red);
		grect.setFilled(true);
		add(grect);
	}
	
}
