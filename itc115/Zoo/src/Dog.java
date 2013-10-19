import java.applet.AudioClip;

public class Dog extends Animal {
	public static final String SONG_PATH = "dog.wav";
	public static final String IMAGE = "dog.jpg";

	public Dog(Zoo zoo, int x, int y, AudioClip sound) {
		super(zoo, x, y, sound);

		image = IMAGE;

	}

	public void move() {
		if (zoo.inZoo(x + 20, y)) {
			this.x = x + 20;
			this.y=y;
		}

	}

}
