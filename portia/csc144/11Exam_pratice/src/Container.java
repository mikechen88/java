public class Container {
	private int count;

	public Container(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("Negative count!");
		}
		this.count = count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public void addOneItem() {
		count++;
	}

	public void removeOneItem() {
		if (isEmpty()) {
			throw new IllegalStateException("empty container!");
		}
		count--;
	}
	
	public int getCount() {
		return count;
	}
}


