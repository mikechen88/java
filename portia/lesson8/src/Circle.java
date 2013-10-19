
public class Circle extends Shape


{
		private int radius;
		
		public Circle(int radius,String color)
		{
			super(color);
		this.radius=radius;		
		
		}
			public double getArea()
			{
				return Math.PI*Math.pow(radius, 2);
			
			}

}
