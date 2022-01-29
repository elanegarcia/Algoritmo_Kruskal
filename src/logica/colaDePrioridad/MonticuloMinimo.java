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
public class MonticuloMinimo extends Monticulo {
    
	public MonticuloMinimo(int tamanioMaximo) {
		super(tamanioMaximo);
		this.nodo[0] = new ParejasAristas(0,0,0);
	}

	@Override
	protected boolean condicion(int hijo, int padre) {
		return this.nodo[hijo].getPeso() >= this.nodo[padre].getPeso();
	}
	
}
