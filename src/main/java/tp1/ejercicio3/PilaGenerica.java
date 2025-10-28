package tp1.ejercicio3;

import tp1.ejercicio2.ListaEnlazadaGenerica;

/**
 * Sigue el principio LIFO (Last In, First Out)
 */
public class PilaGenerica<T> {
    private ListaEnlazadaGenerica<T> datos;

    public PilaGenerica() {
        this.datos = new ListaEnlazadaGenerica<T>();
    }

    /**
     * Agrega element a la pila
     */
    public void apilar(T element) {
        // En una pila, los elementos se agregan al principio
        datos.agregarInicio(element);
    }

    /**
     * Elimina y devuelve el elemento en el tope de la pila
     */
    public T desapilar() {
        if (datos.esVacia()) {
            return null;
        }
        // En una pila, los elementos se eliminan del principio (tope)
        T elemento = datos.elemento(1);
        datos.eliminarEn(1);
        return elemento;
    }

    /**
     * Devuelve el elemento en el tope de la pila sin eliminarlo
     */
    public T tope() {
        if (datos.esVacia()) {
            return null;
        }
        // El tope de la pila es el primer elemento
        return datos.elemento(1);
    }

    /**
     * Retorna 'true' si la pila esta vacia, false en caso contrario
     */
    public Boolean esVacia() {
        return datos.esVacia();
    }

    /**
     * Retorna el número de elementos en la pila
     */
    public int tamanio() {
        return datos.tamanio();
    }
}