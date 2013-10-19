import java.util.EnumSet;
//allow to use built in method   range()    
public class EnumCall {
	public static void main(String[] args) {
		for ( EnumEx   people:  EnumEx.values()){
			System.out.printf("%s\t%s\t%s\n", people,people.getDesc(),people.getYear());
		}
			
			System.out.println("\n and now for the rang of constants\n");
			for (  EnumEx     people1:EnumSet.range( EnumEx.kelsey, EnumEx.candy))
			{
				System.out.printf("%s\t%s\t%s\n", people1,people1.getDesc(),people1.getYear());				
			}	
	}
}
