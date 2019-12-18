package clientbattaglianavale;

import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientBattagliaNavale {
    
    Socket socket;
    Scanner in;
    Scanner tastiera;
    PrintWriter out;
    
    public ClientBattagliaNavale () throws IOException  {
        
        socket = new Socket("127.0.0.1", 42069);
        tastiera = new Scanner(System.in);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
}
    
    
    private void interpreta() {
     
        if (in.hasNextLine()) {
            String testo = in.nextLine();
            if (testo.startsWith("INS"))
                inserimentoBarca(testo.charAt(4));
        }
    }  
    
    private void inserimentoBarca(char ins) {
        if (ins == 'S') {
            System.out.println("Inserire coordinate e orientamento barca dell'inizio della barca(se orizzontale si estende a destra e in verticale verso il basso)(le coordinate vaanno scritte in lettere es: 1 = a, 2 = b, etc..) es. barca gto (x = g = 7, y = t = 20, o = orizzontale)");
   
        }
        if (ins == 'E') {
            System.out.println("Errore nel valore inserito");
            this.inserimentoBarca('S');
        }
        if (ins == '5') {
            System.out.println("Inserire barca da 5 caselle");
            out.println(tastiera.nextLine());
        }
        if (ins == '4') {
            System.out.println("Inserire barca da 4 caselle");
            out.println(tastiera.nextLine());
        }
        if (ins == '3') {
            System.out.println("Inserire barca da 3 caselle");
            out.println(tastiera.nextLine());
        }
        if (ins == '2') {
            System.out.println("Inserire barca da 2 caselle");
            out.println(tastiera.nextLine());
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
