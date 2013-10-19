import java.io.File;
import java.util.*;

public class File1 {
	public static void main(String[] args) {
		
		File x=new File("C:\\test\\greg.txt");
		
		if (x.exists())
			System.out.println(x.getName()+"  exists!");
		else
			System.out.println("the file doesn't exists");
		
		final Formatter   y;
		//Formatter output a string to a file
		
		try {
			y=new Formatter("fred.txt");
			//it can create the file in this java program folder
			//if it couldn't find the file ,it will create the file
			
			
			System.out.println("you created a file");
		}catch(Exception e){
			System.out.println("you got an error");
		}
		
		
		
		
		
		
		
	}

}
