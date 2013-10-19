import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import acm.graphics.GObject;
import acm.io.*;
public class CDCollection extends GraphicsProgram{
	
	
	private RandomGenerator rgen=new RandomGenerator();
	public void run()
	{
		
		setSize(350,350);
		//add a cd to the cd collection
		CD deathHill=new CD("Death Hill","KISS",1977,Color.RED,0,0,25);
		
		add(deathHill);
		
		CD rubberSole=new CD("Rubber Sole","Beatles",1965,Color.BLUE,100,0,25);
		
		add(rubberSole);
		
			CD greatesHits=new CD("Greatest Hits","Whitney houston",2012,Color.GREEN,200,0,25);
		
			add(greatesHits);
			
			addMouseListeners();
			
			//add audio clips
			AudioClip deathSong=getAudioClip(getDocumentBase(),"music/aa.wav");
			deathHill.setSong(deathSong);
			
			AudioClip rubberSong=getAudioClip(getDocumentBase(),"music/bb.wav");
			rubberSole.setSong(rubberSong);
			AudioClip greatesSong=getAudioClip(getDocumentBase(),"music/cc.wav");
			greatesHits.setSong(greatesSong);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		//get the object they clicked
		GObject maybeCD=getElementAt(e.getX(),e.getY());
				if (maybeCD==null) return;
		//they clicked whitespace
				
				CD cd=(CD)maybeCD;//cast as a CD so that you can use the cd methods
				
		/*IODialog dialog =new IODialog();
		dialog.println(cd.getCDName());*/
		cd.setColor(rgen.nextColor() );
		
		cd.playSong();
		
	}
	
	
	

}
