// Adapted from the UW
// An interface that contains only one method to allow one critter
// to know who its neighbors are (see getMove in the Critter class)
//
public interface CritterInfo {
    // Takes a direction as a parameter (one of the constants NORTH,
    // SOUTH, EAST, WEST or CENTER).  It
    // returns the display character for the critter that is one unit away
    // in that direction (or "." if the square is empty).  
    // If multiple critters occupy this space, returns a randomly chosen
    // one of them.
    public String getNeighbor(int direction);
}
