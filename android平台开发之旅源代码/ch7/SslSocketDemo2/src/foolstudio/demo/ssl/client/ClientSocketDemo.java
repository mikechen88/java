package foolstudio.demo.ssl.client;

public class ClientSocketDemo {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage: java foolstudio.demo.ssl.client.ClientSocketDemo <ks-path>");
			return;
		}
		
		new ClientSocketDemo().doAction(args[0]);
	}
	
	public ClientSocketDemo() {
	}
	
	public void doAction(String ksPath) {
		ClientSocketThread t = new ClientSocketThread(ksPath);
		t.start();
	}
}
