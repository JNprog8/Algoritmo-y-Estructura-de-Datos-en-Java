package tp3.ejercicio3;

import tp3.ejercicio1y2.ArbolBinario;

public class ContadorArbol {
    private ArbolBinario<Integer> arbol;
    private int numeroHojas;

    public int getNumeroHojas() {
        if (this.arbol.esVacio()) {
            return 0;
        }
        return numeroHojas;
    }

    public void setContador(ArbolBinario<Integer> arbol) {
        this.arbol = arbol;
    }

    public int contarHojas() {
        int hojas;
        if (this.arbol.esVacio()) {
            return hojas = 0;
        }
        var arbolAux = arbol.getHijoDerecho();
    }
}
