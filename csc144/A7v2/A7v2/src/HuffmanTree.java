import java.io.IOException;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;


public class HuffmanTree {
	
	 public static final int CHAR_MAX = 256;  // max char value to be encoded
	 private Node root;
	 PriorityQueue<Node> queue = new PriorityQueue<Node>();
	 
	// constructor for Huffman Tree
	public HuffmanTree(int[] count){
		
		for(int i = 0; i<count.length; i++){
			if(count[i] > 0){
				Node node = new Node(count[i] , i);
				queue.add(node);
			}
		}
		Node last = new Node(1, count.length);
		queue.add(last);
		while (queue.size() > 1){
			Node q1 = queue.poll();
			Node q2 = queue.poll();
			int result = q1.frequency + q2.frequency;
			Node node = new Node(result, q1, q2);
			queue.add(node);
		}
		//root = queue.peek();
	}
	// alternate constructor that takes a scanner 
	// this is for Decode
	public HuffmanTree(Scanner input){
		Node root = new Node(-1, null, null);
		queue.add(root);
		Node node = queue.peek();
		while(input.hasNext()){
			int n = Integer.parseInt(input.nextLine());
			String code = input.nextLine();
			//Node node = root;
			for (int i = 0; i<code.length(); i++){
				char c = code.charAt(i);		
				if (c == '0'){
					// go left
					if (node.left==null){
						node.left = new Node(-1, null, null);
						node = node.left;
					} else {
						node = node.left;
					}
				}else { // c==1
					// go right
					if (node.right==null){
						node.right = new Node(-1, null, null);
						node = node.right;
					} else {
						node = node.right;
					}
				}				
			}
			node.c = n;
			node = queue.peek();
			// creates last node with eof
			//Node last = new Node(1, );
			//queue.add(last);
		}
}
		public Node getRoot(){
			return root;
		}
				
		// this method writes the current tree to the given output stream
		public void write(PrintStream output){
			Node current = root;
			write(current, output, "");
			
			
		}
		private void write(Node node, PrintStream output, String s ){
			
			// if node has a char value - print it out
			if (node.right == null && node.left == null){
				// this test tells me that this is a leaf node
				// print out ASCII value
				output.println(node.c);
				// print out Huffman encoded value 
				output.println(s);
				
										
			} else {
				// recurse through the tree until you find a leaf
				write(node.left, output, s + "0"); 
				write(node.right, output, s + "1"); 

			}
		}

		// reads bits for the given input stream and writes the corresponding 
		// characters to the output
		public void decode(BitInputStream input, PrintStream output, int eof){			
			// create a loop to read the input
			int n = input.readBit();
			Node current = queue.peek();
				while(n!=-1){
				// check for end of file													
					if(n==0){
							// go left
						current = current.left;	
						//System.out.println("n=0");
					} else {
						// go right
						current = current.right;
						//System.out.println("n=1");
					}
					//System.out.println(current.left == null);
					//System.out.println(current.right == null);
					if (current.left == null && current.right == null){
						// you've reached a leaf node and can print it out
						//print out character at the end of the tree in the node
						if (eof!=1){
							output.write(current.c);
						}
						//System.out.println(current.c);
						//current = root;	
						current = queue.peek();
					}
					n = input.readBit();
				}								
		}
		
	
	// Inner class for Node	implements Comparable
	private class Node implements Comparable<Node>{
		int frequency;
		Node left;
		Node right;
		int c;
		
		// creates a parent in the BTree or branch
		private Node(int frequency, Node left, Node right){
			this.frequency = frequency;
			this.left = left;
			this.right = right;
		}
		// creates a leaf if the bTree
		private Node(int frequency, int c){
			this.c = c;
			this.frequency = frequency;
			
		}
		@Override
		public int compareTo(Node node2) {
			// if node equals return 0
			if(frequency == node2.frequency){
				return 0;
				// if node is less than return -1
			} else if (frequency < node2.frequency){
				return -1;
				// if node is greater than return 1
			} else {
				return 1;
			}
		}
	}

}

