package clientbattaglianavale;

import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientBattagliaNavale {
    
    Socket socket;
    Scanner in;
    PrintWriter out;
    
    public ClientBattagliaNavale () throws IOException  {
        
        socket = new Socket("127.0.0.1", 42069);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
}
    
    
    private void interpreta() {
     
        if (in.hasNextLine()) {
            String testo = in.nextLine();
            if (testo) {
                
            }
        }
    }  
    
    private void gioca() {
        this.interpreta();
    }
    
    public static void main(String[] args) throws IOException {
       
        ClientBattagliaNavale client = new ClientBattagliaNavale();
        client.gioca();
    }
    
}
