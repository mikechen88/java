import acm.program.GraphicsProgram;


class Bullet extends GraphicsProgram implements Runnable {
	public double x, y;
	public boolean live;
	public int b_speed = 1;
	public double locat;

	public Bullet(double dx, double dy) {
		x = dx;
		y = dy;
		live = true;
		locat=dy;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// other bullet will run too fast and display
				Thread.sleep(100);
			} catch (Exception e) {

			}
			while(live){
			locat-= b_speed;
			//y-=b_speed;
			println("locat is   :" +locat);
			
			// to the edge
			if (locat < 0) {
				live = false;
				
			}else if ( getElementAt( x,locat)!=null){
			live=false;
			
			}
			}
		}

	}
}

class Bomb{
	
	double x,y;
	boolean isLive=true;
	int life=9;
	public Bomb( double dx,double dy){
		x=dx;
		y=dy;
	}
	
	public void lifeDown(){
		if( life>0){
			life--;
		}else{
			this.isLive=false;
		}
	}
}
