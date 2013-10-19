import java.applet.AudioClip;
import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;
import acm.graphics.*;

/**
 * Creates a new Zoo which will hold Birds, Monkeys and Dogs
 * @author pplante
 *
 */
public class Zoo extends GraphicsProgram
{
	private static final int APP_WIDTH = 400; 
	private static final int APP_HEIGHT = 400;
	
	private Animal[] animals = new Animal[3];
	
	/**
	 * Starts our zoo by adding in its animals
	 */
	public void run()
	{
		setSize(APP_WIDTH, APP_HEIGHT);
		animals[0] = new Bird(this, 200, 250, getAudioClip(getDocumentBase(), Bird.SONG_PATH));
		addAnimal(animals[0]);
		
		animals[1] = new Monkey(this, 0, 275, getAudioClip(getDocumentBase(), Monkey.SONG_PATH));
		addAnimal(animals[1]);
		
		animals[2] = new Dog(this, 0, 350, getAudioClip(getDocumentBase(), Dog.SONG_PATH));
		addAnimal(animals[2]);
		
		addMouseListeners();
	}
	
	/**
	 * Makes the specifed Animal appear by adding it's GImage
	 * @param animal The animal to make visible
	 */
	public void addAnimal(Animal animal)
	{
		GImage image = new GImage(animal.getImage(), animal.getX(), animal.getY());
		add(image);
	}
	
	/**
	 * Called when the user clicks the app
	 * if they select a Animal make it sing
	 * if they select whitespace make the animals all move
	 */
	public void mouseClicked(MouseEvent e)
	{
		//loop through and if check which animal was clicked
		//make the selected animal sing
		for(int i=0; i < animals.length; i++)
		{
			if(animals[i].inPosition(e.getX(), e.getY()))
			{
				animals[i].talk();
				return;
			}
		}
		
		//loop through the animals and move them
		//then dredraw the animals in the app
		for(int i=0; i < animals.length; i++)
		{
			animals[i].move();
			redrawAnimals();
		}
	}
	
	/**
	 * Redraws the images of the animals after they were all moved
	 */
	public void redrawAnimals()
	{
		removeAll();	//remove all the images
		
		//redraw each of the animals
		for(int i=0; i<animals.length; i++)
		{
			addAnimal(animals[i]);
		}
	}
	
	/**
	 * Checks to see if the given point is in the applet
	 * @param x The x coordinate of the point we're checking
	 * @param y The y coordinate of the point we're checking
	 * @return true if the animal is in the zoo and false otherwise
	 */
	public boolean inZoo(int x, int y)
	{
		if(x < 0 || x + Animal.WIDTH > APP_WIDTH) return false;
		if(y < 0 || y + Animal.HEIGHT > APP_HEIGHT) return false;
		
		return true;
	}
}
