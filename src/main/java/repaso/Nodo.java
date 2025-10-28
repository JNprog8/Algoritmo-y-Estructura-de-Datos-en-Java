package repaso;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase interna que representa un nodo del árbol
 * Cada nodo contiene:
 * - El dato del nodo
 * - Una lista de referencias a sus hijos
 */
public class Nodo<T> {
    private T dato;
    private List<Nodo<T>> hijos;

    /**
     * Constructor del nodo
     * @param dato El valor que almacenará el nodo
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    /**
     * Agrega un hijo al nodo actual
     * @param hijo El nodo hijo a agregar
     */
    public void agregarHijo(Nodo<T> hijo) {
        this.hijos.add(hijo);
    }

    /**
     * Obtiene la lista de hijos del nodo
     * @return Lista de nodos hijos
     */
    public List<Nodo<T>> getHijos() {
        return hijos;
    }

    /**
     * Obtiene el dato almacenado en el nodo
     * @return El dato del nodo
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece el dato del nodo
     * @param dato El nuevo dato a almacenar
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Verifica si el nodo es una hoja (no tiene hijos)
     * @return true si es hoja, false en caso contrario
     */
    public boolean esHoja() {
        return hijos.isEmpty();
    }

    /**
     * Obtiene el grado del nodo (número de hijos)
     * @return El grado del nodo
     */
    public int getGrado() {
        return hijos.size();
    }
}