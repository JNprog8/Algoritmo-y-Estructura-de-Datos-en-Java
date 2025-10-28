package repaso;

/**
 * Nodo genérico que almacena un valor de tipo T.
 *
 * @param <T> Tipo de dato contenido en el nodo.
 *            <p>
 *            Interface funcional: retorna el valor del nodo
 */

public interface INode<T> {
    T getValue();
}
