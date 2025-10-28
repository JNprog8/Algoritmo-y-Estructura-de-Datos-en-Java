package tp4;

/**
 * Representa un nodo en el arbol AVL.
 */
public class Node<T> {
    /**
     * La clave o valor almacenado en el nodo.
     */
    public T key;  // Cambiado de private a public
    /**
     * Referencia al nodo hijo izquierdo.
     */
    public Node<T> left;  // Agregado <T> y cambiado a public
    /**
     * Referencia al nodo hijo derecho.
     */
    public Node<T> right;  // Agregado <T> y cambiado a public
    /**
     * La altura del nodo en el arbol.
     */
    private int height = 1;// Cambiado de private a public

    /**
     * Constructor para nodo nuevo
     *
     * @param key La clave para el nuevo nodo.
     */
    public Node(T key) {
        this.key = key;
        this.height = 1;
        this.left = null;
        this.right = null;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}