import java.io.*;
import java.net.*;

public class Server{
    public static void main(String[] args){
        ServerSocket server=null;

        try{
            server=new ServerSocket(9000);
            System.out.println("Server started at localhost:9000");
        }catch(Exception e){
            System.out.println("Error in starting chat server: "+e.toString());
        }

        while(true){
            try{
                Socket clientSocket=server.accept();
                System.out.println("\n-------------------\n\nClient connected\n");
                DataInputStream inputStream=new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream=new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    
                outputStream.writeUTF("Hello Client");
                String clientMessage=inputStream.readUTF();
                while(!clientMessage.equals("bye")){
                    System.out.println("Message from client: "+clientMessage);
                    outputStream.writeUTF("ACK");
                    clientMessage=inputStream.readUTF();
                }
                System.out.println("\nClient disconnected\n\n--------------------");
                inputStream.close();
                outputStream.close();
            }catch(Exception e){
                System.out.println("Error in communication with client: "+e.toString());
            }
            
        }
    }
}