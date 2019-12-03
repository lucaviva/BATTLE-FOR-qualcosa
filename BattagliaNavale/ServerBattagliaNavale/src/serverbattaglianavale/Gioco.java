/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbattaglianavale;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author informatica
 */
public class Gioco {
    
    JFrame f;
    JLabel label1;

    

    public Gioco() {
    f = new JFrame("Battaglia Navale");
    label1 = new JLabel("Ciao", JLabel.CENTER);
    f.add(label1);
    f.setSize(300, 300);
    f.setVisible(true);
    }
    
}
