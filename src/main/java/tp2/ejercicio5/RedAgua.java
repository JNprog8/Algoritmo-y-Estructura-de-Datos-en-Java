package tp2.ejercicio5;

import tp1.ejercicio2.ListaGenerica;
import tp2.ejercicio1y6.ArbolGeneral;

/**
 * Red de agua potable que representa la distribución de caudal
 * desde un caño maestro hasta las casas finales.
 * El caudal se divide equitativamente entre todas las bifurcaciones.
 */
public class RedAgua {
    private ArbolGeneral<CaudalNodo> redRaiz;
    private double minimoCaudalEncontrado;

    public RedAgua(ArbolGeneral<CaudalNodo> arbol) {
        this.redRaiz = arbol;
        this.minimoCaudalEncontrado = Double.MAX_VALUE;
    }

    private static int getCantidadBifurcaciones(ArbolGeneral<CaudalNodo> arbolActual) {
        return arbolActual.getHijos().tamanio();
    }

    private static double getCaudal(ArbolGeneral<CaudalNodo> arbolActual) {
        return arbolActual.getDato().getCaudal();
    }

    private static boolean esCasa(ArbolGeneral<CaudalNodo> arbolActual) {
        return arbolActual.esHoja();
    }

    public double minimoCaudal(double caudalInicial) {
        if (redRaiz.esVacio()) {
            return 0.0;
        }

        minimoCaudalEncontrado = Double.MAX_VALUE;
        minimoCaudalRecorrido(redRaiz, caudalInicial);

        return minimoCaudalEncontrado;
    }

    /**
     * Metodo auxiliar recursivo para el recorrido en profundidad (DFS).
     *
     * @param arbolActual  El subárbol que se está visitando.
     * @param caudalActual El caudal que llega al nodo raíz de este subárbol.
     */
    private void minimoCaudalRecorrido(ArbolGeneral<CaudalNodo> arbolActual, double caudalActual) {
        // Asigna caudal al nodo actual
        arbolActual.getDato().setCaudal(caudalActual);

        // Si es una hoja (una casa)
        if (esCasa(arbolActual)) {
            // actualizo el caudal mínimo
            if (getCaudal(arbolActual) < minimoCaudalEncontrado) {
                minimoCaudalEncontrado = getCaudal(arbolActual);
            }
        } else {
            // tiene hijos, divide el caudal por la cantidad de hijos
            int cantidadHijos = getCantidadBifurcaciones(arbolActual);
            double nuevoCaudal = caudalActual / cantidadHijos; // nuevo caudal para las bifurcaciones de los hijos

            // lista de hijos para continuar las bifurcaciones
            ListaGenerica<ArbolGeneral<CaudalNodo>> hijos = arbolActual.getHijos();
            hijos.comenzar();
            // llamado recursivo para cada hijo
            while (!hijos.fin()) {
                // nodosHijo a nodo Hijo pregunta por sus hijos
                ArbolGeneral<CaudalNodo> hijo = hijos.proximo();
                minimoCaudalRecorrido(hijo, nuevoCaudal);
            }
        }
    }
}