import acm.graphics.*;
import java.awt.Color;
public class GBasketBall extends GCompound
{
	
	private GOval outterCircle;
public GBasketBall (int x,int y, int radius,Color color)
{
	this.setLocation(x,y);
	
	
/*	outterCircle=new GOval(0,0,radius*2,radius*2);
		
		add(outterCircle);*/
		
		outterCircle= new GOval(x, y, radius*2, radius*2);
		outterCircle.setFillColor(color);
		outterCircle.setFilled(true);
		add (outterCircle);
		
		GLabel spalding=new GLabel("spalding");
		int spaldingX=(int)outterCircle.getWidth()/2-(int)spalding.getWidth()/2;
		int spaldingY=(int)outterCircle.getHeight()/2+(int)spalding.getHeight()/2;
		spalding.setLocation(spaldingX,spaldingY);
		add(spalding);
		 }
public void setColor(Color color)
{
	
outterCircle.setFillColor(color);


}



}
