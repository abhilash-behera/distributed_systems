import java.net.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;

public class TCPClient{
	public static void main(String[] args){
		try{
			Socket socket=new Socket("localhost",8888);
			DataInputStream inputStream=new DataInputStream(socket.getInputStream());
			DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
            String serverMessage="";
            //Sending data packet 1
            System.out.println("Sending data packet 1");
            TimeUnit.SECONDS.sleep(1);
            outputStream.writeUTF("dp1");
            outputStream.flush();
            serverMessage=inputStream.readUTF();
            System.out.println("server:"+serverMessage);
            
            //Sending data packet2
            System.out.println("\nSending data packet 2");
            TimeUnit.SECONDS.sleep(1);
            outputStream.writeUTF("dp2");
            outputStream.flush();
            serverMessage=inputStream.readUTF();
            System.out.println("server:"+serverMessage);

            //Sending data packet3
            System.out.println("\nSending data packet 3");
            TimeUnit.SECONDS.sleep(1);
            outputStream.writeUTF("dp3");
            outputStream.flush();
            serverMessage=inputStream.readUTF();
            System.out.println("server:"+serverMessage);
            
            //Sending data packet4
            System.out.println("\nSending data packet 2");
            TimeUnit.SECONDS.sleep(1);
            outputStream.writeUTF("dp4");
            outputStream.flush();
            serverMessage=inputStream.readUTF();
            System.out.println("server:"+serverMessage);

            //Sending bye
            System.out.println("\n\nTramission Successful. Closing connection...");
            TimeUnit.SECONDS.sleep(1);
            outputStream.writeUTF("EOF");
            outputStream.flush();
            serverMessage=inputStream.readUTF();
            System.out.println("server:"+serverMessage);

			outputStream.close();
			inputStream.close();
			socket.close();
			
		}catch(Exception e){
			System.out.println("Exception in connecting client to server: "+e.toString());
		}
	}
}