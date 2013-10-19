// From the UW
//
// Stone objects are displayed as S and always stay put.
// They always pick ROCK in the Rock-Paper-Scissors game.
//
import java.awt.Color;

public class Bear extends Critter {
	boolean north=true;
	
    public int fight(String opponent) {
        return SCISSORS; // good ol' rock... nothing beats that!
    }
    
    public Color getColor() {
        return new Color(128,128,64); // gray
    }
    
    public int getMove(CritterInfo info) {
    	if (north){
    		north=false;
    		return NORTH;
    	}else{
    		north=true;
    		return WEST;
    	}
       
    }
    
    public String toString() {
        return "B"; // displays stone as S
    }
}




