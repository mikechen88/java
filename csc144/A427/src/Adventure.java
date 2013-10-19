/*
 * File: Adventure.java
 * --------------------
 * This program plays the Adventure game from Assignment #4.
 */

import java.io.*;
import java.util.*;

/* Class: Adventure */
/**
 * This class is the main program class for the Adventure game.
 */

public class Adventure {
	AdvRoom room;
	List<AdvRoom> rooms = new ArrayList<AdvRoom>();;

	AdvObject object;
	Map<String, Integer> objects = new HashMap<String, Integer>();
	List<AdvObject> objectsss = new ArrayList<AdvObject>();
	// Map<String, Integer> inventries = new HashMap<String, Integer>();
	List<AdvObject> inventries = new ArrayList<AdvObject>();

	Map<String, String> synos = new HashMap<String, String>();

	AdvRoom current;

	// get current room entry table
	AdvMotionTableEntry[] entries;

	// read the command
	String[] command;

	Scanner in;

	int backRoom = 1;

	// Use this scanner for any console input
	private static Scanner scan = new Scanner(System.in);

	/**
	 * This method is used only to test the program
	 */
	public static void setScanner(Scanner theScanner) {
		scan = theScanner;
		// Delete the following line when done
		// AdventureStub.setScanner(theScanner);
	}

	public void run() {
		System.out.print("\nEnter the name of the course file: ");
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine().trim();

		try {

			// read room file
			File file = new File(fileName + "Rooms.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				room = AdvRoom.readFromFile(scan);
				rooms.add(room);
			}

			// read object file
			// add to relative room
			File obj_file = new File(fileName + "Objects.txt");
			Scanner scanObject = new Scanner(obj_file);
			while (scanObject.hasNextLine()) {
				object = AdvObject.readFromFile(scanObject);
				objectsss.add(object);
				objects.put(object.getDescription(),
						Integer.valueOf(object.getInitialLocation()));
				for (int l = 0; l < rooms.size(); l++) {
					if (object.getInitialLocation() == (rooms.get(l)
							.getRoomNumber())) {
						rooms.get(l).addObject(object);
					}
				}
			}

			// read synonyms file
			File synonyms = new File(fileName + "Synonyms.txt");
			Scanner scanSynonyms = new Scanner(synonyms);
			String line1;
			while (scanSynonyms.hasNextLine()
					&& (!(line1 = scanSynonyms.nextLine().trim().toUpperCase())
							.equals(""))) {
				String[] line = line1.split("=");
				synos.put(line[1], line[0]);
			}
			if (fileName.equals("small")) {
				synos.put("TAKE", "TAKE");
				synos.put("DROP", "DROP");

			}

			// add unknow direction with synos file
			for (int m = 0; m < rooms.size(); m++) {
				for (int n = 0; n < rooms.get(m).motions.size(); n++) {
					if (!synos.containsKey(rooms.get(m).motions.get(n)
							.getDirection().toUpperCase())) {
						synos.put(rooms.get(m).motions.get(n).getDirection(),
								rooms.get(m).motions.get(n).getDirection());
					}
				}
			}
			// ADD help command
			synos.put("HELP", "HELP");

			// add back command return to the room which we drop object
			synos.put("B", "B");

			current = rooms.get(0);

			do {

				initRoom();
				// read the command
				command = null;
				command = in.nextLine().trim().toUpperCase().split("\\s+");

				if (synos.containsKey(command[0])
						|| synos.containsValue(command[0])) {
					if (synos.containsValue(command[0])) {
						for (String s : synos.keySet()) {
							if (synos.get(s).equals(command[0])) {
								command[0] = s;
							}
						}
					}

					switch (command[0]) {
					case "UP":
					case "NORTH":
					case "IN":
					case "SOUTH":
					case "DOWN":
					case "OUT":
					case "WEST":
					case "EAST":

						executeMotionCommand(command[0]);
						break;

					case "HELP":
						executeHelpCommand();
						break;

					case "QUIT":
					case "Q":
						executeQuitCommand();

					case "LOOK":
						executeLookCommand();
						break;

					case "INVENTORY":
						executeInventoryCommand();
						break;

					case "DROP":

						if (command.length == 2) {

							if (synos.containsValue(command[1])) {
								for (String s : synos.keySet()) {
									if (synos.get(s).equals(command[1])) {
										command[1] = s;
									}
								}
							}
							for (int x = 0; x < objectsss.size(); x++) {

								if (objectsss.get(x).getName()
										.equals(command[1])) {
									executeDropCommand(objectsss.get(x));
									break;
								}
							}
						} else {
							System.out
									.println("  Warning!   You need input object name ");
							break;

						}

						break;
					case "B":
						executeBack();
						break;

					case "TAKE":
						if (command.length == 2) {

							if (synos.containsValue(command[1])) {
								for (String s : synos.keySet()) {
									if (synos.get(s).equals(command[1])) {
										command[1] = s;
									}
								}
							}
							System.out
									.println("take             " + command[1]);
							for (int x = 0; x < objectsss.size(); x++) {

								if (objectsss.get(x).getName()
										.equals(command[1])) {
									executeTakeCommand(objectsss.get(x));
									break;
								}
							}

						} else {
							System.out
									.println("  Warning!   You need input object name ");
							break;

						}

						break;
					default:
						System.out
								.println("**********I don't know what do you want********");
						System.out.println();
						break;
					}
				} else {
					System.out.println("   Invalid command");
				}

			} while (!command.equals("q"));

		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find " + fileName);
			return;
		}

	}

