
public class aa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
aa bb= new aa();
bb.is(5);
	}

	public boolean is ( int x){
		
		while ( x/2>0 ){
			x=x/2;
			if ( x%2!=0)return false;
		}
		return true;
		
	}
}
