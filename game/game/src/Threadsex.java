import java.util.Random;

public class Threadsex implements Runnable {
	
	String name;
	int time;
	Random r=new Random();
	
	public Threadsex(String s){
		name=s;
		time=r.nextInt(999);
		
	}
	public void run(){
		try{
			System.out.printf("%s is sleeping for %d\n",name,time);
			Thread.sleep(time);
			System.out.printf("%s  is done\n", name);
			
			
		}catch(Exception e){
			
		}
	}
}
