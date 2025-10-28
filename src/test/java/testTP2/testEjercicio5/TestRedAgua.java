package testTP2.testEjercicio5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp2.ejercicio1y6.ArbolGeneral;
import tp2.ejercicio5.CaudalNodo;
import tp2.ejercicio5.RedAgua;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRedAgua {

    private RedAgua redDeAguaPrincipal;

    /**
     * Crea un ArbolGeneral que representa la red de agua de la imagen del problema.
     *
     * @return El ArbolGeneral completo.
     */
    private static ArbolGeneral<CaudalNodo> createRedAguaPotable() {
        // Nivel 0: A (Raíz)
        CaudalNodo nodoA = new CaudalNodo("A", 0);
        ArbolGeneral<CaudalNodo> arbolA = new ArbolGeneral<>(nodoA);

        // Nivel 1: B, C, D, E
        ArbolGeneral<CaudalNodo> arbolB = new ArbolGeneral<>(new CaudalNodo("B", 0));
        ArbolGeneral<CaudalNodo> arbolC = new ArbolGeneral<>(new CaudalNodo("C", 0));
        ArbolGeneral<CaudalNodo> arbolD = new ArbolGeneral<>(new CaudalNodo("D", 0));
        ArbolGeneral<CaudalNodo> arbolE = new ArbolGeneral<>(new CaudalNodo("E", 0));
        arbolA.agregarHijo(arbolB);
        arbolA.agregarHijo(arbolC);
        arbolA.agregarHijo(arbolD);
        arbolA.agregarHijo(arbolE);

        // Nivel 2: F, G (hijos de C)
        ArbolGeneral<CaudalNodo> arbolF = new ArbolGeneral<>(new CaudalNodo("F", 0));
        ArbolGeneral<CaudalNodo> arbolG = new ArbolGeneral<>(new CaudalNodo("G", 0));
        arbolC.agregarHijo(arbolF);
        arbolC.agregarHijo(arbolG);

        // Nivel 3: L (hijo de G)
        arbolG.agregarHijo(new ArbolGeneral<>(new CaudalNodo("L", 0)));

        // Nivel 2: H, I, J, K (hijos de D)
        ArbolGeneral<CaudalNodo> arbolH = new ArbolGeneral<>(new CaudalNodo("H", 0));
        ArbolGeneral<CaudalNodo> arbolI = new ArbolGeneral<>(new CaudalNodo("I", 0));
        ArbolGeneral<CaudalNodo> arbolJ = new ArbolGeneral<>(new CaudalNodo("J", 0));
        ArbolGeneral<CaudalNodo> arbolK = new ArbolGeneral<>(new CaudalNodo("K", 0));
        arbolD.agregarHijo(arbolH);
        arbolD.agregarHijo(arbolI);
        arbolD.agregarHijo(arbolJ);
        arbolD.agregarHijo(arbolK);

        // Nivel 3: M, N (hijos de J)
        arbolJ.agregarHijo(new ArbolGeneral<>(new CaudalNodo("M", 0)));
        arbolJ.agregarHijo(new ArbolGeneral<>(new CaudalNodo("N", 0)));

        return arbolA;
    }

    @BeforeEach
    public void setUp() {
        this.redDeAguaPrincipal = new RedAgua(createRedAguaPotable());
    }

    @Test
    public void testMinimoCaudalConCaudal1000() {
        // Act
        double minimoCaudal = redDeAguaPrincipal.minimoCaudal(1000.0);

        // Assert
        assertEquals(25.0, minimoCaudal, 0.001, "El caudal mínimo para el árbol principal debe ser 25.0");
    }

    @Test
    public void testMinimoCaudalConArbolDeUnNodo() {
        // Arrange
        ArbolGeneral<CaudalNodo> arbolUnico = new ArbolGeneral<>(new CaudalNodo("Unico", 0));
        RedAgua redUnica = new RedAgua(arbolUnico);
        double caudalInicial = 500.0;

        // Act
        double minimoCaudal = redUnica.minimoCaudal(caudalInicial);

        // Assert
        assertEquals(500.0, minimoCaudal, 0.001, "El caudal mínimo debe ser el inicial si es un solo nodo");
    }

    @Test
    public void testMinimoCaudalConArbolVacio() {
        // Arrange - Crear un árbol vacío correctamente
        ArbolGeneral<CaudalNodo> arbolVacio = new ArbolGeneral<>(null);  // Constructor sin parámetros para árbol vacío
        RedAgua redVacia = new RedAgua(arbolVacio);
        double caudalInicial = 1000.0;

        // Act & Assert
        assertEquals(0.0, redVacia.minimoCaudal(caudalInicial), 0.001, "El caudal mínimo debe ser 0.0 para un árbol vacío");
    }

    @Test
    public void testMinimoCaudalConArbolNulo() {
        // Act & Assert - El constructor debería manejar null o lanzar excepción
        assertThrows(NullPointerException.class, () -> {
            RedAgua redNula = new RedAgua(null);
            redNula.minimoCaudal(1000.0);
        }, "Debería lanzar NullPointerException con árbol nulo");
    }

    @Test
    public void testCaudalDistribucionCorrecta() {
        // Test adicional para verificar la distribución de caudal
        // Arrange
        ArbolGeneral<CaudalNodo> arbolSimple = new ArbolGeneral<>(new CaudalNodo("Raiz", 0));
        ArbolGeneral<CaudalNodo> hijo1 = new ArbolGeneral<>(new CaudalNodo("Casa1", 0));
        ArbolGeneral<CaudalNodo> hijo2 = new ArbolGeneral<>(new CaudalNodo("Casa2", 0));
        arbolSimple.agregarHijo(hijo1);
        arbolSimple.agregarHijo(hijo2);

        RedAgua redSimple = new RedAgua(arbolSimple);
        double caudalInicial = 100.0;

        // Act
        double minimoCaudal = redSimple.minimoCaudal(caudalInicial);

        // Assert
        assertEquals(50.0, minimoCaudal, 0.001, "El caudal debe dividirse equitativamente: 100/2 = 50");
    }

    @Test
    public void testCaudalConDiferentesNiveles() {
        // Test para verificar cálculo con diferentes profundidades
        // Arrange
        ArbolGeneral<CaudalNodo> raiz = new ArbolGeneral<>(new CaudalNodo("Raiz", 0));
        ArbolGeneral<CaudalNodo> intermedio = new ArbolGeneral<>(new CaudalNodo("Intermedio", 0));
        ArbolGeneral<CaudalNodo> casa1 = new ArbolGeneral<>(new CaudalNodo("Casa1", 0));
        ArbolGeneral<CaudalNodo> casa2 = new ArbolGeneral<>(new CaudalNodo("Casa2", 0));
        ArbolGeneral<CaudalNodo> casa3 = new ArbolGeneral<>(new CaudalNodo("Casa3", 0));

        raiz.agregarHijo(intermedio);
        raiz.agregarHijo(casa3);  // Casa directa desde raíz
        intermedio.agregarHijo(casa1);
        intermedio.agregarHijo(casa2);

        RedAgua red = new RedAgua(raiz);
        double caudalInicial = 200.0;

        // Act
        double minimoCaudal = red.minimoCaudal(caudalInicial);

        // Assert
        // Raíz: 200 -> divide entre 2 hijos = 100 cada uno
        // Intermedio: 100 -> divide entre 2 hijos = 50 cada uno (casa1, casa2)
        // Casa3: 100 (directo desde raíz)
        // Mínimo entre casa1(50), casa2(50), casa3(100) = 50
        assertEquals(50.0, minimoCaudal, 0.001, "El caudal mínimo debe ser 50.0");
    }
}