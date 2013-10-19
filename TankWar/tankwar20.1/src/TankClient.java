import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//add wall
public class TankClient extends Frame {
	public static final int GAME_WIDTH=800;
	public static final int GAME_HEIGHT=600;

	Tank myTank= new Tank(50,50,true,Tank.Direction.STOP,this);
	
	
	List<Explode> explodes=new ArrayList<Explode>();
	
	List<Missile> missiles= new ArrayList<Missile>();
	
	List<Tank> tanks = new ArrayList<Tank>();
	
	Missile m=null;

	Image offScreenImage = null;
	
	Wall w1=new Wall(100,200,20,150,this),w2=new Wall(300,100,300,20,this);

	// make a image in the background first
	// then better for watching

	public void lauchFrame() {
		
		for ( int i=0;i<10;i++){
			tanks.add( new Tank ( 50+40*(i+1),50,false,Tank.Direction.D,this));
		}
		//this.setLocation(400, 300);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("TankWar");
		this.setBackground(Color.GREEN);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}

		});
		setResizable(false);
		this.addKeyListener(new KeyMonitor());
		setVisible(true);

		new Thread(new PaintThread()).start();
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();

	}

	public void paint(Graphics g) {
		//display number of missile
		g.drawString("missile count:"+missiles.size()	,10,50);
		g.drawString("explodes count:"+explodes.size()	,10,70);
		g.drawString("tanks count:"+tanks.size()	,10,90);
		
		for (int i=0;i<missiles.size();i++){
			//if bullet is out ,remove the bullet
			//*if (!m.isLive()) missiles.remove(i);
			
			Missile m=missiles.get(i);
			
			m.hitTanks(tanks);
			m.hitTank(myTank);
			
			m.hitWall(w1);
			m.hitWall(w2);

			m.draw(g);
			w1.draw(g);
			w2.draw(g);
		}
		
		for ( int i=0;i<explodes.size();i++){
			Explode e=explodes.get(i); 
			e.draw(g);
		}
		
		for ( int i=0;i<tanks.size();i++){
			Tank t=tanks.get(i);
			t.collidesWithWall(w1);
			t.collidesWithWall(w2);
			t.draw(g);
		}
		myTank.draw(g);
	
	}

	public void update(Graphics g) {// this pen (g) is front page
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		// get background image's pen
		Graphics gOffScreen = offScreenImage.getGraphics();

		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0,GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);

		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	private class PaintThread implements Runnable {

		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private class KeyMonitor extends KeyAdapter {

		
		public void keyReleased(KeyEvent e) {
			myTank.KeyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			myTank.KeyPressed(e);
			
		}
		
	}

}
