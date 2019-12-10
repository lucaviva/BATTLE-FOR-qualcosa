/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbattaglianavale;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author informatica
 */
public class ServerBattagliaNavale {

    
    
    
    public static void main(String[] args) throws Exception{
        
        try(ServerSocket listener = new ServerSocket(42069)) {
            System.out.println("Il server e' attivo");
            ExecutorService pool = Executors.newFixedThreadPool(200);
            
            
            while(true) {
                Gioco game = new Gioco();
                pool.execute(game.new Giocatore(listener.accept(), "giocatore 1"));
                pool.execute(game.new Giocatore(listener.accept(), "giocatore 2"));
            }

        }
           
    }
    
   
    
}
