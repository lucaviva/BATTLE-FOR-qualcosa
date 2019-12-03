/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbattaglianavale;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author informatica
 */
public class Giocatore implements Runnable{
    Scanner input;
    PrintWriter output;
    Socket socket;
    private ArrayList<Barca> barche;

    public Giocatore(Socket socket) {
        this.socket = socket;
        this.barche = new ArrayList();
    }
    
    
    
    @Override
    public void run() {
        
    }
    
    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream());
    }
    
}
