import java.util.ArrayList;

public class PhoneBook {
	private ArrayList<Person> people = new ArrayList<Person>();

	public PhoneBook() {
	}

	public void add(String firstName, String lastName, String phoneNumber,
			String address) {// if they 've exce
		Person person = new Person(firstName, lastName, phoneNumber, address);
		people.add(person);
	}

	public String[] getList() {
		String[] list = new String[people.size()];
		for (int i = 0; i < people.size(); i++) {
			list[i] = people.get(i).getDetails();

		}

		return list;

	}

}
