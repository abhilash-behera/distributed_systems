import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) {
        // Step 1 : Create a socket to listen at port 1234
        DatagramSocket ds=null;
        try{
            ds = new DatagramSocket(9000);
        }catch(Exception e){
            System.out.println("Error in creating server: "+e.toString());
        }
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive = null;
        while (true) {

            // Step 2 : create a DatgramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);

            // Step 3 : revieve the data in byte buffer.
            try {
                ds.receive(DpReceive);
            } catch (Exception e) {
                System.out.println("Error in receiving message: " + e.toString());
            }

            System.out.println("Client:-" + data(receive));

            // Exit the server if the client sends "bye"
            if (data(receive).toString().equals("bye")) {
                System.out.println("Client sent bye.....EXITING");
                ds.close();
                break;
            }

            // Clear the buffer after every message.
            receive = new byte[65535];
        }
    }

    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}