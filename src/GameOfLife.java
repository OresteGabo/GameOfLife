import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife {
    private Grille grille;
    private JPanel panel;
    private JButton play;
    private JButton restart;

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JButton getPlay() {
        return play;
    }

    public void setPlay(JButton play) {
        this.play = play;
    }

    public JButton getRestart() {
        return restart;
    }

    public void setRestart(JButton restart) {
        this.restart = restart;
    }

    public GameOfLife(Grille grille) {
        this.grille = grille;
        init();
    }
    public GameOfLife(int lc, int nbVies) {
        new GameOfLife(lc,lc,nbVies);
    }
    public GameOfLife(int l, int c,int nbVies) {
        this.grille=new Grille(l,c,nbVies);
        init();
    }
    private void init(){
        this.panel = new JPanel(new GridLayout(grille.getNbCol(),grille.getNbCol()));
        this.play = new JButton("play");
        this.restart = new JButton("restart");

    }

}
