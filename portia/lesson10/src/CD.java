import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;
import java.applet.AudioClip;

public class CD extends GCompound
{
		private String cdName;
		private String artist;
		private int year;
		private GOval cd;
		private AudioClip song=null;
		
		public CD(String cdName,String artist, int year, Color color,int x,int y,int radius)
		{
			this.cdName=cdName;
			this.artist=artist;
			this.year=year;
			
			this.setLocation(x,y);
			//create outside circle
			 cd=new GOval(0,0,radius*2,radius*2);
			add(cd);
			cd.setFilled(true);
			cd.setFillColor(color);
			
			int holeRadius=radius/4;
			//create the inside circle
			GOval hole=new GOval(radius-holeRadius,radius-holeRadius,holeRadius*2,holeRadius*2);
	
			hole.setFilled(true);
			hole.setFillColor(Color.white );
			add (hole);
			int cdNameHeight=(int)cd.getHeight()+30;
			//show the cd name
			GLabel cdNameLabel=new GLabel(cdName,0,cdNameHeight);
			add(cdNameLabel);
			
			//show the artists name
			int cdArtistHeight=cdNameHeight+(int)cdNameLabel.getHeight();
			GLabel cdArtistLabel=new GLabel(artist,0,cdArtistHeight);
			add(cdArtistLabel);
			
			int cdYearHeight=cdArtistHeight+(int)cdArtistLabel.getHeight();
			GLabel cdYearLabel=new GLabel(year+"",0,cdYearHeight);
			add(cdYearLabel);
			
			
		
		

			
		}
		public String getCDName()
		{
			return cdName;
			
			
		}
		public void setColor(Color color)
		{
		cd.setFillColor(color);
		}
		public void setSong(AudioClip song)
		{
			this.song=song;
			
		}
		public void playSong()
		{
			if(song==null) return;
			
			song.play();
		}
		
		
		
		
		
		
		
}
