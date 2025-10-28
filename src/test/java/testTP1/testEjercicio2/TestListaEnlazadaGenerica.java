package testTP1.testEjercicio2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.ejercicio2.ListaEnlazadaGenerica;

public class TestListaEnlazadaGenerica {

    private static void imprimir(ListaEnlazadaGenerica<Estudiante> lista) {
        if (lista.fin()) {
            return; // caso base
        }

        Estudiante e = lista.proximo();
        e.tusDatos();
        System.out.println("------------------------------");
        imprimir(lista);
    }

    @Test
    @DisplayName("Genera lista con diferentes métodos y la imprime")
    public void GeneraImprime() {

        var e1 = new Estudiante(1, "Bruno", "Dias");
        var e2 = new Estudiante(2, "Sergio", "Dias");
        var e3 = new Estudiante(3, "Julio", "Perez");
        var e4 = new Estudiante(4, "Lucia", "Martinez");

        var lista = new ListaEnlazadaGenerica<Estudiante>();

        lista.agregarInicio(e1);
        lista.agregarEn(e2, 2);
        lista.agregarEn(e3, 3);
        lista.agregarFinal(e4);

        System.out.println("\n=== LISTA DE USUARIOS ===");
        lista.comenzar();
        imprimir(lista);

        System.out.println("=== FIN RECORRIDO ===");
    }

    @DisplayName("clase Estudiante")
    private class Estudiante {
        private String apellido;
        private String nombre;
        private int legajo;

        public Estudiante(int legajo, String nombre, String apellido) {
            this.legajo = legajo;
            this.nombre = nombre;
            this.apellido = apellido;
        }

        public void tusDatos() {
            System.out.println("Legajo: " + this.legajo);
            System.out.println("Nombre: " + this.nombre);
            System.out.println("Apellido: " + this.apellido);
        }

        @Override
        public String toString() {
            return nombre + " " + apellido + "(" + legajo + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Estudiante that = (Estudiante) obj;
            return legajo == that.legajo;
        }
    }
}