package tp1.ejercicio3;

import tp1.ejercicio2.ListaEnlazadaGenerica;

/**
 * Sigue el principio FIFO (First In, First Out)
 */
public class ColaGenerica<T> {
    private ListaEnlazadaGenerica<T> datos;

    public ColaGenerica() {
        this.datos = new ListaEnlazadaGenerica<T>();
    }

    /**
     * Agrega element a la cola
     */
    public void encolar(T element) {
        // En una cola, los elementos se agregan al final
        datos.agregarFinal(element);
    }

    /**
     * Elimina y devuelve el primer elemento de la cola
     */
    public T desencolar() {
        if (datos.esVacia()) {
            return null;
        }
        // En una cola, los elementos se eliminan del principio
        T elemento = datos.elemento(1);
        datos.eliminarEn(1);
        return elemento;
    }

    /**
     * Devuelve el elemento en el tope de la cola sin eliminarlo
     */
    public T tope() {
        if (datos.esVacia()) {
            return null;
        }
        // El tope de la cola es el primer elemento
        return datos.elemento(1);
    }

    /**
     * Retorna 'true' si la cola esta vacia, 'false' en caso contrario
     */
    public Boolean esVacia() {
        return datos.esVacia();
    }

    /**
     * Retorna el número de elementos en la cola.
     */
    public int tamanio() {
        return datos.tamanio();
    }
}