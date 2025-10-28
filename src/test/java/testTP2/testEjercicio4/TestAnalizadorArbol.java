package testTP2.testEjercicio4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tp2.ejercicio1y6.ArbolGeneral;
import tp2.ejercicio4.AnalizadorArbol;
import tp2.ejercicio4.AreaEmpresa;

public class TestAnalizadorArbol {

    // El metodo de prueba principal
    @Test
    void testDevolverMaximoPromedio() {
        // Preparación de los datos para el árbol
        AreaEmpresa gerenciaGeneral = new AreaEmpresa("Gerencia General", 14);
        AreaEmpresa finanzas = new AreaEmpresa("Finanzas", 13);
        AreaEmpresa recursosHumanos = new AreaEmpresa("Recursos Humanos", 25);
        AreaEmpresa marketing = new AreaEmpresa("Marketing", 10);
        AreaEmpresa contabilidad = new AreaEmpresa("Contabilidad", 4);
        AreaEmpresa tesoreria = new AreaEmpresa("Tesorería", 7);
        AreaEmpresa presupuestos = new AreaEmpresa("Presupuestos", 5);
        AreaEmpresa reclutamiento = new AreaEmpresa("Reclutamiento", 6);
        AreaEmpresa capacitacion = new AreaEmpresa("Capacitación", 10);
        AreaEmpresa bienestar = new AreaEmpresa("Bienestar", 18);
        AreaEmpresa publicidad = new AreaEmpresa("Publicidad", 9);
        AreaEmpresa relacionesPublicas = new AreaEmpresa("Relaciones Públicas", 12);
        AreaEmpresa ventas = new AreaEmpresa("Ventas", 19);

        // Nivel 2, hijos de nivel 1
        ArbolGeneral<AreaEmpresa> nodoContabilidad = new ArbolGeneral<>(contabilidad);
        ArbolGeneral<AreaEmpresa> nodoTesoreria = new ArbolGeneral<>(tesoreria);
        ArbolGeneral<AreaEmpresa> nodoPresupuestos = new ArbolGeneral<>(presupuestos);
        ArbolGeneral<AreaEmpresa> nodoReclutamiento = new ArbolGeneral<>(reclutamiento);
        ArbolGeneral<AreaEmpresa> nodoCapacitacion = new ArbolGeneral<>(capacitacion);
        ArbolGeneral<AreaEmpresa> nodoBienestar = new ArbolGeneral<>(bienestar);
        ArbolGeneral<AreaEmpresa> nodoPublicidad = new ArbolGeneral<>(publicidad);
        ArbolGeneral<AreaEmpresa> nodoRelacionesPublicas = new ArbolGeneral<>(relacionesPublicas);
        ArbolGeneral<AreaEmpresa> nodoVentas = new ArbolGeneral<>(ventas);

        // Nivel 1 con sus hijos
        ArbolGeneral<AreaEmpresa> nodoFinanzas = new ArbolGeneral<>(finanzas);
        nodoFinanzas.agregarHijo(nodoContabilidad);
        nodoFinanzas.agregarHijo(nodoTesoreria);
        nodoFinanzas.agregarHijo(nodoPresupuestos);

        ArbolGeneral<AreaEmpresa> nodoRecursosHumanos = new ArbolGeneral<>(recursosHumanos);
        nodoRecursosHumanos.agregarHijo(nodoReclutamiento);
        nodoRecursosHumanos.agregarHijo(nodoCapacitacion);
        nodoRecursosHumanos.agregarHijo(nodoBienestar);

        ArbolGeneral<AreaEmpresa> nodoMarketing = new ArbolGeneral<>(marketing);
        nodoMarketing.agregarHijo(nodoPublicidad);
        nodoMarketing.agregarHijo(nodoRelacionesPublicas);
        nodoMarketing.agregarHijo(nodoVentas);

        // Raíz del árbol
        ArbolGeneral<AreaEmpresa> raizEmpresa = new ArbolGeneral<>(gerenciaGeneral);
        raizEmpresa.agregarHijo(nodoFinanzas);
        raizEmpresa.agregarHijo(nodoRecursosHumanos);
        raizEmpresa.agregarHijo(nodoMarketing);

        // Se crea el AnalizadorArbol y se llama al metodo a probar
        AnalizadorArbol analizador = new AnalizadorArbol();
        int promedioObtenido = analizador.devolverMaximoPromedio(raizEmpresa);

        // Verificación del resultado esperado
        int promedioEsperado = 16;
        Assertions.assertEquals(promedioEsperado, promedioObtenido, "El promedio más alto debería ser 16.");
    }
}