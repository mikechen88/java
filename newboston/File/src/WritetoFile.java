import java.io.*;
import java.lang.*;
import java.util.*;

public class WritetoFile {
	private Formatter x;
	
	public void openFile(){
		try{
			x=new  Formatter("chinese.txt");
		}catch(Exception e){
			System.out.println("you have an error");
		}
	}
	public void addRecords(){
		x.format("%s%s%s","20 ","  buckey","  roberts ");
	}
	public void closeFile(){
		x.close();
	}	
}
