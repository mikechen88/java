public class ContainerUser {

	public static void main(String[] args) {
		Container c = new Container(25);
		System.out.println("count = " + countItems(c));
		System.out.println("getCount = " + c.getCount());
	}

	/*
	 * return the amount of the given container object can't call getCount from
	 * Container use recursion( no loops allowed) must leave the Container
	 * unchanged after it's called.
	 */
	public static int countItems(Container c) {

		int count = 0;
		if (c.isEmpty()) { // base case
			return 0;
		} else {
			c.removeOneItem();
			count = 1 + countItems(c);
			c.addOneItem();
			return count;
		}

		
		
		//method 2:
		/*int count = 0;
		if (!c.isEmpty()) { // base case

			c.removeOneItem();
			count = 1 + countItems(c);
			c.addOneItem();
		}
		return count;*/

	}
}
