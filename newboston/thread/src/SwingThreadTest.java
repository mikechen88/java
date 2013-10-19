import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * this program demonstrates that a thread that runs in parallel with the event dispatch thread
 * can cause errors in Swing components.
 * @author feng
 *
 */
public class SwingThreadTest {
	public static void main( String [] args){
		SwingThreadFrame frame= new SwingThreadFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setVisible( true);
	}
}

// this frame has two buttons to fill a combo box from a separate thread.
//the good button sues the event queue,
//the bad button modifies the combo box directly.
class SwingThreadFrame extends JFrame{
	public SwingThreadFrame(){
		setTitle( "SwingThreadTest");
		
		final JComboBox combo=new JComboBox();
		combo.insertItemAt(new Integer( Integer.MAX_VALUE), 0);
		combo.setPrototypeDisplayValue(combo.getItemAt(0));
		combo.setSelectedIndex(0);
		
		JPanel panel=new JPanel();
		
		JButton goodButton=new JButton("Good");
		goodButton.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event){
				new Thread( new GoodWorkerRunnable(combo)).start();
			}			
		});
		panel.add(goodButton);
		
		JButton badButton=new JButton("Bad");
		badButton.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event){
				new Thread( new GoodWorkerRunnable(combo)).start();
			}			
		});
		panel.add(badButton);
		
		panel.add(combo);
		add(panel);
		pack();
		
		
	}
}

//the runnable modifies a combo box by randomly adding and removing numbers . 
//this can result in errors because the combo box methods are not synchronized 
//and both the worder thread and the event dispatch thread access the combo box
class BadWorkerRunnable implements Runnable{
	private JComboBox combo;
	private Random generator;
	
	public BadWorkerRunnable (JComboBox aCombo){
		combo=aCombo;
		generator= new Random();		
	}
	
	public void run(){
		try{
			while ( true){
				combo.showPopup();
				int i=Math.abs( generator.nextInt());
				if (i%2==0)
					combo.insertItemAt( new Integer(i),0);
				else if (combo.getItemCount()>0)
					combo.removeItemAt( i%combo.getItemCount());
				Thread.sleep(1);
			}
		}catch( InterruptedException e){}
	}
}

//this runnable modifies a combo box by randomly adding and removing numbers.
//in order to ensure that the combo box is not corrupted, the editing operations 
//are forwarded to the event dispatch thread.
class GoodWorkerRunnable implements Runnable{
	private JComboBox combo;
	private Random generator;
	
	public GoodWorkerRunnable( JComboBox aCombo){
		combo=aCombo;
		generator=new Random();
	}
	
	public void run(){
		try {
			while( true){
				EventQueue.invokeLater(new Runnable(){
					public void run(){
						combo.showPopup();
						int i=Math.abs(generator.nextInt());
						if (i%2==0)
							combo.insertItemAt(new Integer(i),0);
						else if (combo.getItemCount()>0)
							combo.removeItemAt(i%combo.getItemCount());
					}					
				});
				Thread.sleep(1);
			}
		}catch( InterruptedException e){}
	}
}



















