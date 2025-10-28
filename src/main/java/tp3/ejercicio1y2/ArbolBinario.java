package tp3.ejercicio1y2;

import tp1.ejercicio3.ColaGenerica;

public class ArbolBinario<T> {
    private T dato;
    private ArbolBinario<T> hijoIzquierdo;
    private ArbolBinario<T> hijoDerecho;

    public ArbolBinario() {
        super();
    }

    public ArbolBinario(T dato) {
        this.dato = dato;
    }

    /*
     * getters y setters
     */
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Preguntar antes de invocar si tieneHijoIzquierdo()
     *
     * @return
     */
    public ArbolBinario<T> getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public ArbolBinario<T> getHijoDerecho() {
        return this.hijoDerecho;
    }

    public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
        this.hijoIzquierdo = hijo;
    }

    public void agregarHijoDerecho(ArbolBinario<T> hijo) {
        this.hijoDerecho = hijo;
    }

    public void eliminarHijoIzquierdo() {
        this.hijoIzquierdo = null;
    }

    public void eliminarHijoDerecho() {
        this.hijoDerecho = null;
    }

    public boolean esVacio() {
        return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
    }

    public boolean esHoja() {
        return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());
    }

    @Override
    public String toString() {
        return this.getDato().toString();
    }


    public boolean tieneHijoIzquierdo() {
        return this.hijoIzquierdo != null;
    }


    public boolean tieneHijoDerecho() {
        return this.hijoDerecho != null;
    }

    // ==================== PUNTO 2 ====================

    /**
     * @return Devuelve la cantidad de árbol/subárbol hojas del árbol receptor.
     */
    public int contarHojas() {
        if (this.esVacio()) {
            return 0;
        }

        int hojasIzq = 0;
        int hojasDer = 0;

        if (this.esHoja()) {
            return 1;
        }

        if (this.tieneHijoIzquierdo()) {
            hojasIzq = this.hijoIzquierdo.contarHojas();
        }

        if (this.tieneHijoDerecho()) {
            hojasDer = this.hijoDerecho.contarHojas();
        }

        return hojasIzq + hojasDer;
    }

    /**
     * @return Devuelve el árbol binario espejo del árbol receptor
     */
    public ArbolBinario<T> espejo() {
        if (this.esVacio()) {
            return new ArbolBinario<>();
        }

        ArbolBinario<T> arbolEspejo = new ArbolBinario<>(this.getDato());

        if (this.tieneHijoIzquierdo()) {
            arbolEspejo.agregarHijoDerecho(this.getHijoIzquierdo().espejo());
        }

        if (this.tieneHijoDerecho()) {
            arbolEspejo.agregarHijoIzquierdo(this.getHijoDerecho().espejo());
        }

        return arbolEspejo;
    }

    /**
     * Imprime por consola el recorrido por
     * niveles de los elementos del árbol receptor entre los niveles n y m (ambos inclusive).
     * (0≤ n < m ≤ altura del árbol)
     *
     * @param n Nivel inicial (inclusive).
     * @param m Nivel final (inclusive).
     */
    public void entreNiveles(int n, int m) {

        if (m < n || n < 0) {
            throw new IllegalArgumentException("El nivel 'm' debe ser mayor o igual a 'n', y 'n' no debe ser negativo.");
        }

        int nivelActual = 0; // nivel raíz = 0
        var colaDeNodos = new ColaGenerica<ArbolBinario<T>>();
        colaDeNodos.encolar(this); // Encolar nodo raíz.

        // Usaremos un separador (como 'null') para marcar el final de cada nivel.
        colaDeNodos.encolar(null);

        // 3. Recorrido BFS (Nivel a Nivel)
        while (nivelActual <= m) {
            var actual = colaDeNodos.desencolar();

            if (actual != null) {

                // a) Imprimir solo si estamos en el rango [n, m]
                if (nivelActual >= n && nivelActual <= m) {
                    System.out.print(actual. + " ");
                }

                // b) Encolar hijos para el siguiente nivel
                if (actual.tieneHijoIzquierdo()) {
                    colaDeNodos.encolar(actual.getHijoIzquierdo());
                }
                if (actual.tieneHijoDerecho()) {
                    colaDeNodos.encolar(actual.getHijoDerecho());
                }

            } else {
                // El separador 'null' indica que terminamos un nivel

                if (!colaDeNodos.estaVacia()) {
                    // Hay más nodos, es decir, existe un siguiente nivel
                    nivelActual++;

                    // Si el nuevo nivel 'nivelActual' ya superó 'm',
                    // detenemos el recorrido para ahorrar tiempo.
                    if (nivelActual > m) {
                        break;
                    }

                    // Agregamos el separador para el NUEVO nivel
                    colaDeNodos.encolar(null);

                    // Saltos de línea o formato después de cada nivel impreso
                    if (nivelActual > n) {
                        System.out.println(); // Opcional, para mejor formato de salida
                    }
                }
            }
        }
        System.out.println(); // Limpieza de formato al final
    }
}
