/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal.Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import logica.ParejasAristas;
import logica.colaDePrioridad.ColaDePrioridad;
import logica.colaDePrioridad.Monticulo;

/**
 *
 * @author Usuario
 */
public class PanelResultados extends JPanel {

    private ArrayList<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();
    private HashSet setAristas = new HashSet();
    private Monticulo monticulo;
    private Puntos[] puntos;
    private JLabel[] pesoCamino = new JLabel[30];
    private JLabel[] pesoCamino2 = new JLabel[30];
    private JLabel[] aristas = new JLabel[12];
    private JLabel[] aristas2 = new JLabel[12];
    private int tamanio, k;
    private JLabel[] nodos;
    private boolean graficarLineas = false;
    private boolean dibujarLineasGrafo = false;
    private ParejasAristas[] parejasLineas = new ParejasAristas[30];
    private ArrayList<ParejasAristas> parejasDeAristas = new ArrayList<>();
    private ArrayList<ParejasAristas> parejasSolucion = new ArrayList<>();
    private ArrayList<ParejasAristas> parejasDeAristasInsertadas = new ArrayList<>();

    public PanelResultados() {
        for (int h = 0; h < parejasLineas.length; h++) {
            parejasLineas[h] = null;
        }
        setLayout(null);
        setBounds(12, 558, 1082, 1000);
        setBorder(new LineBorder(Color.BLACK));
        setVisible(true);
    }

    public void colaPrioridad() {
        ColaDePrioridad cp = new ColaDePrioridad(100);
        parejasDeAristas.forEach((pa) -> {
            cp.insertar(pa);
        });
        monticulo = cp.getMonticulo();
        tamanio = cp.getTamanio();

        nodos = new JLabel[monticulo.getTamanio()];
        int x = 521, y = 10, j = 0, aumento = 400;
        this.k = 0;
        int variableParejas = 0;
        for (int i = 1; i <= monticulo.getTamanio() / 2; i++) {
            if (i == 1) {
                agregarNodoArbol(monticulo.getNodo()[i], j, x, y);
                monticulo.getNodo()[i].setX(x);
                monticulo.getNodo()[i].setY(y);
                parejasDeAristasInsertadas.add(monticulo.getNodo()[i]);
                j++;
                aumento = verificarAumento(j, aumento);

            } else if (!parejasDeAristasInsertadas.contains(monticulo.getNodo()[i])) {
            }

            if (monticulo.tieneHijoIzquierdo(i)) {
                agregarNodoArbol(monticulo.getNodo()[2 * i], j, monticulo.getNodo()[i].getX() - aumento, monticulo.getNodo()[i].getY() + 50);
                monticulo.getNodo()[2 * i].setX(monticulo.getNodo()[i].getX() - aumento);
                monticulo.getNodo()[2 * i].setY(monticulo.getNodo()[i].getY() + 50);
                parejasDeAristasInsertadas.add(monticulo.getNodo()[2 * i]);
                parejasLineas[variableParejas] = monticulo.getNodo()[i];
                parejasLineas[variableParejas + 1] = monticulo.getNodo()[2 * i];
                variableParejas += 2;
                j++;
                aumento = verificarAumento(j, aumento);
            }
            if (monticulo.tieneHijoDerecho(i)) {
                agregarNodoArbol(monticulo.getNodo()[2 * i + 1], j, monticulo.getNodo()[i].getX() + aumento, monticulo.getNodo()[i].getY() + 50);
                monticulo.getNodo()[2 * i + 1].setX(monticulo.getNodo()[i].getX() + aumento);
                monticulo.getNodo()[2 * i + 1].setY(monticulo.getNodo()[i].getY() + 50);
                parejasDeAristasInsertadas.add(monticulo.getNodo()[2 * i + 1]);
                parejasLineas[variableParejas] = monticulo.getNodo()[i];
                parejasLineas[variableParejas + 1] = monticulo.getNodo()[2 * i + 1];
                variableParejas += 2;
                j++;
                aumento = verificarAumento(j, aumento);

            }
        }
        this.graficarLineas = true;
        obtenerSumaMin();

        int indx = 0;
        for (Puntos punto : puntos) {
            if (punto != null) {
                System.out.println(indx);
                HashSet e = new HashSet<Integer>();
                e.add(indx + 1);
                sets.add(e);
                indx++;
            }
        }
        System.out.println(sets);
        ParejasAristas paTemp = new ParejasAristas();
        int a = 0, b = 0, c = 0, ind = 0;
        while (sets.size() != 1) {

            paTemp = cp.eliminar();
            System.out.println(paTemp.getArista1());
            System.out.println(paTemp.getArista2());
            for (HashSet f : sets) {
                if (f.contains(paTemp.getArista1())) {
                    a = ind;
                    System.out.println("Primer if");
                }

                if (f.contains(paTemp.getArista2())) {
                    b = ind;
                    System.out.println("Segundo if");
                }
                ind++;
            }
            ind = 0;
            System.out.println("a:" + a + " b:" + b);
            if (a == b) {
                //parejasSolucion.add(paTemp);
                System.out.println("Iguales");
            } else {
                parejasSolucion.add(paTemp);
                sets.get(a).addAll(sets.get(b));
                sets.remove(b);
            }
            System.out.println(sets);
            System.out.println("a:" + a + " b:" + b);
            a = 0;
            b = 0;
        }
        System.out.println("paSol:" + parejasSolucion);
        dibujarGrafos();
    }

