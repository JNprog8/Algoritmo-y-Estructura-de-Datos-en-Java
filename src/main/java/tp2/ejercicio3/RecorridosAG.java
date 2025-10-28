package tp2.ejercicio3;

import tp1.ejercicio2.ListaEnlazadaGenerica;
import tp1.ejercicio2.ListaGenerica;
import tp1.ejercicio3.ColaGenerica;
import tp1.ejercicio3.PilaGenerica;
import tp2.ejercicio1y6.ArbolGeneral;


public class RecorridosAG {

    /**
     * recorrido preorden se visita primero la raíz, luego recursivamente
     * todos los subárboles hijos de izquierda a derecha.
     *
     * @param arbol
     * @param <T>
     * @return lista con los elementos del árbol en orden preOrden
     */
    public <T> ListaGenerica<T> preOrden(ArbolGeneral<T> arbol) {
        ListaGenerica<T> lista = new ListaEnlazadaGenerica<>();
        if (!arbol.esVacio()) {
            lista.agregarFinal(arbol.getDato()); // primero se procesar raíz
            arbol.getHijos().comenzar();
            while (!arbol.getHijos().fin()) {
                ArbolGeneral<T> hijo = arbol.getHijos().proximo();
                // Recursión
                ListaGenerica<T> subRecorrido = preOrden(hijo);
                // Agregar elemento del subrecorrido a la lista principal
                subRecorrido.comenzar();
                while (!subRecorrido.fin()) {
                    lista.agregarFinal(subRecorrido.proximo());
                }
            }
        }
        return lista;
    }

    /**
     *
     * @param arbol
     * @param <T>
     * @return lista con los elementos del árbol en orden inorden
     */
    public <T> ListaGenerica<T> inOrden(ArbolGeneral<T> arbol) {
        ListaGenerica<T> lista = new ListaEnlazadaGenerica<>();
        if (!arbol.esVacio()) {
            arbol.getHijos().comenzar();
            if (!arbol.getHijos().fin()) {
                // Procesar primer hijo
                ArbolGeneral<T> primerHijo = arbol.getHijos().proximo();
                ListaGenerica<T> subRecorrido = inOrden(primerHijo);
                subRecorrido.comenzar();
                while (!subRecorrido.fin()) {
                    lista.agregarFinal(subRecorrido.proximo());
                }
            }
            lista.agregarFinal(arbol.getDato()); // procesar raíz
            while (!arbol.getHijos().fin()) { // el resto de hijos
                ArbolGeneral<T> hijo = arbol.getHijos().proximo();
                ListaGenerica<T> subRecorrido = inOrden(hijo);
                subRecorrido.comenzar();
                while (!subRecorrido.fin()) {
                    lista.agregarFinal(subRecorrido.proximo());
                }
            }
        }
        return lista;
    }

    /**
     *
     * @param arbol
     * @param <T>
     * @return lista con los elementos del árbol en orden postorden
     */
    public <T> ListaGenerica<T> postOrden(ArbolGeneral<T> arbol) {
        ListaGenerica<T> lista = new ListaEnlazadaGenerica<>();
        if (!arbol.esVacio()) {
            arbol.getHijos().comenzar();
            while (!arbol.getHijos().fin()) {
                ArbolGeneral<T> hijo = arbol.getHijos().proximo();
                ListaGenerica<T> subRecorrido = postOrden(hijo);
                subRecorrido.comenzar();
                while (!subRecorrido.fin()) {
                    lista.agregarFinal(subRecorrido.proximo());
                }
            }
            lista.agregarFinal(arbol.getDato()); // procesar raíz al final
        }
        return lista;
    }

    /**
     *
     * @param arbol
     * @param <T>
     * @return lista con los elementos del árbol ordenados por niveles
     */
    public <T> ListaGenerica<T> porNiveles(ArbolGeneral<T> arbol) {
        ListaGenerica<T> lista = new ListaEnlazadaGenerica<>();
        if (!arbol.esVacio()) {
            ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<>();
            cola.encolar(arbol);
            while (!cola.esVacia()) {
                ArbolGeneral<T> actual = cola.desencolar();
                lista.agregarFinal(actual.getDato());
                actual.getHijos().comenzar();
                while (!actual.getHijos().fin()) {
                    cola.encolar(actual.getHijos().proximo());
                }
            }
        }
        return lista;
    }

