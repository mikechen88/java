// From the UW
//
// Stone objects are displayed as S and always stay put.
// They always pick ROCK in the Rock-Paper-Scissors game.
//
import java.awt.Color;

public class Stone extends Critter {
    public int fight(String opponent) {
        return ROCK; // good ol' rock... nothing beats that!
    }
    
    public Color getColor() {
        return Color.GRAY; // gray
    }
    
    public int getMove(CritterInfo info) {
        return CENTER; // does not move
    }
    
    public String toString() {
        return "S"; // displays stone as S
    }
}




