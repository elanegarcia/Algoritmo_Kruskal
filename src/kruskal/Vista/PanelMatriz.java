/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal.Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logica.ParejasAristas;

/**
 *
 * @author Usuario
 */
public class PanelMatriz extends JPanel {

    int x = 0, y = 45, numPuntos = 0;
    private ArrayList<ParejasAristas> parejasDeAristas = new ArrayList<>();
    JLabel lblTituloMatriz, lblTituloMatriz2;
    JTextField[] jTextField;
    String[][] matriz;

    public PanelMatriz() {
        setLayout(null);
        setPreferredSize(new Dimension(800, 520));
        setVisible(true);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        lblTituloMatriz = new JLabel();
        lblTituloMatriz.setBounds(0, 0, 400, 30);
        lblTituloMatriz.setText("<html>99 = Costo de una arista para vertices no adjuntos<br>X indica que la matriz es simétrica</html>");
        add(lblTituloMatriz);
    }

    public void generarMatriz(int numPuntos) {
        this.numPuntos = numPuntos;

        jTextField = new JTextField[(numPuntos + 2) * (numPuntos * 2)];
        matriz = new String[numPuntos + 1][numPuntos + 1];
        for (int i = 0; i <= numPuntos; i++) {
            for (int j = 0; j <= numPuntos; j++) {
                if (i == 0) {
                    matriz[i][j] = j + "";
                } else if (j == 0) {
                    matriz[i][j] = i + "";
                } else if (i == j) {
                    matriz[i][j] = 0 + "";
                } else if (j > i) {
                    matriz[i][j] = 99 + "";
                } else {
                    matriz[i][j] = "X";
                }
            }
        }
        for (int i = 0; i <= numPuntos; i++) {
            for (int j = 0; j <= numPuntos; j++) {
                System.out.print(matriz[i][j] + "-");
            }
            System.out.println("");
        }
        generarComponentes();
    }

    private void generarComponentes() {
        lblTituloMatriz2 = new JLabel();
        lblTituloMatriz2.setBounds(60, 40, 400, 30);
        lblTituloMatriz2.setText("Matriz de adyacencia");
        lblTituloMatriz2.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lblTituloMatriz2);
        int k = 0;
        for (int i = 0; i <= numPuntos; i++) {
            for (int j = 0; j <= numPuntos; j++) {
                crearJTextFields(i, j, k);
                k++;
            }
        }
    }

    private void crearJTextFields(int i, int j, int k) {
        jTextField[k] = new JTextField(matriz[i][j]);
        if (k != 0) {
            jTextField[k].setBounds(x + ((j + 1) * 60), y + ((i + 1) * 35), 50, 30);
        }
        if (i == 0 || j == 0) {
            jTextField[k].setBackground(new Color(51, 255, 153));
        }

        add(jTextField[k]);
        this.repaint();
    }

    public void leerMatriz() {
        int l = 0;
        for (int i = 0; i <= numPuntos; i++) {
            for (int j = 0; j <= numPuntos; j++) {
                matriz[i][j] = jTextField[l].getText();
                l++;
            }
        }

        for (int i = 0; i <= numPuntos; i++) {
            for (int j = 0; j <= numPuntos; j++) {
                System.out.print(matriz[i][j] + "-");
            }
            System.out.println("");
        }

        parejasAristas();
    }

    private void parejasAristas() {
        int l = 0;
        for (int i = 0; i <= numPuntos; i++) {
            for (int j = 0; j <= numPuntos; j++) {
                if (i != 0 && j != 0) {
                    if (!matriz[i][j].equals("X") && !matriz[i][j].equals("99") && !matriz[i][j].equals("0")) {
                        parejasDeAristas.add(new ParejasAristas(i, j, Integer.parseInt(matriz[i][j])));

                    }
                }
            }
        }

        System.out.println("Parejas de aristas:");
        System.out.println(parejasDeAristas);
        parejasDeAristas.forEach((pa) -> {
            System.out.println("Arista1:" + pa.getArista1());
            System.out.println("Arista2:" + pa.getArista2());
            System.out.println("Peso:" + pa.getPeso());
        });
    }

    public ArrayList<ParejasAristas> getParejasDeAristas() {
        return parejasDeAristas;
    }

}
