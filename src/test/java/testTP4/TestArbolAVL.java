package testTP4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp4.AVLTree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Clase de pruebas unitarias para la clase AVLTree.
 * Versión mejorada usando métodos públicos en lugar de acceso directo a campos.
 */
public class TestArbolAVL {

    private static AVLTree<Integer> tree;

    /**
     * Metodo que se ejecuta una sola vez antes de todos los tests.
     * Usa los métodos públicos para una mejor encapsulación.
     */
    @BeforeAll
    static void setup() {
        tree = new AVLTree<>();

        // Usar el método público insert() en lugar de acceso directo
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);
    }

    /**
     * Prueba si la raiz del arbol no es nula despues de las inserciones iniciales.
     */
    @Test
    @DisplayName("La raiz del arbol no debe ser nula despues de las inserciones.")
    void testRootNotNull() {
        assertNotNull(tree.getRoot(), "La raiz del arbol no deberia ser nula.");
    }

    /**
     * Prueba la altura de la raiz para verificar que el arbol se balanceo correctamente.
     */
    @Test
    @DisplayName("Verifica que la altura de la raiz es la esperada despues del balanceo.")
    void testRootHeight() {
        assertEquals(3, tree.height(tree.getRoot()), "La altura de la raiz deberia ser 3.");
    }

    /**
     * Prueba que verifica que la clave de la raíz es la esperada.
     */
    @Test
    @DisplayName("Verifica que la clave de la raiz es correcta.")
    void testRootKey() {
        // Después del balanceo AVL, la raíz debería ser 30
        assertEquals(30, tree.getRoot().getKey(), "La clave de la raiz deberia ser 30.");
    }

    /**
     * Test para mostrar los recorridos usando los métodos públicos.
     */
    @Test
    @DisplayName("Imprime los recorridos para verificacion manual.")
    void printTreeTraversal() {
        System.out.println("--- Recorridos del arbol ---");

        System.out.print("Recorrido Preorden: ");
        tree.preOrder();
        System.out.println();

        System.out.print("Recorrido Inorden: ");
        tree.inOrder();
        System.out.println();

        System.out.print("Recorrido Postorden: ");
        tree.postOrder();
        System.out.println();

        System.out.println("--------------------------");
    }
}