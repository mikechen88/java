
public class Cylinder extends Circle
{
private int height;
			public Cylinder(int radius,int height,String color)
			{
				super(radius,color);//must to be first!
			this.height=height;
			
			}
			
			public double getVolume()
			{
				return getArea()*height;
				
				
			}
}
