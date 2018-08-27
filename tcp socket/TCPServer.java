import java.net.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class TCPServer{
	public static void main(String[] args){
		try{
			ServerSocket server=new ServerSocket(8888);
            System.out.println("Server started at: localhost:8888");
            Socket client=server.accept();
				System.out.println("Client connected...");
				try{
                    DataInputStream inputStream=new DataInputStream(client.getInputStream());
                    DataOutputStream outputStream=new DataOutputStream(client.getOutputStream());
                    String clientMessage="";
        
                    while(!clientMessage.equals("EOF")){
                        clientMessage=inputStream.readUTF();
                        System.out.println("Received:"+clientMessage);
                        TimeUnit.MILLISECONDS.sleep(500);
                        outputStream.writeUTF("TRAMISSION SUCCESSFUL");
                        outputStream.flush();
                    }

                    System.out.println("\n\nTRAMISSION SUCCESSFUL");
                    
                    inputStream.close();
                    outputStream.close();
                    client.close();
                    server.close();
                }catch(Exception e){
                    System.out.println("Exception in starting client thread: "+e.toString());
                }
			
		}catch(Exception e){
			System.out.println("Error in starting server: "+e.toString());
		}
	}
}