    public void setParejasDeAristas(ArrayList<ParejasAristas> parejasDeAristas) {
        this.parejasDeAristas = parejasDeAristas;
    }

    private int verificarAumento(int j, int aumento) {

        if (j == 2 * k + 1) {
            aumento /= 2;
            this.k = 2 * k + 1;
        }
        System.out.println("Aumento:" + aumento);
        return aumento;
    }

    private void agregarNodoArbol(ParejasAristas nodo, int j, int x, int y) {
        System.out.println(nodo.getPeso() + "Peso.");
        nodos[j] = new JLabel("<html><font color=\"red\">" + String.valueOf(nodo.getPeso()) + "</font><br>" + String.valueOf(nodo.getArista1()) + "<br>" + String.valueOf(nodo.getArista2()) + "</html>");
        nodos[j].setBounds(x, y, 20, 45);
        add(nodos[j]);
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (parejasLineas[0] == null) {
        } else {
            if (graficarLineas) {
                for (int i = 0; i < parejasLineas.length; i++) {
                    if (parejasLineas[i] != null && parejasLineas[i + 1] != null) {
                        g.drawLine(parejasLineas[i].getX(), parejasLineas[i].getY(), parejasLineas[i + 1].getX(), parejasLineas[i + 1].getY());
                    }
                    i++;
                }
            }
        }

        if (dibujarLineasGrafo) {
            for (ParejasAristas pda : parejasDeAristas) {
                g.drawLine(aristas[pda.getArista1() - 1].getX(), aristas[pda.getArista1() - 1].getY(), aristas[pda.getArista2() - 1].getX(), aristas[pda.getArista2() - 1].getY());
            }
            for (ParejasAristas pda : parejasSolucion) {
                System.out.println("Aca deberia");
                g.drawLine(aristas2[pda.getArista1() - 1].getX(), aristas2[pda.getArista1() - 1].getY(), aristas2[pda.getArista2() - 1].getX(), aristas2[pda.getArista2() - 1].getY());
            }
        }

    }

