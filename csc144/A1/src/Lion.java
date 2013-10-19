// From the UW
//
// Stone objects are displayed as S and always stay put.
// They always pick ROCK in the Rock-Paper-Scissors game.
//
import java.awt.Color;
import java.util.Random;

public class Lion extends Critter {
	Random rand = new Random();
	
	int steps;
	
	int direction;
	public Lion( int steps){
		this.steps=steps;
	}
    public int fight(String opponent) {
        return PAPER; // good ol' rock... nothing beats that!
    }
    
    public Color getColor() {
        return Color.YELLOW; // gray
    }
    
    public int getMove(CritterInfo info) {
    	int n=rand.nextInt(4);
    	direction=NORTH;
    	switch(n){
    	case 0:
    		direction= NORTH;
    		this.getLocation().y-=steps;
    		break;
    	
    	case 1:
    		direction= EAST;
    		this.getLocation().x+=steps;
    		break;
    	case 2:
    		direction= SOUTH;
    		this.getLocation().y+=steps;
    		break;
    	case 3:
    		direction= WEST;
    		this.getLocation().x-=steps;
    		break;
    		
    	}
    	return direction;
  
    }
    
    public String toString() {
        return "L"; // displays stone as S
    }
}




