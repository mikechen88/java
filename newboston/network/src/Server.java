import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame{
	
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	//outputstream      send out message
	private ObjectInputStream input;
	//inputStream       receive message
	
	private ServerSocket server;
	
	private Socket   connection;
	//socket   means connection between  2   computers
	
	//constructor
	public Server(){
		super("server instant messenger");
		userText=new JTextField();
		userText.setEditable(false);
		//before not connect to a person, set false
		
		userText.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					sendMessage(event.getActionCommand());
					userText.setText("");
					//wipe out the text after send message
				}
			}
		);
		add(userText, BorderLayout.NORTH);
		chatWindow=new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(300,150);
		setVisible(true);
	}
	
	//set up and run the server
	public void startRunning(){
		try{
			server=new ServerSocket(6789,100);
			//6789   is  port number,     100 is backlog:mean how many people can wait
			while(true){
				try{
					waitForConnection();
					//wait user to connect
					setupStreams();
					//once user connect it, it setup connection between server and client
					whileChatting(); 
					//when two computer connecting, make message back and forth each other
				}catch(EOFException eofException){
					//end of connection    exception
					showMessage("\n  Server ended the connection");
				}finally{
					closeCrap();
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
			
		}
	}
	//wait for connection,then display connection information
	private void waitForConnection() throws IOException{
		showMessage("Waiting for someone to connect...\n");
		
		//set up socket,if someone connect accept it.
		connection=server.accept();
		showMessage("Now connected to   "+connection.getInetAddress().getHostName());
		//getInetAddress()  return    client address
		//getHostName()       ip address convert to     string
	}
	
	//get stream to send and receive data
	private void setupStreams() throws IOException{
		output=new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		//only server can flush the byte in the buffer,     input stream can't have flush method
		input= new ObjectInputStream(connection.getInputStream());
		
		showMessage("\n Streams are now setup!   \n");		
	}
	//during the chat conversation
	private void whileChatting() throws IOException{
		String message="you are now connected";
		sendMessage(message);
		
		ableToType(true);
		//now it allow user to type
		do{
			//have a conversation
			try{
				message=(String) input.readObject();
				//read whatever the message ,and make sure it's string
				
				showMessage("\n"+ message);
			}catch(ClassNotFoundException  classNotFoundException){
				showMessage("\n idk wtf that user send");
			}
		}while(!message.equals("CLIENT - END"));
		//until user's message is "end"		
	}
	//close streams and sockets after you are done chatting
	private void closeCrap(){
		showMessage("\n   closing  connection........\n");
		
		ableToType(false);
		//disable user to type
		
		try{
			output.close();
			//close output stream
			
			input.close();
			//close input
			
			connection.close();
			//close the socket
		}catch(IOException ioException){
			ioException .printStackTrace();
		}
	}
	//send a message to client
	private void sendMessage(String message){
		try{
			output.writeObject("SERVER - "+message);
			//writeObject   is build in method :   send the message to the outputstream
			output.flush();
			showMessage("\n SERVER - "+message);
			
		}catch(IOException ioException){
			chatWindow.append("\n Error:  dude  i can't send that message");
		}
	}
	
	//updates chatWindow
	private void showMessage(final String text){
		// SwingUtilities.invokeLater  only  update part of guid , prevent update all the screen
		SwingUtilities.invokeLater(
		//set a thread to update guid
			new Runnable(){
				public void run(){
					chatWindow.append(text);
				}
			}
		);
	}
	
	//let user type stuff into their box
	private void ableToType(final boolean tof){
		SwingUtilities.invokeLater(
				//set a thread to update guid
					new Runnable(){
						public void run(){
						userText.setEditable(tof);
						}
					}
		);
	}
	
	
	
	
	
}
