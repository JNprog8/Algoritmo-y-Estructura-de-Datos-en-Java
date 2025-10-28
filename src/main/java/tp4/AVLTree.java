package tp4;

/**
 * Arbol Binario de Busqueda Auto-Balanceado (AVL).
 * Un arbol AVL asegura que la diferencia de alturas entre los subarboles
 * izquierdo y derecho de cualquier nodo no sea mayor a 1.
 */
public class AVLTree<T> extends Comparable<T> {
    /**
     * La raiz del arbol AVL.
     */
    private Node<T> root;

    /**
     *
     * @return
     */
    public Node<T> getRoot() {
        return root;
    }

    /**
     *
     * @param root
     */
    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /**
     * Obtiene la altura de un nodo.
     *
     * @param node El nodo cuya altura se desea obtener.
     * @return La altura del nodo. Retorna 0 si el nodo es nulo.
     */
    public int height(Node<T> node) {
        if (node == null)
            return 0;
        while (node!= null){
            int actual;
            actual = node.getHeight();
            actual +=
        }
        return node.height;
    }

    /**
     * Obtiene el valor maximo entre dos enteros.
     *
     * @param a El primer entero.
     * @param b El segundo entero.
     * @return El valor mas grande entre a y b.
     */
    public int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * Realiza una rotacion a la derecha en el subarbol con la raiz dada.
     *
     * @param node La raiz del subarbol a rotar.
     * @return El nuevo nodo raiz del subarbol rotado.
     */
    public Node<T> rightRotate(Node<T> node) {
        Node<T> leftChild = node.left;
        Node<T> temp = leftChild.right;

        // Realiza la rotacion
        leftChild.right = node;
        node.left = temp;

        // Actualiza las alturas
        node.height = max(height(node.left), height(node.right)) + 1;
        leftChild.height = max(height(leftChild.left), height(leftChild.right)) + 1;

        // Retorna la nueva raiz
        return leftChild;
    }

    /**
     * Realiza una rotacion a la izquierda en el subarbol con la raiz dada.
     *
     * @param node La raiz del subarbol a rotar.
     * @return El nuevo nodo raiz del subarbol rotado.
     */
    public Node<T> leftRotate(Node<T> node) {
        Node<T> rightChild = node.right;
        Node<T> temp = rightChild.left;

        // Realiza la rotacion
        rightChild.left = node;
        node.right = temp;

        // Actualiza las alturas
        node.height = max(height(node.left), height(node.right)) + 1;
        rightChild.height = max(height(rightChild.left), height(rightChild.right)) + 1;

        // Retorna la nueva raiz
        return rightChild;
    }

    /**
     * Obtiene el factor de equilibrio de un nodo. El factor de equilibrio
     * es la diferencia entre la altura del subarbol izquierdo y el derecho.
     *
     * @param node El nodo cuyo factor de equilibrio se desea obtener.
     * @return El factor de equilibrio del nodo. Retorna 0 si el nodo es nulo.
     */
    public int getBalance(Node<T> node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    /**
     * Metodo público para insertar que maneja la raíz automáticamente
     */
    public void insert(T key) {
        this.root = insert(this.root, key);
    }

    /**
     * Inserta una nueva clave en el arbol AVL. Este metodo es recursivo
     * y maneja los casos de desequilibrio (rotaciones) despues de la insercion.
     *
     * @param root La raiz del subarbol en el que se va a insertar.
     * @param key  La clave a insertar.
     * @return La nueva raiz del subarbol despues de la insercion y posible rotacion.
     */
    private Node<T> insert(Node<T> root, T key) {  // Cambiado int key por T key
        if (root == null)
            return new Node<>(key);  // Agregado <> para el constructor

        if (key.compareTo(root.key) < 0)  // Usar compareTo en lugar de <
            root.left = insert(root.left, key);
        else if (key.compareTo(root.key) > 0)  // Usar compareTo en lugar de >
            root.right = insert(root.right, key);
        else
            return root; // Claves duplicadas no permitidas

        // Actualiza la altura de la raiz actual
        root.height = 1 + max(height(root.left), height(root.right));

        // Obtiene el factor de equilibrio
        int balance = getBalance(root);

        // Casos de rotacion para el desequilibrio
        // Caso Izquierda-Izquierda
        if (balance > 1 && key.compareTo(root.left.key) < 0)
            return rightRotate(root);

        // Caso Derecha-Derecha
        if (balance < -1 && key.compareTo(root.right.key) > 0)
            return leftRotate(root);

        // Caso Izquierda-Derecha
        if (balance > 1 && key.compareTo(root.left.key) > 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Caso Derecha-Izquierda
        if (balance < -1 && key.compareTo(root.right.key) < 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    /**
     *
     */
    public void preOrder() {
        preOrder(this.root);
    }

    /**
     * Realiza un recorrido en preorden del arbol.
     *
     * @param node El nodo raiz desde donde empezar el recorrido.
     */
    private void preOrder(Node<T> node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     *
     */
    public void inOrder() {
        inOrder(this.root);
    }

    /**
     * Realiza un recorrido en inorden del arbol.
     *
     * @param node El nodo raiz desde donde empezar el recorrido.
     */
    private void inOrder(Node<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    /**
     *
     */
    public void postOrder() {
        postOrder(this.root);
    }
    
    /**
     * Realiza un recorrido en postorden del arbol.
     *
     * @param node El nodo raiz desde donde empezar el recorrido.
     */
    private void postOrder(Node<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }
}