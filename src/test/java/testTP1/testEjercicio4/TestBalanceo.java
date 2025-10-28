package testTP1.testEjercicio4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tp1.ejercicio4.Balanceo.isBalanceado;

/**
 * Las pruebas unitarias deben ser automatizadas y, para ser efectivas,
 * deben cumplir con el principio F.I.R.S.T.:
 * <p>
 * Fast (Rápido):
 * Las pruebas deben ejecutarse rápidamente.
 * <p>
 * Isolated (Aislado):
 * Cada prueba debe ser independiente y no depender del orden o del estado de otras pruebas.
 * <p>
 * Repeatable (Repetible):
 * Los resultados deben ser consistentes cada vez que se ejecuten.
 * <p>
 * Self-validating (Autovalidante):
 * La prueba debe indicar su propio resultado (pasa/falla) sin intervención manual. Esto se logra con asserts.
 * <p>
 * Timely (Oportuno):
 * Deben escribirse al mismo tiempo que el código a probar (o incluso antes con TDD).
 */
@DisplayName("Test de la clase Balanceo")
class TestBalanceo {

    @Nested
    @DisplayName("Casos de prueba para strings balanceados")
    class TestBalanceados {

        @Test
        @DisplayName("Debe devolver true para un string vacío")
        void testCadenaVaciaEsBalanceada() {
            assertTrue(isBalanceado(""));
        }

        @Test
        @DisplayName("Debe devolver true para un par simple de paréntesis")
        void testParSimpleParentesis() {
            assertTrue(isBalanceado("()"));
        }

        @Test
        @DisplayName("Debe devolver true para un par simple de corchetes")
        void testParSimpleCorchetes() {
            assertTrue(isBalanceado("[]"));
        }

        @Test
        @DisplayName("Debe devolver true para un par simple de llaves")
        void testParSimpleLlaves() {
            assertTrue(isBalanceado("{}"));
        }

        @ParameterizedTest
        @ValueSource(strings = {"()[]{}", "{()[()]([])}", "([{}])", "([{}])()", "([{}])({})"})
        @DisplayName("Debe devolver true para strings con anidamiento y concatenación válidos")
        void testMultiplesStringsBalanceados(String cadena) {
            assertTrue(isBalanceado(cadena));
        }
    }

    @Nested
    @DisplayName("Casos de prueba para strings NO balanceados")
    class TestNoBalanceados {

        @Test
        @DisplayName("Debe devolver false para un string con paréntesis cruzados")
        void testParentesisCruzados() {
            assertFalse(isBalanceado("([)]"));
        }

        @Test
        @DisplayName("Debe devolver false para un string con apertura sin cierre")
        void testSoloAperturas() {
            assertFalse(isBalanceado("((("));
        }

        @Test
        @DisplayName("Debe devolver false para un string con cierre sin apertura")
        void testSoloCierres() {
            assertFalse(isBalanceado(")))"));
        }

        @Test
        @DisplayName("Debe devolver false para un string con un orden incorrecto")
        void testOrdenIncorrecto() {
            assertFalse(isBalanceado("({[}])"));
        }

        @ParameterizedTest
        @ValueSource(strings = {"{([)()]}", "{ ( [ ) ( ) ] }", "() ]"})
        @DisplayName("Debe devolver false para strings con errores de balanceo")
        void testMultiplesStringsNoBalanceados(String cadena) {
            assertFalse(isBalanceado(cadena));
        }
    }

    @Nested
    @DisplayName("Casos de prueba con entradas inválidas")
    class TestEntradasInvalidas {

        @Test
        @DisplayName("Debe devolver false para un string con caracteres no permitidos")
        void testCaracteresInvalidos() {
            assertFalse(isBalanceado("abc"));
        }

        @Test
        @DisplayName("Debe devolver false para un string con caracteres intermedios")
        void testCaracteresIntermedios() {
            assertFalse(isBalanceado("(a)"));
        }

        @Test
        @DisplayName("Debe devolver false para una entrada nula")
        void testEntradaNula() {
            assertFalse(isBalanceado(null));
        }
    }
}