    /**
     *
     * @param arbol
     * @param <T>
     * @return lista con los elementos del árbol en orden de profundidad
     */
    public <T> ListaGenerica<T> porProfundidad(ArbolGeneral<T> arbol) {
        ListaGenerica<T> lista = new ListaEnlazadaGenerica<>();
        if (!arbol.esVacio()) {
            PilaGenerica<ArbolGeneral<T>> pila = new PilaGenerica<>();
            pila.apilar(arbol);
            while (!pila.esVacia()) {
                ArbolGeneral<T> actual = pila.desapilar();
                lista.agregarFinal(actual.getDato());

                // Apilar hijos en orden inverso para mantener orden natural
                ListaGenerica<ArbolGeneral<T>> hijosInverso = new ListaEnlazadaGenerica<>();
                actual.getHijos().comenzar();
                while (!actual.getHijos().fin()) {
                    hijosInverso.agregarInicio(actual.getHijos().proximo());
                }

                hijosInverso.comenzar();
                while (!hijosInverso.fin()) {
                    pila.apilar(hijosInverso.proximo());
                }
            }
        }
        return lista;
    }

    /**
     * Metodo que retorna una lista con los elementos impares del árbol “a” que sean
     * mayores al valor “n” pasados como parámetros, recorrido en preorden.
     *
     * @param a el árbol general de enteros a recorrer
     * @param n el valor límite para filtrar los números
     * @return una lista con los elementos impares mayores a n
     */
    public ListaGenerica<Integer> imparesMayoresPreOrden(ArbolGeneral<Integer> arbol, int n) {
        ListaGenerica<Integer> recorrido = preOrden(arbol);
        return filtrarImparesMayores(recorrido, n);
    }

    /**
     * Metodo que retorna una lista con los elementos impares del árbol “a” que sean
     * mayores al valor “n” pasados como parámetros, recorrido en inorden.
     *
     * @param a el árbol general de enteros a recorrer
     * @param n el valor límite para filtrar los números
     * @return una lista con los elementos impares mayores a n
     */
    public ListaGenerica<Integer> imparesMayoresInOrden(ArbolGeneral<Integer> arbol, int n) {
        ListaGenerica<Integer> recorrido = inOrden(arbol);
        return filtrarImparesMayores(recorrido, n);
    }

    /**
     * Metodo que retorna una lista con los elementos impares del árbol “a” que sean
     * mayores al valor “n” pasados como parámetros recorrido en postorden.
     *
     * @param a el árbol general de enteros a recorrer
     * @param n el valor límite para filtrar los números
     * @return una lista con los elementos impares mayores a n
     */
    public ListaGenerica<Integer> imparesMayoresPostOrden(ArbolGeneral<Integer> arbol, int n) {
        ListaGenerica<Integer> recorrido = postOrden(arbol);
        return filtrarImparesMayores(recorrido, n);
    }

    /**
     *
     * @param arbol
     * @param n
     * @return
     */
    public ListaGenerica<Integer> imparesMayoresPorNiveles(ArbolGeneral<Integer> arbol, int n) {
        ListaGenerica<Integer> recorrido = porNiveles(arbol);
        return filtrarImparesMayores(recorrido, n);
    }

    /**
     *
     * @param arbol
     * @param n
     * @return
     */
    public ListaGenerica<Integer> imparesMayoresPorProfundidad(ArbolGeneral<Integer> arbol, int n) {
        ListaGenerica<Integer> recorrido = porProfundidad(arbol);
        return filtrarImparesMayores(recorrido, n);
    }

    // Metodo auxiliar para filtrar impares mayores a n
    private ListaGenerica<Integer> filtrarImparesMayores(ListaGenerica<Integer> lista, int n) {
        ListaGenerica<Integer> resultado = new ListaEnlazadaGenerica<>();
        lista.comenzar();
        while (!lista.fin()) {
            Integer dato = lista.proximo();
            if (dato % 2 != 0 && dato > n) {
                resultado.agregarFinal(dato);
            }
        }
        return resultado;
    }
}
