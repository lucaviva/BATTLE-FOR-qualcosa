package clientbattaglianavale;

import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientBattagliaNavale {

    
    
    public ClientBattagliaNavale ()  {
    
    
}
    
    
    
    
    
    public static void main(String[] args) throws IOException {
        
     
  
        
        try (Socket socket = new Socket("127.0.0.1", 42069)) {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                if(in.hasNextLine()) {
                System.out.println(in.nextLine());
                break;
                }
            }
        }
    }
    
}
