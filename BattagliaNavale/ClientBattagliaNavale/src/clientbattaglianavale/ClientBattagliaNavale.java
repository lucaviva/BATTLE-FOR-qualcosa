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
    Boolean finePartita = false;
    
    public ClientBattagliaNavale (String ip, int porta) throws IOException  {
        
        socket = new Socket(ip, porta);
        
        tastiera = new Scanner(System.in);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }
    
    
    private void interpreta() {

        String testo = in.nextLine();
        if (testo.startsWith("G2M")) {
            System.out.println("In attesa del secondo giocatore");
        }
        else if (testo.startsWith("G2C")) {
            System.out.println("Giocatore 2 collegato");
            out.println("G1P");
        }
        else if (testo.startsWith("INS")) {
            inserimentoBarca(testo.charAt(3));
            return;
        }
        else if (testo.startsWith("FIR")) {
            fuoco(testo.charAt(3));
            return;
        }
        else if (testo.startsWith("WIN") || testo.startsWith("LOS")) {
            finePartita = true;
            return;
        }
        else if (testo.startsWith("COL")) {
            colpoRicevuto(testo);
            return;
        }
        else if (testo.startsWith("GRI")) {
            System.out.println(testo.substring(3));
        }
        else
            System.out.println(testo);
        
    }  
    
    private void inserimentoBarca(char ins) {
        if (ins == 'S') {
            System.out.println("Inserire coordinate e orientamento barca dell'inizio della barca(se orizzontale si estende a destra e in verticale verso il basso)(le coordinate vaanno scritte in lettere es: 1 = a, 2 = b ... 21 = u) es. barca gto (x = g = 7, y = t = 20, o = orizzontale)");
   
        }
        if (ins == 'E') {
            System.out.println("Errore nel valore inserito \n");
        }
        else if (ins == '5') {
            System.out.println("Inserire barca da 5 caselle");
            out.println(tastiera.nextLine());
        }
        else if (ins == '4') {
            System.out.println("Inserire barca da 4 caselle");
            out.println(tastiera.nextLine());
        }
        else if (ins == '3') {
            System.out.println("Inserire barca da 3 caselle");
            out.println(tastiera.nextLine());
        }
        else if (ins == '2') {
            System.out.println("Inserire barca da 2 caselle");
            out.println(tastiera.nextLine());
        }
        else if (ins == 'F') {
            System.out.println("Inserimento completato, in attesa dell'altro giocatore");
        }
    } 
    
    private void fuoco(char ins) {
        if (ins == 'O') {
            System.out.println("Inserire coordinate da colpire (le coordinate vaanno scritte in lettere es: 1 = a, 2 = b, etc..)");
            out.println(tastiera.nextLine());
        }
        else if (ins == 'E') {
            System.out.println("Errore nel valore inserito \n");
            this.fuoco('O');
        }
        else if (ins == 'A') {
            System.out.println("Colpo non andato a segno. Acqua!");
        }
        else if (ins == 'C') {
            System.out.println("Colpo andato a segno. Colpito!");
        }
        else if (ins == 'R') {
            System.out.println("Fatto fuoco su una casella già colpita");
        }
        else if(ins == 'M')
            System.out.println("Complimenti hai distrutto una barca");
    }
    
    private void colpoRicevuto(String ins) {
        if (ins.charAt(3) == 'B') {
            System.out.println("Una tua barca e' stata colpita in x: " + (Integer.valueOf(ins.substring(5, 6))-10) + " y:" + (Integer.valueOf(ins.substring(7, 8))-10));
        }
        else if (ins.charAt(3) == 'A') {
            System.out.println("L'avversario ha colpitol'acqua in x: " + (Integer.valueOf(ins.substring(5, 6))-10) + " y:" + (Integer.valueOf(ins.substring(7, 8))-10));
        }
    }
    
    private void gioca() {
        int valore;
        String statoConnesione;
        
        do {
            valore = in.toString().indexOf("source closed=");
            statoConnesione =in.toString().substring(valore + 14, valore + 19);
            if (!statoConnesione.equals("false")) {
                System.out.println("Server disconnesso");
                return;
            }
            if (in.hasNextLine()) {
                this.interpreta();
            }
        } while (!finePartita );
    }
    
    public static void main(String[] args) throws IOException {
        int porta;
        String ip;
        Scanner input = new Scanner(System.in);
        System.out.println("Inserire l'indirizzo ip con il quale creare si vuole creare connessione: ");
        ip = input.nextLine();
        System.out.println("Inserire porta con la quale si vuole creare la connessione: ");
        porta = input.nextInt();
        ClientBattagliaNavale client = new ClientBattagliaNavale(ip, porta);
        client.gioca();
        
    }
    
}
