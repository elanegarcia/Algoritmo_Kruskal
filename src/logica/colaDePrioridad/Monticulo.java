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
public abstract class Monticulo {

    protected ParejasAristas[] nodo;
    protected int tamanio;

    private static final int RAIZ = 1;

    public Monticulo(int tamanioMaximo) {
        this.tamanio = 0;
        this.nodo = new ParejasAristas[tamanioMaximo + 1];
    }

    private int padre(int i) {
        return i / 2;
    }

    private int hijoIzquierdo(int i) {
        return 2 * i;
    }

    private int hijoDerecho(int i) {
        return 2 * i + 1;
    }

    public boolean tieneHijoIzquierdo(int i) {
        return this.hijoIzquierdo(i) <= this.tamanio;
    }

    public boolean tieneHijoDerecho(int i) {
        return this.hijoDerecho(i) <= this.tamanio;
    }

    private boolean tieneHijoUnico(int i) {
        return this.tieneHijoIzquierdo(i) && !this.tieneHijoDerecho(i);
    }

    public boolean estaVacio() {
        return this.tamanio == 0;
    }

    public boolean estaLleno() {
        return this.tamanio == this.nodo.length - 1;
    }

    protected boolean esHoja(int i) {
        return !this.tieneHijoIzquierdo(i) && !this.tieneHijoDerecho(i);
    }

    protected void intercambiar(int i, int j) {
        ParejasAristas tmp = this.nodo[i];
        this.nodo[i] = this.nodo[j];
        this.nodo[j] = tmp;
    }

    private void montar() {
        for (int i = this.tamanio / 2; i >= 1; i--) {
            this.hundir(i);
        }
    }

    private void flotar(int actual) {
        while (!this.condicion(actual, this.padre(actual))) {
            this.intercambiar(actual, this.padre(actual));
            actual = this.padre(actual);
        }
    }

    private void hundir(int i) {
        if (!this.esHoja(i)) {
            if (!this.tieneHijoUnico(i)) {
                if (!this.condicion(this.hijoIzquierdo(i), i) || !this.condicion(this.hijoDerecho(i), i)) {
                    if (!this.condicion(this.hijoIzquierdo(i), this.hijoDerecho(i))) {
                        this.intercambiar(i, this.hijoIzquierdo(i));
                        this.hundir(this.hijoIzquierdo(i));
                    } else {
                        this.intercambiar(this.hijoDerecho(i), i);
                        this.hundir(this.hijoDerecho(i));
                    }
                }
            } else {
                if (!this.condicion(this.hijoIzquierdo(i), i)) {
                    this.intercambiar(i, this.hijoIzquierdo(i));
                    this.hundir(this.hijoIzquierdo(i));
                }
            }
        }
    }

    protected abstract boolean condicion(int hijo, int padre);

    public void insertar(ParejasAristas nuevo) throws IllegalStateException {
        if (this.estaLleno()) {
            throw new IllegalStateException("No se puede insertar nuevo elemento. Mont�culo lleno.");
        }
        this.nodo[++this.tamanio] = nuevo;
        this.flotar(this.tamanio);
        this.montar();
    }

    public ParejasAristas eliminar() throws IllegalStateException {
        if (this.estaVacio()) {
            throw new IllegalStateException("No se puede eliminar un elemento. Mont�culo vac�o.");
        }
        ParejasAristas nodo = this.nodo[RAIZ];
        this.nodo[RAIZ] = this.nodo[this.tamanio--];
        this.hundir(RAIZ);
        return nodo;
    }

    public ParejasAristas pispear() throws IllegalStateException {
        if (this.estaVacio()) {
            throw new IllegalStateException("No se puede pispear un elemento. Mont�culo vac�o.");
        }
        return this.nodo[RAIZ];
    }

    public void mostrar() {
        for (int i = 1; i <= this.tamanio / 2; i++) {
            System.out.print("Padre: " + this.nodo[i].getPeso());
            if (this.tieneHijoIzquierdo(i)) {
                System.out.print(" HijoIzquierdo: " + this.nodo[2 * i].getPeso());
            }
            if (this.tieneHijoDerecho(i)) {
                System.out.print(" HijoDerecho: " + this.nodo[2 * i + 1].getPeso());
            }
            System.out.println();
        }
    }

    public ParejasAristas[] getNodo() {
        return nodo;
    }

    public int getTamanio() {
        return tamanio;
    }
    
    
}
