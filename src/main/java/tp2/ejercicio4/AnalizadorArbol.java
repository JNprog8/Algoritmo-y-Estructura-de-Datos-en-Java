package tp2.ejercicio4;

import tp1.ejercicio2.ListaGenerica;
import tp1.ejercicio3.ColaGenerica;
import tp2.ejercicio1y6.ArbolGeneral;

/**
 * Analizador especializado para árboles de estructura empresarial.
 * clase que analizar un arbol calcula promedios por niveles
 * e identifica el nivel con el promedio más alto.
 */
public class AnalizadorArbol {

    /**
     * Calcula y devuelve el mayor promedio de duración entre todos los niveles del árbol.
     * Utiliza recorrido por niveles para procesar cada nivel de forma independiente,
     * calculando el promedio de duración de transmisión de mensajes para cada nivel
     * y retornando el máximo encontrado. Imprime el nivel y el promedio más alto.
     *
     * @param arbol que representa la estructura jerárquica de la empresa
     * @return el promedio máximo de duración encontrado entre todos los niveles, o 0 si el árbol es nulo o vacío
     */
    public int devolverMaximoPromedio(ArbolGeneral<AreaEmpresa> arbol) {
        int maxPromedio = -1;
        int nivelConMaximoPromedio = -1;

        // Verifica si el árbol es nulo o está vacio
        if (!arbol.esVacio()) {
            // Se usa la ColaGenerica para el recorrido por niveles
            ColaGenerica<ArbolGeneral<AreaEmpresa>> arbolEncolado = new ColaGenerica<>();
            arbolEncolado.encolar(arbol);

            int nivelActual = 0;

            // Bucle para procesar cada nivel del arbol
            while (!arbolEncolado.esVacia()) {
                int nodosNivel = arbolEncolado.tamanio();
                int sumaElementos = 0;

                // Bucle que procesa todos los nodos del nivel actual
                for (int i = 0; i < nodosNivel; i++) {
                    ArbolGeneral<AreaEmpresa> nodoActual = arbolEncolado.desencolar();
                    sumaElementos += nodoActual.getDato().getDuration();

                    // Encola los hijos del nodo actual, para el próximo nivel
                    ListaGenerica<ArbolGeneral<AreaEmpresa>> hijos = nodoActual.getHijos();
                    hijos.comenzar();
                    while (!hijos.fin()) {
                        arbolEncolado.encolar(hijos.proximo());
                    }
                }

                // Calcula promedio del nivel actual
                int promedioNivel = sumaElementos / nodosNivel;

                // Actualiza al promedio más alto, si el del nivel actual es mayor
                if (promedioNivel > maxPromedio) {
                    maxPromedio = promedioNivel;
                    nivelConMaximoPromedio = nivelActual;
                }
                nivelActual++;
            }
            // Llama al metodo privado para imprimir el resultado
            imprimirResultado(nivelConMaximoPromedio, maxPromedio);
        }


        return maxPromedio;
    }

    /**
     * Metodo privado para imprimir el nivel y el promedio más alto.
     *
     * @param nivel    El nivel con el promedio más alto.
     * @param promedio El promedio más alto.
     */
    private void imprimirResultado(int nivel, int promedio) {
        System.out.println("El nivel con el promedio más alto es el " + nivel + " con un promedio de " + promedio + ".");
    }
}