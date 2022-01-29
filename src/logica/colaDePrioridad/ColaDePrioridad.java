/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.colaDePrioridad;

import logica.ParejasAristas;

/**
 *
 * @author Usuario
 */
public class ColaDePrioridad {

    private Monticulo monticulo;
    private int tamanioMaximo;
    private int cantidadDeElementos;
    private int tamanio;

    public int getTamaniooMaximo() {
        return tamanioMaximo;
    }

    public int getCantidadDeElementos() {
        return cantidadDeElementos;
    }

    public ColaDePrioridad(int tamanioMaximo) {
        this.tamanioMaximo = tamanioMaximo;
        this.monticulo = new MonticuloMinimo(tamanioMaximo);
        this.cantidadDeElementos = 0;
    }

    public boolean estaVacia() {
        return this.monticulo.estaVacio();
    }

    public boolean estaLlena() {
        return this.monticulo.estaLleno();
    }
    
    public void mostrarColaPrioridad(){
        monticulo.mostrar();
        while(!estaVacia())
            System.out.println(eliminar().getPeso());
        
        
    }
    public void insertar(ParejasAristas nuevo) throws IllegalStateException {
        if (this.estaLlena()) {
            throw new IllegalStateException("No se puede insertar. Cola de prioridad llena.");
        }
        this.cantidadDeElementos++;
        this.monticulo.insertar(nuevo);
    }

    public ParejasAristas eliminar() throws IllegalStateException {
        if (this.estaVacia()) {
            throw new IllegalStateException("No se puede eliminar. Cola de prioridad vac�a.");
        }
        this.cantidadDeElementos--;
        return this.monticulo.eliminar();
    }

    public ParejasAristas pispear() throws IllegalStateException {
        if (this.estaVacia()) {
            throw new IllegalStateException("No se puede pispear. Cola de prioridad vac�a.");
        }
        return this.monticulo.pispear();
    }

    public Monticulo getMonticulo() {
        return monticulo;
    }

    public int getTamanio() {
        return tamanio;
    }
    
    
}
