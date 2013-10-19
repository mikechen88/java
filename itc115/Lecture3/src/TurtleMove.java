
import acm.program.GraphicsProgram;
import acm.graphics.*;

public class TurtleMove extends GraphicsProgram {

	
	public void run(){
	
		GTurtle bob=new GTurtle(151,151);
		add(bob);
		
		//wait for mouse click
		waitForClick();
		
		/*for (int i=0;i<100;i++){
			bob.forward(i);
		}
		*/
		
	
		//move bob forever
		while(true){
			//if bob hits the right edge
			if( bob.getX()>=getWidth()){
				
				bob.setDirection(180);
				
			}	//if bob hits the left edge
			else if(  bob.getX()<=0){
				bob.setDirection(0);
			}
			bob.forward(52);
		}
	}

}
