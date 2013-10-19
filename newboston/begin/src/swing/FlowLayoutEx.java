package swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlowLayoutEx extends JFrame{
	private  JButton lb,cb,rb;
	
	private FlowLayout  layout;
	private Container container;
	
	public FlowLayoutEx(){
		super("the title");
		
		layout =new FlowLayout();
		container =getContentPane();
		//get the bulk of the window 
		//it know where to put the stuff
		
		setLayout(layout);
		
		//left stuff here
		lb=new JButton("left");
		add(lb);
		lb.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						layout.setAlignment(FlowLayout.LEFT);
						//change everything to left
						
						layout.layoutContainer(container);
						//rearrange everything depend on what our layout is 
					}
				}
				);
		
	

		//center tuff here
		cb=new JButton("center");
		add(cb);
		cb.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						layout.setAlignment(FlowLayout.CENTER);
						//change everything to left
						
						layout.layoutContainer(container);
					}
				}
				);
		
		//left stuff here
		rb=new JButton("right");
		add(rb);
		rb.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						layout.setAlignment(FlowLayout.RIGHT);
						//change everything to left
						
						layout.layoutContainer(container);
					}
				}
				);
		
		
		
		
	}
}
