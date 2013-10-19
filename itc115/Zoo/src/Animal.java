import java.applet.AudioClip;

public abstract class Animal {
	Zoo zoo;
	int x;
	int y;
	AudioClip sound;
	String image;

	public static int WIDTH = 50;
	public static int HEIGHT =50;

	public Animal(Zoo zoo, int x, int y, AudioClip sound) {
		this.zoo = zoo;
		this.x = x;
		this.y = y;
		this.sound=sound;

	}

	//play the song
	public void talk() {

		sound.play();
	}

	//if click empty space, return false
	//if click image itself , return true
	public boolean inPosition(int x, int y) {
		if (x < this.getX() || x > this.getX() + this.WIDTH)
			return false;
		if (y < this.getY() || y > this.getY() + this.HEIGHT)
			return false;
		return true;
	}

	public abstract void move();

	//get x value
	public int getX() {
		return x;

	}

	//get y value
	public int getY() {
		return y;
	}
	
	//return image string 
	public String getImage() {
		return image;
	}



}
