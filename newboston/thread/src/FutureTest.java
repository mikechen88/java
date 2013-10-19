import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class FutureTest {
	public static void main ( String[]  args){
		Scanner  in= new Scanner ( System.in);
		System.out.print("enter base directory");
		String directory=in.nextLine();
		System.out.print("enter a keyword" );
		String keyword=in.nextLine();
		
		MatchCounter counter=new MatchCounter( new File(directory),keyword);
		FutureTask<Integer> task= new FutureTask<Integer>(counter);
		Thread t = new Thread( task);
		t.start();
		try{
			System.out.println(task.get()+" matching files.");
			}catch(ExecutionException e){
				e.printStackTrace();
			}catch ( InterruptedException e){}
		}
}

// this task counts the files in a directory and its subdirectories that contain a given keyword
class MatchCounter implements Callable <Integer>{
	private File directory;
	private String keyword;
	private int count ;
	
	public MatchCounter ( File directory, String keyword){
		this.directory=directory;
		this.keyword=keyword;
	}
	
	public Integer call(){
		count=0;
		try{
			File[] files= directory.listFiles();
			ArrayList<Future<Integer>> results= new ArrayList<Future<Integer>>();
			
			for (File file:files)
				if (file.isDirectory()){
					MatchCounter counter =new MatchCounter(file,keyword);
					FutureTask<Integer> task= new FutureTask<Integer>(counter);
					results.add(task);
					Thread t= new Thread(task);
					t.start();
				}else{
					if (search(file))count++;
				}
			
			for (Future<Integer> result:results)
			try{
				count+=result.get();
			}catch( ExecutionException e){
				e.printStackTrace();
			}
		}catch(InterruptedException e ){}
		return count;
	}
	
	//searchs a file for a given keyword
	public boolean search ( File file){
		try
		{
			Scanner in = new Scanner ( new FileInputStream(file));
			boolean found=false;
			while (!found && in.hasNextLine()){
				String line = in.nextLine();
				if(line.contains(keyword))found=true;
			}
			in.close();
			return found;
		}catch(IOException e){
			return false;
		}
	}
}