    public void dibujarGrafos() {
        int x = 0, a = 0;
        for (Puntos punto : puntos) {
            if (punto != null) {
                aristas[x] = new JLabel(x + 1 + "");
                aristas[x].setBounds(punto.getPosX(), punto.getPosY() + 300, 20, 45);
                aristas2[x] = new JLabel(x + 1 + "");
                aristas2[x].setBounds(punto.getPosX() + 530, punto.getPosY() + 300, 20, 45);
                add(aristas[x]);
                add(aristas2[x]);
                this.repaint();
            }

            x++;
        }
        for (ParejasAristas pda : parejasDeAristas) {
            // Se verifique qué arita está abajo o arriba.
            pesoCamino[a] = new JLabel(pda.getPeso() + "");
            pesoCamino[a].setForeground(Color.red);
            System.out.println("");
            if (aristas[pda.getArista1() - 1].getX() == aristas[pda.getArista2() - 1].getX() && aristas[pda.getArista1() - 1].getY() < aristas[pda.getArista2() - 1].getY()) {
                pesoCamino[a].setBounds(pesoCamino[pda.getArista2() - 1].getX(), (pesoCamino[pda.getArista2() - 1].getY() - pesoCamino[pda.getArista1() - 1].getY()) / 2, 20, 45);
            } else if (aristas[pda.getArista1() - 1].getX() < aristas[pda.getArista2() - 1].getX() && aristas[pda.getArista1() - 1].getY() > aristas[pda.getArista2() - 1].getY()) {
                pesoCamino[a].setBounds(((aristas[pda.getArista2() - 1].getX() - aristas[pda.getArista1() - 1].getX()) / 2) + aristas[pda.getArista1() - 1].getX(), ((aristas[pda.getArista1() - 1].getY() - aristas[pda.getArista2() - 1].getY()) / 2) + aristas[pda.getArista2() - 1].getY() - 15, 20, 45);
            } else if (aristas[pda.getArista1() - 1].getX() < aristas[pda.getArista2() - 1].getX() && aristas[pda.getArista1() - 1].getY() == aristas[pda.getArista2() - 1].getY()) {
                // 2
                pesoCamino[a].setBounds(((aristas[pda.getArista2() - 1].getX() - aristas[pda.getArista1() - 1].getX()) / 2) + aristas[pda.getArista1() - 1].getX(), aristas[pda.getArista1() - 1].getY(), 20, 45);
            } else if (aristas[pda.getArista1() - 1].getX() < aristas[pda.getArista2() - 1].getX() && aristas[pda.getArista1() - 1].getY() < aristas[pda.getArista2() - 1].getY()) {
                // 3
                pesoCamino[a].setBounds(((aristas[pda.getArista2() - 1].getX() - aristas[pda.getArista1() - 1].getX()) / 2) + aristas[pda.getArista1() - 1].getX(), ((aristas[pda.getArista2() - 1].getY() - aristas[pda.getArista1() - 1].getY()) / 2) + aristas[pda.getArista1() - 1].getY() - 5, 20, 45);
            } else if (aristas[pda.getArista1() - 1].getX() == aristas[pda.getArista2() - 1].getX() && aristas[pda.getArista1() - 1].getY() < aristas[pda.getArista2() - 1].getY()) {
                // 4
                pesoCamino[a].setBounds(aristas[pda.getArista1() - 1].getX() - 15, ((aristas[pda.getArista2() - 1].getY() - aristas[pda.getArista2() - 1].getY()) / 2) + aristas[pda.getArista1() - 1].getY(), 20, 45);
            } else if (aristas[pda.getArista1() - 1].getX() > aristas[pda.getArista2() - 1].getX() && aristas[pda.getArista1() - 1].getY() < aristas[pda.getArista2() - 1].getY()) {
                // 5
                pesoCamino[a].setBounds(((aristas[pda.getArista1() - 1].getX() - aristas[pda.getArista2() - 1].getX()) / 2) + aristas[pda.getArista2() - 1].getX(), ((aristas[pda.getArista2() - 1].getY() - aristas[pda.getArista1() - 1].getY()) / 2) + aristas[pda.getArista1() - 1].getY() - 10, 20, 45);
            } else if (aristas[pda.getArista1() - 1].getX() > aristas[pda.getArista2() - 1].getX() && aristas[pda.getArista1() - 1].getY() == aristas[pda.getArista2() - 1].getY()) {
                // 6
                pesoCamino[a].setBounds(((aristas[pda.getArista1() - 1].getX() - aristas[pda.getArista2() - 1].getX()) / 2) + aristas[pda.getArista2() - 1].getX(), aristas[pda.getArista2() - 1].getY() - 5, 20, 45);
            } else if (aristas[pda.getArista1() - 1].getX() > aristas[pda.getArista2() - 1].getX() && aristas[pda.getArista1() - 1].getY() > aristas[pda.getArista2() - 1].getY()) {
                // 7
                pesoCamino[a].setBounds(((aristas[pda.getArista1() - 1].getX() - aristas[pda.getArista2() - 1].getX()) / 2) + aristas[pda.getArista2() - 1].getX(), ((aristas[pda.getArista1() - 1].getY() - aristas[pda.getArista2() - 1].getY()) / 2) + aristas[pda.getArista2() - 1].getY() - 5, 20, 45);
            }

            add(pesoCamino[a]);
            this.repaint();
            a++;

        }
        a=0;
        int suma = 0;
        for (ParejasAristas pda : parejasSolucion) {
            // Se verifique qué arita está abajo o arriba.
            suma += pda.getPeso();
            pesoCamino2[a] = new JLabel(pda.getPeso() + "");
            pesoCamino2[a].setForeground(Color.red);
            System.out.println("");
            if (aristas2[pda.getArista1() - 1].getX() == aristas2[pda.getArista2() - 1].getX() && aristas2[pda.getArista1() - 1].getY() < aristas2[pda.getArista2() - 1].getY()) {
                pesoCamino2[a].setBounds(pesoCamino2[pda.getArista2() - 1].getX(), (pesoCamino2[pda.getArista2() - 1].getY() - pesoCamino2[pda.getArista1() - 1].getY()) / 2, 20, 45);
            } else if (aristas2[pda.getArista1() - 1].getX() < aristas2[pda.getArista2() - 1].getX() && aristas2[pda.getArista1() - 1].getY() > aristas2[pda.getArista2() - 1].getY()) {
                pesoCamino2[a].setBounds(((aristas2[pda.getArista2() - 1].getX() - aristas2[pda.getArista1() - 1].getX()) / 2) + aristas2[pda.getArista1() - 1].getX(), ((aristas2[pda.getArista1() - 1].getY() - aristas2[pda.getArista2() - 1].getY()) / 2) + aristas2[pda.getArista2() - 1].getY() - 15, 20, 45);
            } else if (aristas2[pda.getArista1() - 1].getX() < aristas2[pda.getArista2() - 1].getX() && aristas2[pda.getArista1() - 1].getY() == aristas2[pda.getArista2() - 1].getY()) {
                // 2
                pesoCamino2[a].setBounds(((aristas2[pda.getArista2() - 1].getX() - aristas2[pda.getArista1() - 1].getX()) / 2) + aristas2[pda.getArista1() - 1].getX(), aristas2[pda.getArista1() - 1].getY(), 20, 45);
            } else if (aristas2[pda.getArista1() - 1].getX() < aristas2[pda.getArista2() - 1].getX() && aristas2[pda.getArista1() - 1].getY() < aristas2[pda.getArista2() - 1].getY()) {
                // 3
                pesoCamino2[a].setBounds(((aristas2[pda.getArista2() - 1].getX() - aristas2[pda.getArista1() - 1].getX()) / 2) + aristas2[pda.getArista1() - 1].getX(), ((aristas2[pda.getArista2() - 1].getY() - aristas2[pda.getArista1() - 1].getY()) / 2) + aristas2[pda.getArista1() - 1].getY() - 5, 20, 45);
            } else if (aristas[pda.getArista1() - 1].getX() == aristas2[pda.getArista2() - 1].getX() && aristas2[pda.getArista1() - 1].getY() < aristas2[pda.getArista2() - 1].getY()) {
                // 4
                pesoCamino2[a].setBounds(aristas2[pda.getArista1() - 1].getX() - 15, ((aristas2[pda.getArista2() - 1].getY() - aristas2[pda.getArista2() - 1].getY()) / 2) + aristas2[pda.getArista1() - 1].getY(), 20, 45);
            } else if (aristas2[pda.getArista1() - 1].getX() > aristas2[pda.getArista2() - 1].getX() && aristas2[pda.getArista1() - 1].getY() < aristas2[pda.getArista2() - 1].getY()) {
                // 5
                pesoCamino2[a].setBounds(((aristas2[pda.getArista1() - 1].getX() - aristas2[pda.getArista2() - 1].getX()) / 2) + aristas2[pda.getArista2() - 1].getX(), ((aristas2[pda.getArista2() - 1].getY() - aristas2[pda.getArista1() - 1].getY()) / 2) + aristas2[pda.getArista1() - 1].getY() - 10, 20, 45);
            } else if (aristas2[pda.getArista1() - 1].getX() > aristas2[pda.getArista2() - 1].getX() && aristas2[pda.getArista1() - 1].getY() == aristas2[pda.getArista2() - 1].getY()) {
                // 6
                pesoCamino2[a].setBounds(((aristas2[pda.getArista1() - 1].getX() - aristas2[pda.getArista2() - 1].getX()) / 2) + aristas2[pda.getArista2() - 1].getX(), aristas2[pda.getArista2() - 1].getY() - 5, 20, 45);
            } else if (aristas2[pda.getArista1() - 1].getX() > aristas2[pda.getArista2() - 1].getX() && aristas2[pda.getArista1() - 1].getY() > aristas2[pda.getArista2() - 1].getY()) {
                // 7
                pesoCamino2[a].setBounds(((aristas2[pda.getArista1() - 1].getX() - aristas2[pda.getArista2() - 1].getX()) / 2) + aristas2[pda.getArista2() - 1].getX(), ((aristas2[pda.getArista1() - 1].getY() - aristas2[pda.getArista2() - 1].getY()) / 2) + aristas2[pda.getArista2() - 1].getY() - 5, 20, 45);
            }

            add(pesoCamino2[a]);
            this.repaint();
            a++;

        }
        this.dibujarLineasGrafo = true;
        JOptionPane.showMessageDialog(null, "Suma min:"+suma);
        System.out.println("Suma:"+suma);
    }

    public void obtenerSumaMin() {

    }

    public void setPuntos(Puntos[] puntos) {
        this.puntos = puntos;
    }

}
