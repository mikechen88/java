
public class aa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
aa bb= new aa();
boolean b=bb.is(16);

System.out.print(b);
	}

	public boolean is ( int x){
		
		while ( x>0 ){
			if ( x>1&&x%2!=0)return false;
			x=x/2;
			
		}
		return true;
		
	}
}
