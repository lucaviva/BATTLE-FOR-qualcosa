/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbattaglianavale;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javax.swing.JFrame;
import javax.swing.JLabel;
import serverbattaglianavale.Gioco.Giocatore;

/**
 *
 * @author informatica
 */
public class ServerBattagliaNavale {


    
    public static void main(String[] args) throws Exception{
        System.out.println("Inserire porta con la quale creare la connessione: ");
        Scanner input = new Scanner(System.in);
        int porta ;
        porta = input.nextInt();    
        try(ServerSocket listener = new ServerSocket(porta)) {
            System.out.println("Il server e' attivo");
            ExecutorService pool = Executors.newFixedThreadPool(200);
            Gioco game = new Gioco();
            Giocatore g1 = game.new Giocatore(listener.accept(), "giocatore 1");
            pool.execute(g1);
            Giocatore g2 = game.new Giocatore(listener.accept(), "giocatore 2");
            pool.execute(g2);
            

        }
           
    }
    
   
    
}
