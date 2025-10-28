package repaso;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Implementación de un Árbol Genérico usando la representación "Lista de Hijos"
 * <p>
 * Un árbol es una colección de nodos que puede estar vacía o formada por un nodo raíz
 * y un conjunto de subárboles, donde cada subárbol está conectado a la raíz por una arista.
 * <p>
 * Esta implementación utiliza listas enlazadas para almacenar los hijos de cada nodo,
 * proporcionando flexibilidad en el uso y gestión dinámica de memoria.
 *
 * @param <T> Tipo de dato que almacena cada nodo del árbol
 * @author Basado en material de UNRN - Algoritmos y Estructuras de Datos
 */
public class ArbolGenerico<T> {
    // Referencia al nodo raíz del árbol
    private Nodo<T> raiz;

    /**
     * Constructor que crea un árbol vacío
     */
    public ArbolGenerico() {
        this.raiz = null;
    }

    /**
     * Constructor que crea un árbol con un nodo raíz
     *
     * @param datoRaiz El dato que contendrá la raíz
     */
    public ArbolGenerico(T datoRaiz) {
        this.raiz = new Nodo<>(datoRaiz);
    }

    /**
     * Verifica si el árbol está vacío
     *
     * @return true si el árbol está vacío, false en caso contrario
     */
    public boolean estaVacio() {
        return raiz == null;
    }

    /**
     * Obtiene el dato de la raíz
     *
     * @return El dato de la raíz, null si el árbol está vacío
     */
    public T getRaiz() {
        return raiz != null ? raiz.getDato() : null;
    }

    /**
     * Establece la raíz del árbol
     *
     * @param dato El dato para la raíz
     */
    public void setRaiz(T dato) {
        this.raiz = new Nodo<>(dato);
    }

    /**
     * Agrega un hijo a un nodo específico identificado por su valor
     *
     * @param padre El valor del nodo padre
     * @param hijo  El valor del nodo hijo a agregar
     * @return true si se pudo agregar, false si no se encontró el padre
     */
    public boolean agregarHijo(T padre, T hijo) {
        if (estaVacio()) {
            return false;
        }

        Nodo<T> nodoPadre = buscarNodo(raiz, padre);
        if (nodoPadre != null) {
            nodoPadre.agregarHijo(new Nodo<>(hijo));
            return true;
        }
        return false;
    }

    /**
     * Metodo auxiliar para buscar un nodo por su valor
     * Utiliza recursión en preorden para recorrer el árbol
     * <p>
     * RECURSIÓN: La función se llama a sí misma para cada hijo del nodo actual,
     * explorando primero el nodo actual y luego recursivamente sus hijos.
     *
     * @param nodoActual El nodo desde donde iniciar la búsqueda
     * @param valor      El valor a buscar
     * @return El nodo que contiene el valor, null si no se encuentra
     */
    private Nodo<T> buscarNodo(Nodo<T> nodoActual, T valor) {
        // Caso base: si el nodo actual es null, no se encontró
        if (nodoActual == null) {
            return null;
        }

        // Si encontramos el valor, retornamos el nodo
        if (nodoActual.getDato().equals(valor)) {
            return nodoActual;
        }

        // Caso recursivo: buscamos en cada hijo
        for (Nodo<T> hijo : nodoActual.getHijos()) {
            Nodo<T> resultado = buscarNodo(hijo, valor);
            if (resultado != null) {
                return resultado;
            }
        }

        return null; // No se encontró en este subárbol
    }

    /**
     * RECORRIDO PREORDEN
     * <p>
     * Estrategia: Se procesa primero el nodo actual (raíz) y luego recursivamente
     * se procesan todos sus hijos de izquierda a derecha.
     * <p>
     * RECURSIÓN EN PREORDEN:
     * 1. Se procesa el nodo actual (imprimir/procesar dato)
     * 2. Para cada hijo del nodo actual:
     * - Se llama recursivamente al metodo preorden con el hijo
     * - Esto garantiza que cada subárbol se procese completamente
     * antes de pasar al siguiente hermano
     * <p>
     * Orden de procesamiento: Raíz -> Hijo1 -> Nietos de Hijo1 -> Hijo2 -> Nietos de Hijo2...
     *
     * @param accion Función que define qué hacer con cada dato visitado
     */
    public void recorridoPreorden(Consumer<T> accion) {
        if (!estaVacio()) {
            preordenRecursivo(raiz, accion);
        }
    }

    /**
     * Implementación recursiva del recorrido preorden
     */
    private void preordenRecursivo(Nodo<T> nodo, Consumer<T> accion) {
        // Caso base: si el nodo es null, no hay nada que procesar
        if (nodo == null) {
            return;
        }

        // 1. Procesar el nodo actual PRIMERO
        accion.accept(nodo.getDato());

        // 2. Luego procesar recursivamente cada hijo
        for (Nodo<T> hijo : nodo.getHijos()) {
            preordenRecursivo(hijo, accion);
        }
    }

