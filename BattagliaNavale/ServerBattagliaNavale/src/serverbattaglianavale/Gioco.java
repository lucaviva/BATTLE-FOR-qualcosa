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
    
    private void inserisciBarche() {
        output.println("Inizio inserimento barche (inserire coordinata di inizio e orientamento)");
        output.println("Inserisci barca da 5");
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
