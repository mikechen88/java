import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	
	private ArrayList scenes;
	//store for pictures
	
	private int sceneIndex;
	
	private long movieTime;
	//the time of animation
	
	private long totalTime;
	//check the total amount time
	
	//constructor
	public Animation(){
		scenes=new ArrayList();
		totalTime=0;
		//set everything to zero
		
		start();
	}
	
	//add scene to ArrayList and set time for eache scene
	public synchronized void addScene( Image i, long t){
		//synchronized make the method in the order line. one by one 
		//thread  can run at the same time.
		
		totalTime +=t;
		scenes.add(new   OneScene(  i,   totalTime));
		//each scene is the object 	
	}
	
	//start animation from begin
	public synchronized void start(){
		movieTime=0;
		sceneIndex=0;
	}
	
	//change scenes
	public synchronized void update(long timePassed){
		// timePassed      is how long it will take from this update to next update
		if (scenes.size()>1){
			// check arraylist   have more picture
			
			movieTime +=timePassed;
			if ( movieTime >=totalTime){
				//if   then reset everything 
				movieTime=0;
				sceneIndex=0;
			}
			while( movieTime> getScene(sceneIndex).endTime){
				sceneIndex++;
			}
		}
	}
	
	//get aniamation current scene(  aka  image)
	public synchronized Image    getImage(){
		if (scenes.size()==0){
			return null;
		}else{
			return getScene (sceneIndex).pic;
			//get current picture   ,and return the picture
		}
	}
	
	//get scene
	private OneScene getScene(int x){
		return (OneScene)scenes.get(x);		
	}
	
	/////////////////////private inner class//////////////
	private class OneScene {
		Image pic;
		long endTime;
		
		public OneScene( Image pic, long endTime){
			this.pic=pic;
			this.endTime=endTime;
		}
	}
	
	
	
	
}