    /**
     * RECORRIDO POSTORDEN
     * <p>
     * Estrategia: Se procesan primero recursivamente todos los hijos del nodo
     * y finalmente se procesa el nodo actual (raíz del subárbol).
     * <p>
     * RECURSIÓN EN POSTORDEN:
     * 1. Para cada hijo del nodo actual:
     * - Se llama recursivamente al metodo postorden con el hijo
     * - Esto garantiza que todos los descendientes se procesen
     * antes que el nodo actual
     * 2. Después de procesar todos los hijos, se procesa el nodo actual
     * <p>
     * Orden de procesamiento: Hojas -> Nodos internos -> Raíz
     * Útil para operaciones como eliminación o cálculo de tamaños de subárboles.
     *
     * @param accion Función que define qué hacer con cada dato visitado
     */
    public void recorridoPostorden(Consumer<T> accion) {
        if (!estaVacio()) {
            postordenRecursivo(raiz, accion);
        }
    }

    /**
     * Implementación recursiva del recorrido postorden
     */
    private void postordenRecursivo(Nodo<T> nodo, Consumer<T> accion) {
        // Caso base: si el nodo es null, no hay nada que procesar
        if (nodo == null) {
            return;
        }

        // 1. PRIMERO procesar recursivamente todos los hijos
        for (Nodo<T> hijo : nodo.getHijos()) {
            postordenRecursivo(hijo, accion);
        }

        // 2. DESPUÉS procesar el nodo actual
        accion.accept(nodo.getDato());
    }

    /**
     * RECORRIDO INORDEN para Árboles Generales
     * <p>
     * Estrategia: Se procesa el hijo más izquierdo, luego la raíz,
     * y finalmente el resto de los hijos de izquierda a derecha.
     * <p>
     * RECURSIÓN EN INORDEN:
     * 1. Si el nodo tiene hijos:
     * - Se procesa recursivamente el PRIMER hijo (más izquierdo)
     * - Se procesa el nodo actual (raíz)
     * - Se procesan recursivamente el RESTO de los hijos
     * 2. Si el nodo no tiene hijos (es hoja), solo se procesa el nodo
     * <p>
     * Nota: Para árboles generales, el recorrido inorden no es tan común
     * como en árboles binarios, pero sigue esta lógica.
     *
     * @param accion Función que define qué hacer con cada dato visitado
     */
    public void recorridoInorden(Consumer<T> accion) {
        if (!estaVacio()) {
            inordenRecursivo(raiz, accion);
        }
    }

    /**
     * Implementación recursiva del recorrido inorden
     */
    private void inordenRecursivo(Nodo<T> nodo, Consumer<T> accion) {
        // Caso base: si el nodo es null, no hay nada que procesar
        if (nodo == null) {
            return;
        }

        List<Nodo<T>> hijos = nodo.getHijos();

        // Si el nodo tiene al menos un hijo
        if (!hijos.isEmpty()) {
            // 1. Procesar recursivamente el PRIMER hijo (más izquierdo)
            inordenRecursivo(hijos.get(0), accion);

            // 2. Procesar el nodo actual
            accion.accept(nodo.getDato());

            // 3. Procesar recursivamente el RESTO de los hijos
            for (int i = 1; i < hijos.size(); i++) {
                inordenRecursivo(hijos.get(i), accion);
            }
        } else {
            // Si es hoja, solo procesar el nodo actual
            accion.accept(nodo.getDato());
        }
    }

    /**
     * RECORRIDO POR NIVELES (Breadth-First Search)
     * <p>
     * Estrategia: Se procesan todos los nodos de un nivel antes de pasar
     * al siguiente nivel. Se utiliza una cola (Queue) para mantener
     * el orden de procesamiento.
     * <p>
     * NO ES RECURSIVO - Utiliza iteración y una cola:
     * 1. Se encola la raíz
     * 2. Mientras la cola no esté vacía:
     * - Se desencola un nodo y se procesa
     * - Se encolan todos los hijos del nodo desencolado
     * <p>
     * Orden de procesamiento: Nivel 0 (raíz) -> Nivel 1 -> Nivel 2 -> ...
     *
     * @param accion Función que define qué hacer con cada dato visitado
     */
    public void recorridoPorNiveles(Consumer<T> accion) {
        if (estaVacio()) {
            return;
        }

        // Utilizamos una cola para mantener el orden de procesamiento
        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.offer(raiz); // Encolamos la raíz

        while (!cola.isEmpty()) {
            // Desencolamos el siguiente nodo a procesar
            Nodo<T> nodoActual = cola.poll();

            // Procesamos el nodo actual
            accion.accept(nodoActual.getDato());

            // Encolamos todos los hijos del nodo actual
            for (Nodo<T> hijo : nodoActual.getHijos()) {
                cola.offer(hijo);
            }
        }
    }