	public void initRoom() {
		entries = current.getMotionTable();
		System.out.println(current.getName());

		if (!current.hasBeenVisited()) {
			current.setVisited(true);
			printDescription();
		}

		// if is "force" force to the room
		if (entries[0].getDirection().equals("FORCED")) {
			printDescription();
			if (entries[0].getDestinationRoom() == 0) {
				System.out
						.println("Congradation!   ************    Good   Job!!!!!!");
				System.exit(0);
			}
			current = rooms.get(entries[0].getDestinationRoom() - 1);
			return;
		}

		

	}

	/* Method: executeMotionCommand(direction) */
	/**
	 * Executes a motion command. This method is called from the
	 * AdvMotionCommand class to move to a new room.
	 * 
	 * @param direction
	 *            The string indicating the direction of motion
	 */
	public void executeMotionCommand(String direction) {

		for (int i = 0; i < entries.length; i++) {
			if (entries[i].getDirection().equals(direction)) {

				if (entries[i].getKeyName() != null) {
					for (int j = 0; j < inventries.size(); j++) {
						if (inventries.get(j).getName()
								.equals(entries[i].getKeyName())) {
							current = rooms
									.get(entries[i].getDestinationRoom() - 1);
							return;
						}
					}
				} else if (entries[i].getKeyName() == null) {
					current = rooms.get(entries[i].getDestinationRoom() - 1);
					return;
				}

			}
		}

		// super.executeMotionCommand(direction); // Replace with your code

	}

	/* Method: executeQuitCommand() */
	/**
	 * Implements the QUIT command. This command should ask the user to confirm
	 * the quit request and, if so, should exit from the play method. If not,
	 * the program should continue as usual.
	 */
	public void executeQuitCommand() {
		System.out.print("Are you sure you want to quite (Yes/Y) or no ");
		String quite = scan.nextLine();
		if (quite.equals("yes") || quite.equals("y")) {
			System.out.println("\nThank you for your game");
			System.exit(0);
		}
		return;
		// super.executeQuitCommand(); // Replace with your code
	}

	/* Method: executeHelpCommand() */
	/**
	 * Implements the HELP command. Your code must include some help text for
	 * the user.
	 */
	public void executeHelpCommand() {
		System.out.println( "You are at   "+ current.getRoomNumber()+ "    room");
		// get current room entry table
				// comment out here. it doesn't hurt the program.

				for (int i = 0; i < entries.length; i++) {
					System.out.println(entries[i].getDirection()
							+ "     "
							+ entries[i].getDestinationRoom()
							+ ((entries[i].getKeyName() == null) ? "" : "  /  "
									+ (entries[i].getKeyName())));

				}
				System.out.println("--------------------------------");
		for (int x = 0; x < objectsss.size(); x++) {
			System.out.println("   There is a " + objectsss.get(x).getName()
					+ "  at  " + " room "
					+ objectsss.get(x).getInitialLocation());
		}
		// super.executeHelpCommand(); // Replace with your code
	}

	/* Method: executeLookCommand() */
	/**
	 * Implements the LOOK command. This method should give the full description
	 * of the room and its contents.
	 */
	public void executeLookCommand() {
		printDescription();
		// super.executeLookCommand(); // Replace with your code
	}

	/* Method: executeInventoryCommand() */
	/**
	 * Implements the INVENTORY command. This method should display a list of
	 * what the user is carrying.
	 */
	public void executeInventoryCommand() {
		if (inventries.size() == 0) {
			System.out.println("It looks like you don't have anything");
		} else {
			for (int i = 0; i < inventries.size(); i++) {
				System.out.println("You have " + inventries.get(i).getName()
						+ "\n");
			}
		}
		// super.executeInventoryCommand(); // Replace with your code
	}

	/* Method: executeTakeCommand(obj) */
	/**
	 * Implements the TAKE command. This method should check that the object is
	 * in the room and deliver a suitable message if not.
	 * 
	 * @param obj
	 *            The AdvObject you want to take
	 */
	public void executeTakeCommand(AdvObject obj) {

		if (current.containsObject(obj)) {
			inventries.add(obj);
			current.removeObject(obj);
			System.out.println("you taken a  " + obj.getName());

		} else {
			System.out.println("sorry , There isn't  " + obj.getName()
					+ "    in this room");
		}
		// super.executeTakeCommand(obj); // Replace with your code
	}

	/* Method: executeDropCommand(obj) */
	/**
	 * Implements the DROP command. This method should check that the user is
	 * carrying the object and deliver a suitable message if not.
	 * 
	 * @param obj
	 *            The AdvObject you want to drop
	 */
	public void executeDropCommand(AdvObject obj) {

		if (inventries.contains(obj)) {
			inventries.remove(obj);

			for (int i = 0; i < objectsss.size(); i++) {
				if (objectsss.get(i).getName().equals(obj.getName())) {
					objectsss.get(i).setLocation(current.getRoomNumber());
				}
			}
			current.addObject(obj);
			backRoom = current.getRoomNumber() - 1;
			System.out.println("You drop a " + obj.getName());
		} else {
			System.out.println("sorry , There isn't " + obj
					+ "   in your inventory.");
		}

	}

	public void executeBack() {
		current = rooms.get(backRoom);
		return;
	}

	/* Private instance variables */
	// Add your own instance variables here

	// print this room's description
	public void printDescription() {
		// System.out.println(current.getName());
		String[] descriptions = current.getDescription();
		for (int k = 0; k < descriptions.length; k++) {
			System.out.println(descriptions[k]);
		}
		System.out.println("------------------------------------");

	}

	/**
	 * Runs the adventure program
	 */
	public static void main(String[] args) {
		// AdventureStub.main(args); // Replace with your code

		Adventure adventure = new Adventure();
		adventure.run();
	}
}
