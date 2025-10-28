package testTP1.testEjercicio1;

import tp1.ejercicio1.ListaDeEnterosEnlazada;

import java.util.Random;

public class TestListaDeEnterosEnlazada {
    public static void main(String[] args) {
        var lista = new ListaDeEnterosEnlazada();

        Integer primero = 9;
        Integer ultimo = 1;

        lista.agregarInicio(primero);

        Random random = new Random();
        for (int i = 2; i < 10; i++) {
            Integer valor = random.nextInt(100);
            lista.agregarEn(valor, i);
        }

        lista.agregarFinal(ultimo);

        imprimir(lista);

        var invertida = invertirLista(lista);

        imprimir(invertida);
    }

    private static ListaDeEnterosEnlazada invertirLista(ListaDeEnterosEnlazada lista) {
        if (lista.esVacia()) {
            return new ListaDeEnterosEnlazada(); // Caso base: si no hay elementos, no hay que invertir
        }

        Integer primero = lista.elemento(1); // primer elemento de la lista actual
        lista.eliminarEn(1); // Desacoplo primer elemento, reduce la lista original

        // recursion: invierto el RESTO de la lista, sin primer elemento
        ListaDeEnterosEnlazada invertida = invertirLista(lista);

        // volviendo de la recursion, agrego primero al FINAL de la lista invertida del RESTO.
        // el antiguo primer elemento termina al final
        invertida.agregarFinal(primero);

        return invertida;
    }

    private static void imprimir(ListaDeEnterosEnlazada lista) {
        lista.comenzar();
        while (!lista.fin()) {
            System.out.print(lista.proximo() + " ");
        }
        System.out.println();
    }
}
