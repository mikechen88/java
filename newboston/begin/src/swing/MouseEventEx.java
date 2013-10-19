package swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MouseEventEx extends JFrame{
	private JPanel mousepanel;
	private JLabel statusbar;
	
	public MouseEventEx(){
		super("title");
		
		mousepanel=new JPanel();
		mousepanel.setBackground(Color.white);
		add(mousepanel,BorderLayout.CENTER);
		
		statusbar=new JLabel();
		add(statusbar,BorderLayout.SOUTH);
		
		HandlerClass handler=new HandlerClass();
		
		mousepanel.addMouseListener(handler);
		mousepanel.addMouseMotionListener(handler);
	}
	
	private class HandlerClass implements MouseListener,MouseMotionListener{
		//when you click down and release at the same place
		//it's click
		public void mouseClicked(MouseEvent event){
			statusbar.setText(String.format("Clicked at %d ,%d",event.getX(),event.getY()));
		}
		
		public void mousePressed(MouseEvent event){
			statusbar.setText("you pressed down the mouse");
		}
		//when you press and drag,and release at different place
		//it's called released
		public void mouseReleased(MouseEvent event){
			statusbar.setText("you released the mouse");
		}
		
		public void mouseEntered( MouseEvent event){
			statusbar.setText("you enter the area");
			mousepanel.setBackground(Color.red);
			
		}
		
		public void mouseExited(MouseEvent  event){
			statusbar.setText("mouse has left the window");
			mousepanel.setBackground(Color.white);
		}
		//this 5 methods is mouselistener  event
		
		
		
		//this 2 methods is mousemontionlistener   event
		public void mouseDragged(MouseEvent event){
			statusbar.setText("you are dragging the mouse");
		}
		public void mouseMoved(MouseEvent event){
			statusbar.setText("you are moving the mouse");
		}
		
	}
	
	
	
}
