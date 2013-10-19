import java.applet.AudioClip;

public class Bird extends Animal {
	public static String SONG_PATH = "bird.wav";

	public static String IMAGE = "bird.jpg";

	public Bird(Zoo zoo, int x, int y, AudioClip sound) {
		super(zoo, x, y, sound);

		image = IMAGE;
	}

	public void move() {
		if (zoo.inZoo(x, y - 10)) {
			this.x = x;
			this.y = y - 10;
		}

	}

}
