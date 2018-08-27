import java.net.*;
import java.io.*;

public class Server{
	public static void main(String[] args){
		try{
			ServerSocket server=new ServerSocket(8888);
			int counter=0;
			System.out.println("Server started ...");
			while(true){
				counter++;
				Socket client=server.accept();
				System.out.println(" >> "+"Client No:"+counter+" connected...");
				ClientThread clientThread=new ClientThread(client,counter);
				clientThread.start();
			}
		}catch(Exception e){
			System.out.println("Error in starting server: "+e.toString());
		}
	}
}

class ClientThread extends Thread{
	Socket client;
	int clientId;
	
	ClientThread(Socket client, int counter){
		this.client=client;
		this.clientId=counter;	
	}

	public void run(){
		try{
			DataInputStream inputStream=new DataInputStream(client.getInputStream());
			DataOutputStream outputStream=new DataOutputStream(client.getOutputStream());
			String clientMessage="";
			String serverMessage="";

			while(!clientMessage.equals("bye")){
				clientMessage=inputStream.readUTF();
				System.out.println("Message from client-"+this.clientId+":"+clientMessage);
				outputStream.writeUTF("Message from server: I have read your message client-"+this.clientId);
				outputStream.flush();
			}
			
			inputStream.close();
			outputStream.close();
			client.close();
		}catch(Exception e){
			System.out.println("Exception in starting client thread: "+e.toString());
		}finally{
			System.out.println("client-"+this.clientId+" exitting now!!!");
		}
	}
}
