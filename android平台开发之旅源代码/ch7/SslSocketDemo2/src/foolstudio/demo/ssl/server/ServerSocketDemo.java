package foolstudio.demo.ssl.server;

public class ServerSocketDemo {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage: java foolstudio.demo.ssl.server.ServerSocketDemo <ks-path>");
			return;
		}
		
		new ServerSocketDemo().doAction(args[0]);
	}
	
	public ServerSocketDemo() {
	}
	
	public void doAction(String ksPath) {
		ServerSocketThread t = new ServerSocketThread(ksPath);
		t.setIsRunning(true);
		t.start();
	}
};
