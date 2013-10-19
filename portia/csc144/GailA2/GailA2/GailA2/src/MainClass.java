
	// Assignment 3
	// Programmer: Gail Thynes
	// Exercise intended to learn Model View, plus get more familiar with Interfaces and Abstract classes
	// by creating 2 shapes(diamond and plus) on JPanel


import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;


public class MainClass {

	
	public static void main(String[] args) {
		// Create a graphics view and put it in a window
		JFrame frame = new JFrame("Shapes");
		View view = new View();
		frame.getContentPane().setBackground(Color.BLACK);
		//frame.getContentPane().add(view);
		frame.setSize(400, 400); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(view);
		frame.getContentPane().add(view);
		frame.validate();
		frame.setVisible(true);

		// Create the simulation model and populate it
		DrawModel shapes = new DrawModel();
		int x=40;
		for ( int i=0;i<10;i++){
			shapes.add(new Diamond(x*i,50,20,Color.RED));
			shapes.add(new Plus(x*i,100,20,Color.GREEN));
			}
		// Connect the view to the simulation and let it run
		shapes.addView(view);
		//shapes.cycle();
		
	ArrayList<AbstractShape>  newArray=shapes.clone();
		
		shapes.getShapes().get(0).setHeight(100);
		int newHeight=shapes.getShapes().get(0).getHeight();
		int oldHeight=newArray.get(0).getHeight();
			
		System.out.println( "new height is : "+newHeight);
		
		System.out.println( "old height is : "+oldHeight);
		
	}

}
