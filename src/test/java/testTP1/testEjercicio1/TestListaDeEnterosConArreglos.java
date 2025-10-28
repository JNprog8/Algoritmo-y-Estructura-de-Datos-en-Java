package testTP1.testEjercicio1;

import tp1.ejercicio1.ListaDeEnterosConArreglos;

public class TestListaDeEnterosConArreglos {
    public static void main(String[] args) {
        var lista = new ListaDeEnterosConArreglos();

        for (int i = 1; i <= 10; i++) {
            Integer numero = i;
            lista.agregarFinal(numero);
        }

        imprimir(lista);

        ListaDeEnterosConArreglos invertida = invertirLista(lista);

        imprimir(invertida);
    }

    private static ListaDeEnterosConArreglos invertirLista(ListaDeEnterosConArreglos lista) {
        if (lista.esVacia()) {
            return new ListaDeEnterosConArreglos(); // Caso base lista vacia
        }

        Integer primero = lista.elemento(1); //Tomo el primer elemento
        lista.eliminarEn(1); //  Lo elimino

        ListaDeEnterosConArreglos invertida = invertirLista(lista); // Llamo recursivamente para invertir el resto.
        invertida.agregarFinal(primero); // Agrego el elemento al final de la invertida.

        return invertida;
    }

    private static void imprimir(ListaDeEnterosConArreglos lista) {
        lista.comenzar();
        while (!lista.fin()) {
            System.out.print(lista.proximo() + " ");
        }
        System.out.println("\n");
    }
}