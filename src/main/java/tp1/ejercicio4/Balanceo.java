package tp1.ejercicio4;

import tp1.ejercicio3.PilaGenerica;

/**
 * Clase que determina si un string que contiene únicamente los caracteres
 * (, ), [, ], {, } está balanceado según las reglas establecidas.
 * <p>
 * Un string está balanceado si:
 * - Es vacío
 * - Tiene la forma "(T)", "[T]" o "{T}" donde T es un string balanceado
 * - Es la concatenación "TU" de dos strings balanceados T y U
 *
 */
public class Balanceo {
    /**
     * Verifica si un string está balanceado
     * utiliza pila para mantener el orden de los símbolos de apertura.
     *
     * @param s String a verificar, debe contener únicamente: ( ) [ ] { }
     * @return true si el string está balanceado, false en caso contrario
     */
    public static boolean isBalanceado(String s) {
        // Validación de entrada
        if (s == null) {
            return false;
        }

        // String vacío está balanceado por definición
        if (s.length() == 0) {
            return true;
        }

        // Pila para mantener los símbolos de apertura
        PilaGenerica<Character> pila = new PilaGenerica<>();

        // Procesar cada caracter del string
        for (int i = 0; i < s.length(); i++) {
            char caracter = s.charAt(i);

            // Si es símbolo de apertura, lo apilamos
            if (esSimboloApertura(caracter)) {
                pila.apilar(caracter);
            }
            // Si es símbolo de cierre, verificamos correspondencia
            else if (esSimboloCierre(caracter)) {
                // Si la pila está vacía, no hay símbolo de apertura correspondiente
                if (pila.esVacia()) {
                    return false;
                }

                // Obtenemos el último símbolo de apertura
                Character simboloApertura = pila.desapilar();

                // Verificamos si forman un par válido
                if (!formanPar(simboloApertura, caracter)) {
                    return false;
                }
            }
            // Caracter no válido según las especificaciones
            else {
                return false;
            }
        }

        // El string está balanceado si la pila quedó vacía
        return pila.esVacia();
    }

    /**
     * Determina si el caracter es un símbolo de apertura.
     *
     * @param c caracter a verificar
     * @return true si es (, [ o {
     */
    private static boolean esSimboloApertura(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    /**
     * Determina si el caracter es un símbolo de cierre.
     *
     * @param c caracter a verificar
     * @return true si es ), ] o }
     */
    private static boolean esSimboloCierre(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    /**
     * Verifica si dos caracteres forman un par válido de apertura-cierre.
     *
     * @param apertura símbolo de apertura
     * @param cierre   símbolo de cierre
     * @return true si forman un par válido
     */
    private static boolean formanPar(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
                (apertura == '[' && cierre == ']') ||
                (apertura == '{' && cierre == '}');
    }
}
