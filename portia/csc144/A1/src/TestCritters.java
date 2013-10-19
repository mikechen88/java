import java.awt.Color;

import junit.framework.TestCase;

public class TestCritters extends TestCase implements CritterConstants {

	public void testStone() {
		Stone st = new Stone();
		assertTrue(st.getMove(null) == CENTER);
		assertTrue(st.toString().equals("S"));
		assertTrue(st.getColor().equals(Color.GRAY));
		String[] opponent = { "S", "T", "L", "B" };
		for (String s : opponent) {
			assertTrue(st.fight(s) == ROCK);
		}

	}

	public void testBear() {
		Bear st = new Bear();
		assertTrue(st.getMove(null) == NORTH || st.getMove(null) == WEST);
		assertTrue(st.toString().equals("B"));
		assertTrue(st.getColor().equals(new Color(128, 128, 64)));
		String[] opponent = { "S", "T", "L", "B" };
		for (String s : opponent) {
			assertTrue(st.fight(s) == SCISSORS);
		}
	}

	public void testLion() {
		Lion st = new Lion(5);
		int dir=st.getMove(null);
		assertTrue(dir == NORTH || dir == WEST
				||dir == SOUTH || dir== EAST);
		
		assertTrue(st.toString().equals("L"));
		assertTrue(st.getColor().equals(Color.YELLOW));
		String[] opponent = { "S", "T", "L", "B" };
		for (String s : opponent) {
			assertTrue(st.fight(s) == PAPER);
		}
	}

	public void testTiger() {
		Tiger st = new Tiger(Color.YELLOW);
		int dir=st.getMove(null);
		assertTrue(dir == NORTH || dir == WEST
				||dir == SOUTH || dir== EAST);
		assertTrue(st.toString().equals("T"));
		assertTrue(st.getColor().equals(Color.yellow));
		String[] opponent = { "S", "T", "L", "B" };
		for (String s : opponent) {
			assertTrue(st.fight(s) == SCISSORS && st.fight(s) == PAPER);
		}
	}

	public void testWolf() {
		Wolf st = new Wolf();	
		int dir=st.getMove(new CritterInfoTest());
		assertTrue(dir==NORTH||dir==WEST||dir==EAST||dir==SOUTH);
		assertTrue(st.toString().equals("ROCK")||st.toString().equals("SCISSORS")||st.toString().equals("BEAR")||st.toString().equals("BEAR")||st.toString().equals("WOLF"));
		assertTrue(st.getColor().equals(Color.RED));
		String[] opponent = { "S", "T", "L", "B" };
		for (String s : opponent) {
			assertTrue(st.fight(s) == PAPER||st.fight(s) == ROCK||st.fight(s) == SCISSORS);
		}
	}
}
