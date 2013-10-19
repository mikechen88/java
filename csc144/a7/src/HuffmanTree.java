import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {

	public int[] count;

	PriorityQueue<HuffmanNode> pq;

	HuffmanNode root = new HuffmanNode();

	HuffmanNode node;

	int CHAR_MAXX = 256;

	//the constructor  for read file
	public HuffmanTree(Scanner s) {

		String input = "";
		while (s.hasNextLine()) {

			int val = Integer.parseInt(s.nextLine());

			String path = s.nextLine();
			setNode(val, path);
		}
	}
//recreate the tree
	private void setNode(int val, String path) {
		HuffmanNode t = root;
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '0') {
				if (t.left == null) {
					t.left = new HuffmanNode();
				}
				t = t.left;
			} else {
				if (t.right == null) {
					t.right = new HuffmanNode();
				}
				t = t.right;
			}
		}
		t.c = (char) val;
	}

	//constructor     create a tree from the pass in array
	public HuffmanTree(int[] count) {
		this.count = count;

		pq = new PriorityQueue<HuffmanNode>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0)
				pq.add(new HuffmanNode(i, count[i]));
		}
//add end of file node
		pq.add(new HuffmanNode(256, 1));

		HuffmanNode r = null;
		while (pq.size() > 1) {
			HuffmanNode t1 = pq.remove();
			HuffmanNode t2 = pq.remove();
			int temp = t1.getNum() + t2.getNum();
			// r = new HuffmanNode(' ', temp);
			r = new HuffmanNode(0, temp); // 0 represents for null characters
			r.left = t1;
			r.right = t2;
			pq.add(r);
		}
		pq.remove();
		this.root = r; //set the root value

	}

	//write to the file 
	public void write(PrintStream p) {
		System.out.println("break");
		print(p, root, "");
	}

	public void print(PrintStream p, HuffmanNode node, String binary) {

		if (node == null)
			return;
		if (node.left == null && node.right == null) {
			p.println(node.charVal());
			p.println(binary);
		} else {
			print(p, node.left, binary + 0);
			print(p, node.right, binary + 1);
		}
	}

	public void decode(BitInputStream input, OutputStream output, int eof) {

		int c;
		HuffmanNode t = root;
		//go to the end to place the leaf
		while (true) {
			c = input.readBit();
			if (c == 1) {
				t = t.right;
			} else {
				t = t.left;
			}

			if (t.c != 0) {
				if (t.c == eof)
					break;
				try {
					output.write((char) t.c);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t = root;
			}

		}
	}

}

// //////////// Tree class /////////////
class HuffmanNode implements Comparable<HuffmanNode> {

	char c;
	int n;

	HuffmanNode left, right;
	boolean leaf;
	String binary;

	int weight;

	public HuffmanNode(int i, int n) {
		// this.c = (char) (i + '0');
		this.c = (char) i;
		this.n = n;
		leaf = true;
		binary = "";
		left = null;
		right = null;
	}

	public HuffmanNode() {

	}

	public HuffmanNode(HuffmanNode a, HuffmanNode b) {
		left = a;
		right = b;
		leaf = false;
		n = a.n + b.n;

	}

	public int charVal() {
		return c;
	}

	public void setWeight(int n) {
		this.weight = n;
	}

	public int getWeight() {
		return this.weight;
	}

	public boolean equals(HuffmanNode second) {
		if (this == second)
			return true;
		return false;
	}

	public char getChar() {
		return c;

	}

	public int getNum() {
		return n;

	}

	public String getBinNum() {
		return binary;
	}

	public void setNum(int n) {
		this.n = n;
	}

	@Override
	public int compareTo(HuffmanNode second) {
		if (this.n < second.n) {
			return -1;
		} else if (this.n == second.n) {
			return 0;
		} else
			return 1;

	}

}