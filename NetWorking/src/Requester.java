import java.io.*;
import java.net.*;
public class Requester {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	Requester(){}
	void run()
	
	{
		try{
			requestSocket = new Socket("192.168.5.102",2004);
			System.out.println("Connected to localHost in port 2004");
			out =  new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			do{
				try{
					message = (String)in.readObject();
					System.out.println("Server>"+ message);
					sendMessage("Hi my server");
					message = "Bye";
					sendMessage(message);
					
				}
				catch(ClassNotFoundException classNot){
					System.err.println("Data received in unknown format");
							
							
				}
			}while(!message.equals("Bye")); 
						
			}
			catch(UnknownHostException unKnownHost){
				System.err.println("Data received in unknown format");
			}
			catch(IOException ioException){
				ioException.printStackTrace();
				
			}
			finally{
				try{
					in.close();
					out.close();
					requestSocket.close();
					
				}
				catch(IOException ioException){
					ioException.printStackTrace();
				}
			}
		}
		void sendMessage(String msg)
		{
			try{
				out.writeObject(msg);
				out.flush();
				System.out.println("Client>"+ msg);
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
			public static void main(String args[])
			{
				Requester client = new Requester();
				client.run();
			}
		
	}


