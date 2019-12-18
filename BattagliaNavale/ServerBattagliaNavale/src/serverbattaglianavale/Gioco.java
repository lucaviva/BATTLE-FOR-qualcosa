/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbattaglianavale;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
/**
 *
 * @author informatica
 */
public class Gioco {

    public Gioco() {
    
        
    }
    
    
    
    class Giocatore implements Runnable{
    Giocatore avversario;
    Scanner input;
    PrintWriter output;
    Socket socket;
    String nome;
    private ArrayList<Barca> barche;
    private int[][] griglia = new int[21][21];

    public Giocatore(Socket socket, String nome) {
        this.nome = nome;
        this.socket = socket;
        this.barche = new ArrayList();
        initGriglia();
        
    }
    
    private void initGriglia() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                griglia[i][j] = 0;
            }
            
        }
    }
    //AGGIUNGERE CONTROLLO DEI CONFLITTI TRA LE BARCHE
    private Barca interpretaIns(String testo) {
        Barca b = new Barca();
        if(testo != null || testo.length() == 3) {
            //segmento la Stringa in input
            testo = testo.toLowerCase();
            int x = Character.getNumericValue(testo.charAt(0));
            int y = Character.getNumericValue(testo.charAt(1));
            char orient = testo.charAt(2);
            if ( x >= 10 && x <= 30 ) //controllo che il valore di x sia una lettera dell'alfabeto
                if ( y >= 10 && y <= 30 ) //controllo che il valore di y sia una lettera dell'alfabeto
                    if (orient == 'o' || orient == 'v') //controllo che orient sia o (orizzontale) oopure v (verticale)
                    {
                        b.setInizio(new Coordinata(Character.getNumericValue(x) - 9, Character.getNumericValue(y) - 9));
                        b.setOrientamento(orient);
                        if (orient == 'o') //calcolo la coordinata di fine della barca
                            b.setFine(new Coordinata(Character.getNumericValue(x) - 9 + b.getLunghezza(), Character.getNumericValue(y) - 9));
                        else
                            b.setFine(new Coordinata(Character.getNumericValue(x) - 9, Character.getNumericValue(y) - 9 + b.getLunghezza()));
                    }
            else
                     output.println("Errore nell'inserimento dei dati della barca");
        }
         return b;
    }
    
    private void inserisciBarche() {
        output.println("INSS"); //prefisso INS sta per inserimento, S sta per "inizio inserimento"
        output.println("INS5"); //prefisso INS sta per inserimento, 5 sta per "inserisci barca da 5"
        barche.add(interpretaIns(input.nextLine()));
        output.println("INS4"); //prefisso INS sta per inserimento, 4 sta per "inserisci barca da 4"
        barche.add(interpretaIns(input.nextLine()));
        for (int i = 0; i < 2; i++) { //ci sono 2 barche da 3
            output.println("INS3"); //prefisso INS sta per inserimento, 3 sta per "inserisci barca da 3"
            barche.add(interpretaIns(input.nextLine()));
        }
        for (int i = 0; i < 3; i++) { //ci sono 3 barche da 2
            output.println("INS2"); //prefisso INS sta per inserimento, 2 sta per "inserisci barca da 2"
            barche.add(interpretaIns(input.nextLine()));
        }
        
        
    }
    
    @Override
    public void run() {
        try {
                setup();
            } catch (Exception e) {
                System.out.println(e.getMessage());;
            } finally {
                
                try {socket.close();} catch (IOException e) {}
            }
    }
    
    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("Benvenuto " + nome);
    }
    }
}
