import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TheWindow extends JFrame{
	private JSlider  slider;
	private DrawOval myPanel;
	
	public TheWindow(){
		super("the title");
		
		myPanel=new DrawOval();
		myPanel.setBackground(Color.orange);
		
		slider=new JSlider(SwingConstants.HORIZONTAL,  0, 200 ,10);
							//direction,   min,   max,
		slider.setMajorTickSpacing(10);
		//tick  everything 10 px;   how far for tick
		slider.setPaintTicks(true);
		// paint them on the screen.
		
		slider.addChangeListener(
				new ChangeListener(){
					public void stateChanged(ChangeEvent e){
						myPanel.setD(slider.getValue());
					}
				}				
		);	
		//add everything to window
		add(slider,BorderLayout.SOUTH);
		add(myPanel,BorderLayout.CENTER);
		
	}
}
