package testTP1.testEjercicio3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tp1.ejercicio3.PilaGenerica;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test de la clase PilaGenerica (LIFO)")
public class TestPilaGenerica {

    private PilaGenerica<String> pila;

    // Se ejecuta antes de cada método de prueba para asegurar un estado limpio
    @BeforeEach
    void setUp() {
        pila = new PilaGenerica<>();
    }

    @Nested
    @DisplayName("Tests para pila vacía")
    class TestPilaVacia {

        @Test
        @DisplayName("Debe estar vacía al ser creada")
        void testPilaCreadaEsVacia() {
            assertTrue(pila.esVacia());
        }

        @Test
        @DisplayName("El tope de una pila vacía debe ser null")
        void testTopePilaVaciaEsNull() {
            assertNull(pila.tope());
        }

        @Test
        @DisplayName("Desapilar de una pila vacía debe ser null")
        void testDesapilarPilaVaciaEsNull() {
            assertNull(pila.desapilar());
        }
    }

    @Nested
    @DisplayName("Tests para operaciones de apilar")
    class TestApilar {

        @Test
        @DisplayName("Debe apilar un elemento correctamente")
        void testApilarUnElemento() {
            pila.apilar("Elemento1");
            assertFalse(pila.esVacia());
            assertEquals("Elemento1", pila.tope());
        }

        @Test
        @DisplayName("Debe apilar varios elementos y el tope debe ser el último")
        void testApilarVariosElementos() {
            pila.apilar("Primero");
            pila.apilar("Segundo");
            pila.apilar("Tercero");

            assertEquals("Tercero", pila.tope());
            assertEquals(3, pila.tamanio());
        }
    }

    @Nested
    @DisplayName("Tests para operaciones de desapilar")
    class TestDesapilar {

        @BeforeEach
        void setUpConPila() {
            pila.apilar("Primero");
            pila.apilar("Segundo");
            pila.apilar("Tercero");
        }

        @Test
        @DisplayName("Debe desapilar el elemento del tope")
        void testDesapilarTope() {
            assertEquals("Tercero", pila.desapilar());
            assertEquals("Segundo", pila.tope());
            assertEquals(2, pila.tamanio());
        }

        @Test
        @DisplayName("Debe desapilar todos los elementos y quedar vacía")
        void testDesapilarTodos() {
            pila.desapilar();
            pila.desapilar();
            pila.desapilar();

            assertTrue(pila.esVacia());
            assertNull(pila.tope());
        }
    }
}
