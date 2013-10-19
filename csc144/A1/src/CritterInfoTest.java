
public class CritterInfoTest implements CritterInfo,CritterConstants{

	String [] neighbors;
	/*public CritterInfoTest ( String [] neighbors){
		this.neighbors=neighbors;
	}
*/
	public String getNeighbor(int direction) {
		//north  ->  bear
		//south -> Tiger
		//East  -> Lion
		//West  -> Wolf
	/*	if (direction==NORTH){
			return "B";
		}else if ( direction==SOUTH){
			return "T";
		}else if ( direction==WEST){
			return "W";
		}else if ( direction==EAST){
			return "L";
		}else{
			return "S";
		}*/
		return Integer.toString(direction);
	}

}
