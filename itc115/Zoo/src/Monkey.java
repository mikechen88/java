import java.applet.AudioClip;

public class Monkey extends Animal {

	public static String SONG_PATH = "monkey.wav";
	public static String IMAGE = "monkey.jpg";

	public Monkey(Zoo zoo, int x, int y, AudioClip sound) {
		super(zoo, x, y, sound);
		image = IMAGE;

	}

	public void move() {
		if (zoo.inZoo(x + 10, y - 3)) {
			this.x = x + 10;
			this.y = y - 3;
		}

	}

}
