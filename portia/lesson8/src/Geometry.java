import java.text.DecimalFormat;

import acm.program.ConsoleProgram;
public class Geometry extends ConsoleProgram

{
    public void run()
    {
    	Shape[] shapes=new Shape[3];
    	shapes[0]=new Rectangle(1100,1500,"Brown");
    	shapes[1]=new Circle(500,"Blue");
    	shapes[2]=new Square(200,"White");
    	
    	//loop through and add of the areas
    	double totalSquareFeet=0;
    	for(Shape shape:shapes)
    	{
    		totalSquareFeet +=shape.getArea();    		
    	}
    	println("Total Square feet:"+totalSquareFeet);
    	
    	
    /*	Circle circle=new Circle(6,"Red");
    	
    	
    	DecimalFormat df=new DecimalFormat("0.00");
    	println("Area:"+df.format(circle.getArea()));
    	Cylinder cylinder=new Cylinder(5,7,"Blue");
    	println("volume:"+df.format(cylinder.getVolume()));
    	
    	Square square=new Square(5,"Green");
    	println("Square Area:"+square.getArea());*/
    }
    
}
