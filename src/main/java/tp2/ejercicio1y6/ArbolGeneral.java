package tp2.ejercicio1y6;

import tp1.ejercicio2.ListaEnlazadaGenerica;
import tp1.ejercicio2.ListaGenerica;
import tp1.ejercicio3.ColaGenerica;

/**
 * Clase genérica que representa un Árbol General.
 * Un Árbol General es una estructura de datos en la cual cada nodo puede tener
 * cero o más hijos.
 *
 * @param <T> el tipo de dato almacenado en cada nodo del árbol.
 */
public class ArbolGeneral<T> {
    private T dato;
    private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

    /**
     * Constructor que inicia un árbol cuya raíz almacena el dato pasado como parámetro
     * y no tiene hijos.
     *
     * @param dato valor almacenado en la raíz
     */
    public ArbolGeneral(T dato) {
        this.dato = dato;
    }

    /**
     * Constructor que inicia un árbol con el dato pasado como parámetro
     * y con una lista de hijos especificada.
     *
     * @param dato  valor almacenado en la raíz. No puede ser null.
     * @param hijos lista de subárboles hijos. Si es null, se inicializa como lista vacía.
     */
    public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
        this(dato);
        if (hijos == null)
            this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
        else
            this.hijos = hijos;
    }

    /**
     * Obtiene el dato almacenado en la raíz del árbol.
     *
     * @return el dato almacenado en la raíz del árbol.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece el dato de la raíz del árbol.
     *
     * @param dato el nuevo dato para la raíz. No puede ser null.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la lista de hijos de la raíz del árbol.
     *
     * @return la lista de hijos de la raíz del árbol (nunca null)
     */
    public ListaGenerica<ArbolGeneral<T>> getHijos() {
        return this.hijos;
    }

    /**
     * Establece la lista de hijos del árbol.
     *
     * @param hijos la nueva lista de hijos. Si es null, se inicializa como lista vacía.
     */
    public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
        if (hijos == null)
            this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
        else
            this.hijos = hijos;
    }

    /**
     * Agrega un subárbol como hijo a la lista de hijos del árbol actual.
     *
     * @param unHijo subárbol a agregar. No puede ser null.
     * @complexity O(1) - inserción al final de la lista enlazada
     */
    public void agregarHijo(ArbolGeneral<T> unHijo) {
        this.getHijos().agregarFinal(unHijo);
    }

    /**
     * Verifica si el nodo raíz es hoja (no tiene hijos).
     *
     * @return true si el nodo raíz es hoja (no tiene hijos), false en caso contrario
     * @complexity O(1) - verificación directa del estado de la lista
     */
    public boolean esHoja() {
        return !this.tieneHijos();
    }

    /**
     * Verifica si la raíz tiene hijos.
     *
     * @return true si la raíz tiene al menos un hijo, false en caso contrario
     * @complexity O(1) - verificación del tamaño de la lista
     */
    public boolean tieneHijos() {
        return !this.hijos.esVacia();
    }

    /**
     * Verifica si el árbol está vacío.
     * Un árbol se considera vacío si el dato de la raíz es null y no tiene hijos.
     *
     * @return true si el árbol está vacío (dato == null y sin hijos), false en caso contrario
     * @complexity O(1) - verificaciones directas de atributos
     */
    public boolean esVacio() {
        return this.dato == null && !this.tieneHijos();
    }

    /**
     * Elimina, de la lista de hijos, el subárbol específico
     *
     * @param hijo subárbol a eliminar de la lista de hijos
     * @complexity O(n) donde n es el número de hijos (debido a la búsqueda en lista enlazada)
     */
    public void eliminarHijo(ArbolGeneral<T> hijo) {
        if (this.tieneHijos()) {
            ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
            if (hijos.incluye(hijo))
                hijos.eliminar(hijo);
        }
    }
    // ======================== PUNTO 3.b ========================

    // ======================== BÚSQUEDAS ========================

    /**
     * Busca un nodo que contenga el dato especificado.
     * Realiza búsqueda en profundidad (DFS).
     *
     * @param elementoABuscar el dato a buscar en el árbol. No puede ser null.
     * @return el nodo que contiene el dato, null si no se encuentra
     * @complexity O(n) donde n es el número total de nodos
     */
    private ArbolGeneral<T> buscarNodo(T elementoABuscar) {
        // Si la raíz es no nula y el dato coincide, se retorna el nodo actual
        if (!this.esVacio() && this.dato.equals(elementoABuscar)) {
            return this;
        }

        // si tiene hijos
        if (this.tieneHijos()) {
            // Se recorren los hijos
            var listaHijos = this.getHijos();
            listaHijos.comenzar();
            while (!listaHijos.fin()) {
                var hijo = listaHijos.proximo();
                // para buscar el elemento, recursivamente al subArbol del hijo
                var resultado = hijo.buscarNodo(elementoABuscar);

                // Si el resultado de la búsqueda en el subárbol no es nulo, se ha encontrado el nodo.
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        // se recorrió el árbol y no se encontró el elemento
        return null;
    }

    /**
     * Verifica si existe algún nodo con el dato especificado en el subárbol.
     *
     * @param elemento el dato a buscar en el árbol.No puede ser null.
     * @return true si el dato se encuentra, false en caso contrario.
     * @complexity O(n), donde n es el número total de nodos
     */
    public boolean contieneDescendiente(T elemento) {
        // el dato coincide con la raíz
        if (this.getDato() != null && this.getDato().equals(elemento)) {
            return true;
        }

        // Si el nodo actual tiene hijos, se recorre cada uno.
        if (this.tieneHijos()) {
            var hijos = this.getHijos();
            hijos.comenzar();
            while (!hijos.fin()) {
                var hijo = hijos.proximo();
                if (hijo.contieneDescendiente(elemento)) {
                    return true;
                }
            }
        }
        // Si no se encontró el elemento en el subárbol de cada hijo, se devuelve false.
        return false;
    }

    // ======================== MÉTODOS AUXILIARES ========================

    /**
     * Obtiene el grado actual del nodo (cantidad de hijos directos de este nodo).
     *
     * @return el número de hijos directos. Retorna -1 si el árbol está vacío.
     * @complexity O(1)
     */
    private int getGradoActual() {
        if (this.esVacio()) return -1;
        return this.hijos.tamanio();
    }

    /**
     * Obtiene el grado máximo del árbol (el mayor número de hijos que tiene cualquier nodo).
     * Utiliza recorrido BFS para examinar todos los nodos.
     *
     * @return el grado máximo encontrado en el árbol. Retorna -1 si el árbol está vacío.
     * @complexity O(n) donde n es el número total de nodos
     */
    private int getGradoTotal() {
        if (this.esVacio()) return -1;

        int maxGrado = this.getGradoActual();
        var cola = new ColaGenerica<ArbolGeneral<T>>();
        cola.encolar(this);

        while (!cola.esVacia()) {
            var nodo = cola.desencolar();
            int gradoNodo = nodo.getHijos().tamanio();
            maxGrado = Math.max(maxGrado, gradoNodo);

            encolarHijos(nodo, cola);
        }

        return maxGrado;
    }

    /**
     * Mantiene consistencia en el patrón BFS utilizado en múltiples métodos.
     * Función auxiliar para encolar hijos de un nodo.
     *
     * @param nodo el nodo cuyos hijos se van a encolar. No puede ser null.
     * @param cola la cola donde se encolarán los hijos. No puede ser null.
     * @throws IllegalArgumentException si nodo o cola son null
     * @complexity O(h) donde h es el número de hijos del nodo
     */
    private void encolarHijos(ArbolGeneral<T> nodo, ColaGenerica<ArbolGeneral<T>> cola) {
        if (nodo.tieneHijos()) {
            var hijos = nodo.getHijos();
            hijos.comenzar();
            while (!hijos.fin()) {
                cola.encolar(hijos.proximo());
            }
        }
    }

    /**
     * Imprime los elementos de una lista genérica.
     * Metodo util para debugging y testing.
     *
     * @param lista la lista a imprimir. No puede ser null.
     * @throws IllegalArgumentException si lista es null
     * @complexity O(k) donde k es el tamaño de la lista
     */
    private void imprimirLista(ListaGenerica<T> lista) {
        lista.comenzar();
        while (!lista.fin()) {
            System.out.print(lista.proximo() + " ");
        }
        System.out.println();
    }

    /**
     * Devuelve una representación en cadena del árbol para depuración.
     * Muestra el dato de la raíz y la cantidad de hijos.
     *
     * @return representación en String del nodo actual
     */
    @Override
    public String toString() {
        if (esVacio()) {
            return "Árbol vacío";
        }
        return "Nodo: " + dato + " (Hijos: " + hijos.tamanio() + ")";
    }

    // ======================== RECORRIDOS DEL ÁRBOL ========================

    /**
     * Recorrido en preorden: visita raíz, luego hijos de izquierda a derecha.
     *
     * @return lista con los datos en orden preorden
     * @complexity O(n), n es el número total de nodos
     */
    public ListaGenerica<T> preOrden() {
        var resultado = new ListaEnlazadaGenerica<T>();
        preOrdenRecursivo(this, resultado);
        return resultado;
    }

    /**
     * Metodo auxiliar, recursivo para el recorrido preorden.
     *
     * @param nodo el nodo actual a procesar. No puede ser null.
     * @param res  la lista resultado donde se almacenan los datos. No puede ser null.
     * @complexity O(n) donde n es el número de nodos en el subárbol
     */
    private void preOrdenRecursivo(ArbolGeneral<T> nodo, ListaGenerica<T> res) {
        if (nodo.esVacio()) return;
        res.agregarFinal(nodo.getDato());
        var hijos = nodo.getHijos();
        hijos.comenzar();
        while (!hijos.fin()) {
            preOrdenRecursivo(hijos.proximo(), res);
        }
    }

    /**
     * Recorrido en postorden: visita hijos de izquierda a derecha, luego raíz.
     *
     * @return lista con los datos en orden postorden
     * @complexity O(n) donde n es el número total de nodos
     */
    public ListaGenerica<T> postOrden() {
        var resultado = new ListaEnlazadaGenerica<T>();
        postOrdenRecursivo(this, resultado);
        return resultado;
    }

    /**
     * Metodo auxiliar recursivo para el recorrido postorden.
     *
     * @param nodo el nodo actual a procesar. No puede ser null.
     * @param res  la lista resultado donde se almacenan los datos. No puede ser null.
     * @complexity O(n) donde n es el número de nodos en el subárbol
     */
    private void postOrdenRecursivo(ArbolGeneral<T> nodo, ListaGenerica<T> res) {
        if (nodo.esVacio()) return;
        var hijos = nodo.getHijos();
        hijos.comenzar();
        while (!hijos.fin()) {
            postOrdenRecursivo(hijos.proximo(), res);
        }
        res.agregarFinal(nodo.getDato());
    }

    /**
     * Recorrido en inorden para árbol general.
     *
     * @return lista con los datos en orden inorden
     * @complexity O(n) donde n es el número total de nodos
     */
    public ListaGenerica<T> inOrden() {
        var resultado = new ListaEnlazadaGenerica<T>();
        if (!this.esVacio()) {
            inOrdenRecursivo(resultado);
        }
        return resultado;
    }

    /**
     * Metodo auxiliar para el recorrido inorden.
     *
     * @param listaResultado la lista resultado donde se almacenan los datos. No puede ser null.
     * @complexity O(n) donde n es el número de nodos en el subárbol
     */
    private void inOrdenRecursivo(ListaGenerica<T> listaResultado) {
        if (this.tieneHijos()) {
            var listaHijos = this.getHijos();
            listaHijos.comenzar();

            // Procesa primer hijo, si existe
            if (!listaHijos.fin()) {
                var primerHijo = listaHijos.proximo();
                primerHijo.inOrdenRecursivo(listaResultado);
            }

            // se visitar la raíz
            listaResultado.agregarFinal(this.getDato());

            // Procesa el resto de los hijos
            while (!listaHijos.fin()) {
                var hijoActual = listaHijos.proximo();
                hijoActual.inOrdenRecursivo(listaResultado);
            }
        } else {
            // Si es hoja, solo agregar el dato
            listaResultado.agregarFinal(this.getDato());
        }
    }

    /**
     * Recorrido por niveles (BFS) del árbol.
     * Visita todos los nodos nivel por nivel, de izquierda a derecha.
     *
     * @return lista con los datos recorridos por niveles
     * @complexity O(n) donde n es el número total de nodos
     */
    public ListaGenerica<T> porNiveles() {
        var resultado = new ListaEnlazadaGenerica<T>();
        if (this.esVacio()) return resultado;

        var cola = new ColaGenerica<ArbolGeneral<T>>();
        cola.encolar(this);

        while (!cola.esVacia()) {
            var nodo = cola.desencolar();
            resultado.agregarFinal(nodo.getDato());

            encolarHijos(nodo, cola);
        }
        return resultado;
    }

    /**
     * Recorrido en profundidad (DFS) del árbol.
     * Equivalente al recorrido preorden.
     *
     * @return lista con los datos recorridos en profundidad
     * @complexity O(n)
     */
    //    public ListaGenerica<T> porProfundidad() {
    //         return this.preOrden();
    //     }

    // ======================== PUNTO 6 ========================

    // ==================== PROPIEDADES MÉTRICAS =================

    /**
     * Obtiene la cantidad de hijos directos de este nodo (alias de getGrado).
     *
     * @return el número de hijos directos
     */
    //    public int getHijosCantidad() {
    //      return this.getGrado();
    //    }

    /**
     * Calcula la altura del árbol.
     * La altura es la longitud del camino más largo desde la raíz hasta una hoja.
     * por definición: altura de hoja = 0, altura de árbol vacío = -1.
     *
     * @return altura del árbol. Retorna -1 si el árbol está vacío, 0 si es hoja.
     * @complexity O(n) donde n es el número total de nodos
     */
    public int altura() {
        if (this.esVacio()) return -1;
        if (this.esHoja()) return 0;

        int maxAltura = 0;
        var hijos = this.getHijos();
        hijos.comenzar();

        while (!hijos.fin()) {
            int alturaHijo = hijos.proximo().altura();
            maxAltura = Math.max(maxAltura, alturaHijo);
        }

        return maxAltura + 1;
    }

    /**
     * Devuelve el nivel (profundidad) del nodo que contiene el dato buscado.
     * La raíz está en nivel 0. Utiliza recorrido BFS con early termination.
     *
     * @param dato valor a buscar. No puede ser null.
     * @return nivel del dato (0 para raíz), -1 si no existe en el árbol
     * @complexity O(n) - recorrido BFS en el peor caso
     */
    public int nivel(T dato) {
        if (this.esVacio()) return -1;

        var cola = new ColaGenerica<ArbolGeneral<T>>();
        cola.encolar(this);
        int nivel = 0;

        while (!cola.esVacia()) {
            int nodosEnNivel = cola.tamanio();

            // Procesa todos los nodos del nivel actual
            for (int i = 0; i < nodosEnNivel; i++) {
                var nodo = cola.desencolar();

                // Si se encuentra el dato
                if (nodo.getDato().equals(dato)) {
                    return nivel;
                }

                // Encola los hijos para el nivel siguiente
                encolarHijos(nodo, cola);
            }
            nivel++;
        }
        return -1; // si no es encontrado
    }

    /**
     * Devuelve el ancho del árbol (máxima cantidad de nodos que se encuentran en un mismo nivel). 0 si el árbol está vacío
     *
     * @return el ancho del árbol. Retorna 0 si el árbol está vacío.
     * @complexity O(n) - recorrido BFS
     */
    public int ancho() {
        if (this.esVacio()) return 0;

        var arbolEncolado = new ColaGenerica<ArbolGeneral<T>>();
        arbolEncolado.encolar(this);
        int maxAncho = 0;

        while (!arbolEncolado.esVacia()) {
            int nodosEnNivel = arbolEncolado.tamanio();
            if (nodosEnNivel > maxAncho) {
                maxAncho = nodosEnNivel;
            }
            // Procesa todos los nodos del nivel actual
            for (int i = 0; i < nodosEnNivel; i++) {
                var nodoActual = arbolEncolado.desencolar();
                if (nodoActual.tieneHijos()) {
                    var hijos = nodoActual.getHijos();
                    hijos.comenzar();
                    while (!hijos.fin()) {
                        arbolEncolado.encolar(hijos.proximo());
                    }
                }
            }
        }
        return maxAncho;
    }

    // ======================== RELACIONES ENTRE NODOS ========================

    /**
     * Verifica si el valor 'a' es ancestro del valor 'b' en el árbol.
     * Un nodo 'a' es ancestro de un nodo 'b' si existe un camino desde 'a' hasta 'b'.
     * NOTA: Por definición, un nodo NO es ancestro de sí mismo en esta implementación.
     *
     * @param a valor del posible nodo ancestro. No puede ser null.
     * @param b valor del posible nodo descendiente. No puede ser null.
     * @return true si 'a' es ancestro de 'b', false en caso contrario.
     * @throws IllegalArgumentException si alguno de los valores es null
     * @complexity O(n) donde n es el número total de nodos
     */
    public Boolean esAncestro(T a, T b) {
        if (esVacio() || a == null || b == null || a.equals(b)) {
            return false;
        }

        ArbolGeneral<T> nodoA = buscarNodo(a);
        if (nodoA == null) {
            return false;
        }

        return nodoA.contieneDescendiente(b);
    }

    // ======================== PROPIEDADES ESTRUCTURALES ========================

    /**
     * Determina si el árbol es lleno.
     * Un árbol es lleno si todos los nodos internos tienen el mismo grado (k)
     * y todas las hojas están en el mismo nivel.
     * <p>
     * Definición formal:
     * - Todos los nodos internos tienen exactamente k hijos
     * - Todas las hojas están en el nivel h (altura del árbol)
     * - No hay niveles con mezcla de nodos internos y hojas
     *
     * @return true si es lleno, false en caso contrario. Un árbol vacío retorna false.
     * @complexity O(n) donde n es el número total de nodos
     */
    public Boolean esArbolLleno() {
        if (this.esVacio()) return false;
        if (this.esHoja()) return true; // Un solo nodo es árbol lleno

        int gradoEsperado = this.getGradoActual();
        return esLlenoAux(gradoEsperado);
    }

    /**
     * Metodo auxiliar que verifica si es lleno usando un solo recorrido (BFS).
     *
     * @param gradoEsperado el grado que deben tener todos los nodos internos
     * @return true si cumple las condiciones de árbol lleno
     * @complexity O(n) donde n es el número total de nodos
     */
    private boolean esLlenoAux(int gradoEsperado) {
        var arbolEncolado = new ColaGenerica<ArbolGeneral<T>>();
        arbolEncolado.encolar(this);
        boolean hayHojas = false;
        boolean hayNodosInternos = false;

        while (!arbolEncolado.esVacia()) {
            int nodosEnNivel = arbolEncolado.tamanio();
            boolean nivelTieneHojas = false;
            boolean nivelTieneNodosInternos = false;

            for (int i = 0; i < nodosEnNivel; i++) {
                var nodo = arbolEncolado.desencolar();

                if (nodo.esHoja()) {
                    nivelTieneHojas = true;
                    // Si ya habíamos encontrado hojas en niveles anteriores, no es lleno
                    if (hayHojas) return false;
                } else {
                    nivelTieneNodosInternos = true;
                    // Si ya encontramos hojas antes, no es lleno
                    if (hayHojas) return false;
                    // Si el grado no coincide, no es lleno
                    if (nodo.getHijos().tamanio() != gradoEsperado) return false;

                    encolarHijos(nodo, arbolEncolado);
                }
            }

            // Verificar consistencia del nivel
            if (nivelTieneHojas && nivelTieneNodosInternos) {
                return false; // Nivel mixto que no es lleno
            }

            if (nivelTieneHojas) hayHojas = true;
            if (nivelTieneNodosInternos) hayNodosInternos = true;
        }
        return true;
    }

    /**
     * Determina si el árbol es completo.
     * Un árbol de grado (k) y altura (h) es completo si:
     * 1. Es lleno hasta el penúltimo nivel (h-1)
     * 2. El último nivel se llena de izquierda a derecha
     * <p>
     * En árboles generales, la propiedad "de izquierda a derecha" se interpreta
     * como que no debe haber "huecos" en el recorrido (BFS).
     *
     * @return true si es completo, false en caso contrario. Un árbol vacío retorna false.
     * @complexity O(n) donde n es el número total de nodos
     */
    public Boolean esArbolCompleto() {
        if (this.esVacio()) return false;
        if (this.esHoja()) return true;

        return esCompletoAux();
    }

    /**
     * Metodo auxiliar de verificación de árbol completo.
     * La clave está en verificar que no haya "huecos" en el llenado por niveles.
     * <p>
     * Algoritmo:
     * - Una vez que encuentra el primer nodo con menos hijos del esperado
     * - No debería encontrar más nodos con el número completo de hijos
     *
     * @return true si cumple las condiciones de árbol completo
     * @complexity O(n) donde n es el número total de nodos
     */
    private boolean esCompletoAux() {
        var cola = new ColaGenerica<ArbolGeneral<T>>();
        cola.encolar(this);

        int gradoEsperado = this.getGradoActual();
        boolean encontroNivelIncompleto = false;

        while (!cola.esVacia()) {
            int nodosEnNivel = cola.tamanio();
            boolean nivelTieneHojas = false;

            for (int i = 0; i < nodosEnNivel; i++) {
                var nodoActualNivel = cola.desencolar();
                int gradoNodo = nodoActualNivel.getHijos().tamanio();

                if (gradoNodo == 0) {
                    // Es hoja
                    nivelTieneHojas = true;
                    if (encontroNivelIncompleto) {
                        // Si ya encontramos nivel incompleto antes, no puede haber más hojas
                        return false;
                    }
                } else if (gradoNodo == gradoEsperado) {
                    // Nodo interno completo
                    if (nivelTieneHojas || encontroNivelIncompleto) {
                        // No puede haber nodos completos después de hojas o nodos incompletos
                        return false;
                    }
                    encolarHijos(nodoActualNivel, cola);
                } else if (gradoNodo < gradoEsperado) {
                    // Nodo interno incompleto
                    if (nivelTieneHojas || encontroNivelIncompleto) {
                        // Ya encontramos incompletitud antes
                        return false;
                    }
                    encontroNivelIncompleto = true;
                    encolarHijos(nodoActualNivel, cola);
                } else {
                    // gradoNodo > gradoEsperado - imposible en árbol completo
                    return false;
                }
            }
        }

        return true;
    }
}