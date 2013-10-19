public class Ant extends Organism
{
	public Ant(World world, int x, int y)
	{
		super(world, x, y);
		antTracker=3;
	}
	
	//returns a string representation of the ant
	public String toString()
	{
		return "ant";
	}
	
	public void makeChild(int x, int y)
	{
		world.setAt(x, y,new Ant( world,  x,  y)) ;
		
		
	}
}
