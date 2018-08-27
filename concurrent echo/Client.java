import java.net.*;
import java.io.*;
import java.lang.*;

public class Client{
	public static void main(String[] args){
		try{
			Socket socket=new Socket("localhost",8888);
			DataInputStream inputStream=new DataInputStream(socket.getInputStream());
			DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String clientMessage="";
			String serverMessage="";
			while(!clientMessage.equals("bye")){
				System.out.println("Enter your message: ");
				clientMessage=br.readLine();
				outputStream.writeUTF(clientMessage);
				outputStream.flush();
				serverMessage=inputStream.readUTF();
				System.out.println(serverMessage);
			}

			outputStream.close();
			inputStream.close();
			socket.close();
			
		}catch(Exception e){
			System.out.println("Exception in connecting client to server: "+e.toString());
		}
	}
}
