import acm.util.*;
public abstract class Organism
{
	protected World world;
	protected int x;
	protected int y;
	protected boolean simulated;
	protected int antTracker;
	protected int times;
	RandomGenerator rg = new RandomGenerator();
	
	public Organism(World world, int x, int y)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		simulated = true;
	}
	
	//returns the string representation of the organism
	public abstract String toString();
	
	public void simulate()
	{
		//don't simulate twice in a round
		if(simulated) return;
		simulated = true;
		
		//now move, breed, ....
		move();
		breed();
		
	}

	public void move()
	{//make sure the point to the right is in the grid
		int future = rg.nextInt(0,3);
		int fx=x;
		int fy=y;
		if (future==1){fx++;}
		else if (future==2){fx--;}
		else if (future==3){fy++;}
		else {fy--;};
		
		
		
		if (!world.pointInGrid(fx, fy))return;
		//make sure there is no one at the spot to 
		if (world.getAt(fx, fy)!=null)return;
		//clone my self to the new spot
		world.setAt(fx,fy, this);
		world.setAt(x, y, null);
		x=fx;
		y=fy;
		times++;		
	}
	public void breed()
	{
		if (antTracker==times){
		times=0;
			if (world.getAt(x+1, y)==null && world.pointInGrid(x+1,y)){
			makeChild(x+1,y);}
			else if (world.getAt(x-1, y)==null && world.pointInGrid(x-1,y)){
				makeChild(x-1,y);}
			else if (world.getAt(x, y-1)==null && world.pointInGrid(x,y-1)){
				makeChild(x,y-1);}
			else if (world.getAt(x, y+1)==null && world.pointInGrid(x,y+1)){
				makeChild(x,y+1);}
			else {
				return;
			}
			
			
		}
			
			
			
		}
		
		public abstract void makeChild(int x, int y);
		
		
	
	
	
	//indicate that the organism hasn't simulated this round
	public void resetSimulation()
	{
		simulated = false;
	}
}
