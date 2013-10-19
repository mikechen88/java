
import java.io.*;

/** Test the BSTree class */
public class TestTree {


    /**
     * Creates a tree and lets the user manipulates the tree
     */
    public static void main(String[] args) {
        // Empty tree
        BSTree t = new BSTree();

        // Create a tree of height H that contains 1,2,...,2^H-1
        int H=5;
        for(int h=0; h<H; ++h)
        {
            for(int v = 0; v < (int)Math.pow(2,h); v++)
            {
                int val = (2*v+1)*(int)(Math.pow(2,H-h-1));
                t.add(new Integer(val));
            }
        }

        for(int i=0; i<20; ++i) {
            t.add(new Integer((int)(Math.random()*100)));
        }


        boolean quit = false; // true when the user terminates the program
        String userChoice;   // command selected by the user

        System.out.println("Type L for a list of the commands");

        // to get the input from the console window
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // loop to get the user's choice
        while(!quit) {
            System.out.print(">"); //Prompt
            userChoice = readString(in);

            // Process the command
            userChoice = userChoice.toUpperCase();
            userChoice = userChoice.trim();
            try{
                command(userChoice,t);
            }
            catch(Exception e) {
                // End up here if the user uses a bad format for a command
                // (e.g. bad format for an integer as in "I 1.3")
                System.out.println("Invalid command format");
            }

            // Quit the program if the user asks for it
            if (userChoice.startsWith("Q")) {
                quit = true;
            }
        }

        System.exit(0);
    }

    /**
     * Processes the command selected by the user
     *
     * @param choice the command string given by the user
     * @param t the tree to manipulate
     *
     */
    private static void command(String choice, BSTree t) {
        // process the command requested by the user
        if (choice.startsWith("L")) {
            // List all of the commands
            commandList();
        }
        else if (choice.startsWith("PRE")) {
            // print the tree (preorder)
            t.printPreOrder(System.out);
            System.out.println();
        }
        else if (choice.startsWith("POS")) {
            // print the tree (postorder)
            t.printPostOrder(System.out);
            System.out.println();
        }
        else if (choice.startsWith("IN")) {
            // print the tree (inorder)
            t.printInOrder(System.out);
            System.out.println();
        }
        else if (choice.startsWith("I")) {
            // Insert an item in the tree
            int val = Integer.parseInt(choice.substring(1).trim());
            t.add(new Integer(val));
        }
        else if (choice.startsWith("R")) {
            // Remove an item from the tree
            int val = Integer.parseInt(choice.substring(1).trim());
            t.remove(new Integer(val));
        }
        else if (choice.startsWith("F")) {
            // Find an item in the tree
            int val = Integer.parseInt(choice.substring(1).trim());
            if (t.contains(new Integer(val)))
                System.out.println(val+" is in the tree");
            else
                System.out.println(val+" is not in the tree");
        }
        else if (choice.startsWith("S")) {
            //Number of nodes
            System.out.println("Number of nodes = "+t.size());
        }
        else if (choice.startsWith("H")) {
            //Height of the tree
            System.out.println("Tree Height = "+t.height());
        }
        else if (choice.startsWith("C")) {
            //Clone the current tree
            t=(BSTree)(t.clone());
        }
        else if (!choice.startsWith("Q")) {
            // Unknown command
            System.out.println("This command is not available");
        }
    }

    private static void commandList() {
        // give a list of all the commands
        String output;
        output =  "Available commands\n";
        output += "L        : to display the list of the commands\n";
        output += "I <val>  : to insert a value in the tree (e.g. I 10)\n";
        output += "R <val>  : to remove a value from the tree (e.g. R 10)\n";
        output += "           (you can also click on the node in the tree window)\n";
        output += "F <val>  : to find a value in the tree (e.g. F 10)\n";
        output += "H        : to print the tree height\n";
        output += "S        : to print the number of nodes in the tree\n";
        output += "PRE      : to print the tree preorder\n";
        output += "IN       : to print the tree inorder\n";
        output += "POS      : to print the tree postorder\n";
        output += "C        : to clone the current tree\n";
        output += "Q        : to terminate the program";

        System.out.println(output);
    }

    // Auxiliary functions to read a String
    private static String readString(BufferedReader in) {
        String input="";
        try {
            input = in.readLine();
        }
        catch(IOException e)
        {
            System.out.println("Problem reading the input");
            System.exit(0);
        }

        return input;
    }
}