public class DoodleBug extends Organism
{
	
	public DoodleBug(World world, int x, int y)
	{
		super(world, x, y);
		antTracker=8;
	}
	
	//string representation of doodlebug
	public String toString()
	{
		return "doodlebug";
	}
	public void simulate()
	{
		//don't simulate twice in a round
		if(simulated) return;
		simulated = true;
		
		//now move, breed, ....
		
		
		move();
		
		breed();
		eat();
		
		}
	
	public void makeChild(int x, int y)
	{
		world.setAt(x, y,new DoodleBug( world,  x,  y)) ;

		
	}
	
	public void eat()
	{
		if  Ant( world,  x,  y) ;
		
		
		return ;
		
		
		
	}
}
