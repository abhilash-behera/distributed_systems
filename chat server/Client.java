import java.io.*;
import java.net.*;

public class Client{
    public static void main(String[] args){
        Socket clientSocket=null;
        try{
            clientSocket=new Socket("localhost",9000);
            System.out.println("Successfully connected to server at localhost:9000");
        }catch(Exception e){
            System.out.println("Error in connecting to server: "+e.toString());
        }

        try{
            DataInputStream dataInputStream=new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream=new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

            String clientMessage="";
            String serverMessage="";
            while(!clientMessage.equals("bye")){
                serverMessage=dataInputStream.readUTF();
                System.out.println("Message from server: "+serverMessage);
                clientMessage=bufferedReader.readLine();
                dataOutputStream.writeUTF(clientMessage);
            }

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        }catch(Exception e){
            System.out.println("Error in communication with server: "+e.toString());
        }
    }
}