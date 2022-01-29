/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Usuario
 */
public class ParejasAristas {
    private int arista1, arista2, peso,x,y;
    
    public ParejasAristas(){
        
    }
    public ParejasAristas(int arista1, int arista2, int peso){
        this.arista1 = arista1;
        this.arista2 = arista2;
        this.peso = peso;
        this.x=0;
        this.y=0;
        
    }

    public int getArista1() {
        return arista1;
    }

    public void setArista1(int arista1) {
        this.arista1 = arista1;
    }

    public int getArista2() {
        return arista2;
    }

    public void setArista2(int arista2) {
        this.arista2 = arista2;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
