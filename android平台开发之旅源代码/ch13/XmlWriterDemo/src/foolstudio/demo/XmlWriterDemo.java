package foolstudio.demo;

public class XmlWriterDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			XmlUtil.getInstance().saveToFile("C:\\1.xml");
			System.out.println("Save finished!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

};
