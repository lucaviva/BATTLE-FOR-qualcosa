/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbattaglianavale;

/**
 *
 * @author informatica
 */
public class Barca {
    private int lunghezza;
    private Coordinata inizio;
    private Coordinata fine;
    private char Orientamento;
    private int valore;

    public Barca(int lunghezza, Coordinata inizio, char Orientamento) {
        this.lunghezza = lunghezza;
        this.inizio = inizio;
        this.Orientamento = Orientamento;
        this.valore = lunghezza;
    }

    Barca() {
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public void setLunghezza(int lunghezza) {
        this.lunghezza = lunghezza;
        this.valore = lunghezza;
    }

    public Coordinata getInizio() {
        return inizio;
    }

    public void setInizio(Coordinata inizio) {
        this.inizio = inizio;
    }

    public Coordinata getFine() {
        return fine;
    }

    public void setFine(Coordinata fine) {
        this.fine = fine;
    }

    public char getOrientamento() {
        return Orientamento;
    }

    public void setOrientamento(char Orientamento) {
        this.Orientamento = Orientamento;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }
    
    
    
}
