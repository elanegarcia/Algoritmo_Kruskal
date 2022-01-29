/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal.Vista;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Usuario
 */
public class PanelPuntos extends JPanel implements MouseListener{
    private Puntos[] puntos;
    private int numPuntos = 0;
    private JLabel[] lblPuntos;
    public PanelPuntos(){
        lblPuntos = new JLabel[12];
        puntos = new Puntos[12];
        setLayout(null);
        addMouseListener(this);
        setBounds(12,97,614,323);
        setBorder(new LineBorder(Color.BLACK));
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
        if(numPuntos<10){
            numPuntos++;
            generarPunto(e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public int getNumPuntos() {
        return numPuntos;
    }
    
    private void generarPunto(int x, int y){
        puntos[numPuntos-1] = new Puntos(x,y);
        lblPuntos[numPuntos-1] = new JLabel(numPuntos+"");
        lblPuntos[numPuntos-1].setBounds(x, y, 14, 14);
        add(lblPuntos[numPuntos-1]);
        this.repaint();
    }

    public Puntos[] getPuntos() {
        return puntos;
    }
}
