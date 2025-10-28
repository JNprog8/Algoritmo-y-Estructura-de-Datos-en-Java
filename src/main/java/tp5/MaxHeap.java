package tp5;

import tp1.ejercicio2.ListaGenerica;

public class MaxHeap<T> implements ColasPrioridades<T> {
    private T[] datos;
    private int cantidadElementos;

    public MaxHeap() {
        this.datos = (T[]) new Comparable[cantidadElementos];
        this.cantidadElementos = 0;
    }

    public MaxHeap(ListaGenerica<T> lista) {
        if (lista.esVacia()) {
            throw new RuntimeException("La lista no debe ser vacía. Ingrese una lista con elementos.");
        }
        this.cantidadElementos = lista.tamanio();
        this.datos = (T[]) new Comparable[cantidadElementos];

        lista.comenzar();
        int i = 0;
        while (!lista.fin()) {
            datos[i] = lista.proximo();
            i++;
        }
    }

    @Override
    public boolean esVacia() {
        if (cantidadElementos == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void eliminar() {
        datos.
    }

    @Override
    public boolean agregar(T elemento) {
        return false;
    }

    @Override
    public void tope() {

    }
}
