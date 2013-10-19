import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame {
	int x=50,y=50;
	
	Image offScreenImage=null;
	
	public void lauchFrame() {
		this.setLocation(400, 300);
		this.setSize(800, 600);
		this.setTitle("TankWar");
		this.setBackground(Color.GREEN);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}

		});
		setResizable(false);
		setVisible(true);
		
		new Thread( new PaintThread()).start();
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();

	}
	
	public void paint(Graphics g){
		Color c=g.getColor();
		g.setColor(Color.RED);
		
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		
		y+=5;
		
	}
	
	private class PaintThread implements Runnable {
		
		public void run(){
			while(true){
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



}
