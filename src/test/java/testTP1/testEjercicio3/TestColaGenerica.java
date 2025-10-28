package testTP1.testEjercicio3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tp1.ejercicio3.ColaGenerica;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test de la clase ColaGenerica (FIFO)")
public class TestColaGenerica {

    private ColaGenerica<String> cola;

    // Se ejecuta antes de cada prueba para asegurar un estado inicial limpio
    @BeforeEach
    void setUp() {
        cola = new ColaGenerica<>();
    }

    @Nested
    @DisplayName("Tests para cola vacía")
    class TestColaVacia {
        @Test
        @DisplayName("Debe estar vacía al ser creada")
        void testColaCreadaEsVacia() {
            assertTrue(cola.esVacia());
            assertEquals(0, cola.tamanio());
        }

        @Test
        @DisplayName("El tope de una cola vacía debe ser null")
        void testTopeColaVaciaEsNull() {
            assertNull(cola.tope());
        }

        @Test
        @DisplayName("Desencolar de una cola vacía debe ser null")
        void testDesencolarColaVaciaEsNull() {
            assertNull(cola.desencolar());
        }
    }

    @Nested
    @DisplayName("Tests para operaciones de encolar")
    class TestEncolar {

        @Test
        @DisplayName("Debe encolar un elemento correctamente")
        void testEncolarUnElemento() {
            cola.encolar("Primero");
            assertFalse(cola.esVacia());
            assertEquals(1, cola.tamanio());
            assertEquals("Primero", cola.tope());
        }

        @Test
        @DisplayName("Debe encolar varios elementos y el tope debe ser el primero")
        void testEncolarVariosElementos() {
            cola.encolar("Primero");
            cola.encolar("Segundo");
            cola.encolar("Tercero");

            assertEquals("Primero", cola.tope());
            assertEquals(3, cola.tamanio());
        }
    }

    @Nested
    @DisplayName("Tests para operaciones de desencolar")
    class TestDesencolar {

        @BeforeEach
        void setUpConCola() {
            cola.encolar("Primero");
            cola.encolar("Segundo");
            cola.encolar("Tercero");
        }

        @Test
        @DisplayName("Debe desencolar el primer elemento encolado")
        void testDesencolar() {
            String primero = cola.desencolar();
            assertEquals("Primero", primero);
            assertEquals("Segundo", cola.tope());
            assertEquals(2, cola.tamanio());
        }

        @Test
        @DisplayName("Debe desencolar todos los elementos y quedar vacía")
        void testDesencolarTodos() {
            cola.desencolar();
            cola.desencolar();
            cola.desencolar();

            assertTrue(cola.esVacia());
            assertEquals(0, cola.tamanio());
            assertNull(cola.tope());
        }
    }
}