    /**
     * Calcula la altura del árbol
     * La altura es la longitud del camino más largo desde la raíz hasta una hoja
     * <p>
     * RECURSIÓN PARA CALCULAR ALTURA:
     * - Si el nodo es null (árbol vacío), altura = -1
     * - Si el nodo es hoja, altura = 0
     * - Si el nodo tiene hijos, altura = 1 + máxima altura de sus hijos
     *
     * @return La altura del árbol (-1 si está vacío)
     */
    public int getAltura() {
        return calcularAltura(raiz);
    }

    /**
     * Metodo recursivo auxiliar para calcular la altura
     */
    private int calcularAltura(Nodo<T> nodo) {
        // Caso base: nodo null (árbol vacío)
        if (nodo == null) {
            return -1;
        }

        // Si es hoja, altura = 0
        if (nodo.esHoja()) {
            return 0;
        }

        // Caso recursivo: 1 + máxima altura de los hijos
        int alturaMaxima = -1;
        for (Nodo<T> hijo : nodo.getHijos()) {
            int alturaHijo = calcularAltura(hijo);
            alturaMaxima = Math.max(alturaMaxima, alturaHijo);
        }

        return 1 + alturaMaxima;
    }

    /**
     * Calcula el grado del árbol (grado máximo entre todos los nodos)
     *
     * @return El grado del árbol
     */
    public int getGradoArbol() {
        return calcularGradoMaximo(raiz);
    }

    /**
     * Método recursivo para calcular el grado máximo del árbol
     */
    private int calcularGradoMaximo(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }

        int gradoMaximo = nodo.getGrado();

        // Verificar recursivamente el grado de todos los hijos
        for (Nodo<T> hijo : nodo.getHijos()) {
            int gradoHijo = calcularGradoMaximo(hijo);
            gradoMaximo = Math.max(gradoMaximo, gradoHijo);
        }

        return gradoMaximo;
    }

    /**
     * Cuenta el total de nodos en el árbol
     *
     * @return El número total de nodos
     */
    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    /**
     * Metodo recursivo para contar nodos
     */
    private int contarNodosRecursivo(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }

        int contador = 1; // Contar el nodo actual

        // Sumar recursivamente los nodos de todos los hijos
        for (Nodo<T> hijo : nodo.getHijos()) {
            contador += contarNodosRecursivo(hijo);
        }

        return contador;
    }

    /**
     * Verifica si el árbol está lleno
     * Un árbol está lleno si cada nodo interno tiene grado k (grado del árbol)
     * y todas las hojas están en el mismo nivel
     *
     * @return true si el árbol está lleno, false en caso contrario
     */
    public boolean esLleno() {
        if (estaVacio()) {
            return true;
        }

        int grado = getGradoArbol();
        int altura = getAltura();

        return verificarLleno(raiz, grado, altura, 0);
    }

    /**
     * Metodo recursivo auxiliar para verificar si el árbol está lleno
     */
    private boolean verificarLleno(Nodo<T> nodo, int gradoEsperado, int alturaArbol, int nivelActual) {
        if (nodo == null) {
            return true;
        }

        // Si es hoja, debe estar en el último nivel
        if (nodo.esHoja()) {
            return nivelActual == alturaArbol;
        }

        // Si no es hoja, debe tener exactamente el grado esperado
        if (nodo.getGrado() != gradoEsperado) {
            return false;
        }

        // Verificar recursivamente todos los hijos
        for (Nodo<T> hijo : nodo.getHijos()) {
            if (!verificarLleno(hijo, gradoEsperado, alturaArbol, nivelActual + 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Metodo para imprimir el árbol de forma visual (representación simple)
     */
    public void imprimirArbol() {
        if (estaVacio()) {
            System.out.println("Árbol vacío");
            return;
        }
        imprimirNodo(raiz, "", true);
    }

    /**
     * Metodo auxiliar recursivo para imprimir el árbol visualmente
     */
    private void imprimirNodo(Nodo<T> nodo, String prefijo, boolean esUltimo) {
        if (nodo != null) {
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + nodo.getDato());

            List<Nodo<T>> hijos = nodo.getHijos();
            for (int i = 0; i < hijos.size(); i++) {
                boolean esUltimoHijo = (i == hijos.size() - 1);
                String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");
                imprimirNodo(hijos.get(i), nuevoPrefijo, esUltimoHijo);
            }
        }
    }

    /**
     * Metodo toString para representación del árbol
     */
    @Override
    public String toString() {
        if (estaVacio()) {
            return "Árbol vacío";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Árbol Genérico:\n");
        sb.append("Raíz: ").append(raiz.getDato()).append("\n");
        sb.append("Altura: ").append(getAltura()).append("\n");
        sb.append("Grado: ").append(getGradoArbol()).append("\n");
        sb.append("Número de nodos: ").append(contarNodos()).append("\n");
        sb.append("¿Es lleno?: ").append(esLleno() ? "Sí" : "No");

        return sb.toString();
    }
}
