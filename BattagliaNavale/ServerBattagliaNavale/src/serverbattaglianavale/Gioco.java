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
    
    private boolean controlloIns(Barca barca) {
        if (barche.isEmpty())
            return true; //true equivale a inserimento corretto
        int x = barca.getInizio().getX();
        int y = barca.getInizio().getY();
                
        if (x == 0 && y == 0) { //angolo Alto-Sinistra
            if (griglia[x][y] == 1
                || griglia[x + 1][y + 1] == 1
                || griglia[x + 1][y] == 1
                || griglia[x][y + 1] == 1)
                return false;
            if (barca.getOrientamento() == 'o')
                x++;
            else
                y++;
        }
            
        
        if (x == 20 && y == 0) { //angolo Alto-Destra
            if (griglia[x][y] == 1
                || griglia[x - 1][y] == 1
                || griglia[x - 1][y + 1] == 1
                || griglia[x][y + 1] == 1)
                return false;
            y++;
        }
        
        if (x == 0 && y == 20) { //angolo Basso-Sinistra
            if (griglia[x][y] == 1
                || griglia[x][y - 1] == 1
                || griglia[x + 1][y - 1] == 1
                || griglia[x + 1][y] == 1)
                return false;
            x++;
        }
        
        
        if (x == barca.getInizio().getX() && y == barca.getInizio().getY()) { //controllo inizio generico
            if (griglia[x][y] == 1 
                || griglia[x + 1][y + 1] == 1
                || griglia[x - 1][y - 1] == 1
                || griglia[x + 1][y] == 1
                || griglia[x][y + 1] == 1
                || griglia[x - 1][y] == 1
                || griglia[x][y - 1] == 1)
                return false;
            if (barca.getOrientamento() == 'o')
                x++;
            else
                y++;
        }
                    
        if (barca.getOrientamento() == 'o') { //ciclo per barche orizzontali
            while ( x <= barca.getFine().getX()) {
                if (x == 20 && y == 20) { //angolo Basso-Destra
                    if (griglia[x][y] == 1
                        || griglia[x -1][y] == 1
                        || griglia[x - 1][y - 1] == 1
                        || griglia[x][y - 1] == 1)
                        return false;
                }
                if (x == 20 && y == 0) { //angolo Alto-Destra
                    if (griglia[x][y] == 1
                        || griglia[x - 1][y] == 1
                        || griglia[x - 1][y + 1] == 1
                        || griglia[x][y + 1] == 1)
                        return false;
                }
                if (x == 0 && y == 20) { //angolo Basso-Sinistra
                    if (griglia[x][y] == 1
                        || griglia[x][y - 1] == 1
                        || griglia[x + 1][y - 1] == 1
                        || griglia[x + 1][y] == 1)
                        return false;
                }
                if (griglia[x][y] == 1
                    || griglia[x + 1][y + 1] == 1
                    || griglia[x - 1][y - 1] == 1
                    || griglia[x + 1][y] == 1
                    || griglia[x][y + 1] == 1
                    || griglia[x - 1][y] == 1
                    || griglia[x][y - 1] == 1)
                    return false;
                x++;
            }
        } else
            while ( y <= barca.getFine().getY()) { //ciclo per barche verticali
                if (x == 20 && y == 20) { //angolo Basso-Destra
                    if (griglia[x][y] == 1
                        || griglia[x -1][y] == 1
                        || griglia[x - 1][y - 1] == 1
                        || griglia[x][y - 1] == 1)
                        return false;
                }
                if (x == 20 && y == 0) { //angolo Alto-Destra
                    if (griglia[x][y] == 1
                        || griglia[x - 1][y] == 1
                        || griglia[x - 1][y + 1] == 1
                        || griglia[x][y + 1] == 1)
                        return false;
                }
                if (x == 0 && y == 20) { //angolo Basso-Sinistra
                    if (griglia[x][y] == 1
                        || griglia[x][y - 1] == 1
                        || griglia[x + 1][y - 1] == 1
                        || griglia[x + 1][y] == 1)
                        return false;
                }
                if (griglia[x][y] == 1
                    || griglia[x + 1][y + 1] == 1
                    || griglia[x - 1][y - 1] == 1
                    || griglia[x + 1][y] == 1
                    || griglia[x][y + 1] == 1
                    || griglia[x - 1][y] == 1
                    || griglia[x][y - 1] == 1)
                    return false;
                y++;
            }
        return true;
    }
    
    
    private Barca interpretaIns(String testo) {
        Barca b = new Barca();
        if(testo != null || testo.length() == 3) {
            //segmento la Stringa in input
            testo = testo.toLowerCase();
            int x = Character.getNumericValue(testo.charAt(0));
            int y = Character.getNumericValue(testo.charAt(1));
            char orient = testo.charAt(2);
            if ( x >= 10 && x <= 30 ){ //controllo che il valore di x sia una lettera dell'alfabeto. a = 0 -> u = 21
                if ( y >= 10 && y <= 30 ) //controllo che il valore di y sia una lettera dell'alfabeto. a = 0 -> u = 21
                    if (orient == 'o' || orient == 'v') //controllo che orient sia o (orizzontale) oopure v (verticale)
                    {
                        b.setInizio(new Coordinata(Character.getNumericValue(x) - 10, Character.getNumericValue(y) - 10));
                        b.setOrientamento(orient);
                        if (orient == 'o') //calcolo la coordinata di fine della barca
                            b.setFine(new Coordinata(Character.getNumericValue(x) - 10 + b.getLunghezza(), Character.getNumericValue(y) - 10));
                        else
                            b.setFine(new Coordinata(Character.getNumericValue(x) - 10, Character.getNumericValue(y) - 10 + b.getLunghezza()));
                    }
                else
                    output.println("INSE"); //prefisso INS sta per inserimento, E sta per "errore nell'inserimento"
                else
                    output.println("INSE"); //prefisso INS sta per inserimento, E sta per "errore nell'inserimento"
            }   else
                    output.println("INSE"); //prefisso INS sta per inserimento, E sta per "errore nell'inserimento"
        }
         return b;
    }
    
    private void inserisciBarche() {
        Barca temp = new Barca();
        output.println("INSS"); //prefisso INS sta per inserimento, S sta per "inizio inserimento"
        do {
        output.println("INS5"); //prefisso INS sta per inserimento, 5 sta per "inserisci barca da 5"
        temp = interpretaIns(input.nextLine());
        } while(controlloIns(temp) == false); //Ripete finche il controllo non va a buon fine
        
        barche.add(temp);
        do{
        output.println("INS4"); //prefisso INS sta per inserimento, 4 sta per "inserisci barca da 4"
        temp = interpretaIns(input.nextLine());
        } while (controlloIns(temp) == false); //Ripete finche il controllo non va a buon fine
        
        barche.add(temp);
        for (int i = 0; i < 2; i++) { //ci sono 2 barche da 3
            do {
            output.println("INS3"); //prefisso INS sta per inserimento, 3 sta per "inserisci barca da 3"
            temp = interpretaIns(input.nextLine());
            } while(controlloIns(temp) == false); //Ripete finche il controllo non va a buon fine
            barche.add(temp);
        }
        for (int i = 0; i < 3; i++) { //ci sono 3 barche da 2
            do {
            output.println("INS2"); //prefisso INS sta per inserimento, 2 sta per "inserisci barca da 2"
            temp = interpretaIns(input.nextLine());
            }while(controlloIns(temp) == false); //Ripete finche il controllo non va a buon fine
            barche.add(temp);
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
