package concept.CentralAutomacao.socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EnviaSocket {
	 private static Socket socket;
	 static int i =1;
	 
	  public static void main(String args[])
	    {
		//  while (i!=0){
	        try
	        {
	            String host = "192.168.2.201";
	            int port = 82;
	            InetAddress address = InetAddress.getByName(host);
	            socket = new Socket(address, port);
	 
	            //Send the message to the server
	            OutputStream os = socket.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os);
	            BufferedWriter bw = new BufferedWriter(osw);
	 
	            String number = "000L#R#";
	 
	            String sendMessage = "C=90;N=01;F=A0#";
	            bw.write(sendMessage);
	            bw.flush();
	            System.out.println("Message sent to the server : "+sendMessage);
	        }
	        catch (Exception exception) 
	        {
	            exception.printStackTrace();
	        }
	        finally
	        {
	            //Closing the socket
	            try
	            {
	                socket.close();
	            }
	            catch(Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	// }
}
