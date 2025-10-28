# Análisis de Estructuras de Datos - Lista de Enteros y Listas Genéricas

## 1.4 Diferencias entre implementaciones

### Análisis comparativo de `ListaDeEnterosConArreglos` vs `ListaDeEnterosEnlazada`

Las diferencias fundamentales entre ambas implementaciones radican en:

#### **Asignación de Memoria**
- **ListaDeEnterosConArreglos**: Utiliza **asignación estática** con un arreglo de tamaño fijo predefinido (`Integer[] datos = new Integer[30]`). La memoria se reserva completamente al momento de la creación del objeto.
- **ListaDeEnterosEnlazada**: Emplea **asignación dinámica** mediante nodos enlazados (`NodoEntero`). La memoria se asigna bajo demanda conforme se agregan elementos.

#### **Estructura Interna**
- **Con Arreglos**: Los elementos se almacenan en posiciones contiguas de memoria, utilizando un índice (`tamanio`) para controlar el número de elementos válidos.
- **Enlazada**: Cada elemento se almacena en un nodo que contiene el dato y una referencia al siguiente nodo, formando una cadena de referencias.

#### **Limitaciones de Capacidad**
- **Con Arreglos**: Limitada por el tamaño fijo del arreglo (30 elementos en esta implementación).
- **Enlazada**: Teóricamente limitada solo por la memoria disponible del sistema.

#### **Rendimiento**
- **Acceso aleatorio**: Los arreglos ofrecen acceso O(1), mientras que las listas enlazadas requieren O(n).
- **Inserción/eliminación**: En arreglos requiere desplazamiento de elementos O(n), en listas enlazadas es O(1) si se tiene la referencia al nodo.
- **Uso de memoria**: Los arreglos tienen overhead fijo, las listas enlazadas tienen overhead por nodo (referencia adicional).

#### **Comparativa Pros y Contras**

| Aspecto | ListaDeEnterosConArreglos | ListaDeEnterosEnlazada |
|---------|---------------------------|------------------------|
| **Pros** | • Acceso directo O(1)<br>• Menor fragmentación de memoria<br>• Cache-friendly<br>• Menor overhead por elemento | • Capacidad dinámica<br>• Inserción eficiente con referencia<br>• No desperdicia memoria<br>• Flexibilidad de tamaño |
| **Contras** | • Capacidad limitada<br>• Inserción/eliminación costosa<br>• Desperdicio de memoria<br>• Redimensionamiento complejo | • Acceso secuencial O(n)<br>• Mayor overhead por nodo<br>• Fragmentación de memoria<br>• Cache-unfriendly |

## 1.5 Método recursivo para imprimir en sentido inverso

```java
/**
 * Imprime los elementos de una lista de enteros en sentido inverso
 * utilizando recursión. Funciona con cualquier implementación de ListaDeEnteros.
 * 
 * @param lista La lista de enteros a imprimir en orden inverso
 */
public static void imprimirInverso(ListaDeEnteros lista) {
    imprimirInversoRecursivo(lista, 1);
}

/**
 * Método auxiliar recursivo que implementa la lógica de impresión inversa
 * 
 * @param lista La lista de enteros
 * @param posicion Posición actual a procesar (base 1)
 */
private static void imprimirInversoRecursivo(ListaDeEnteros lista, int posicion) {
    // Caso base: si la posición excede el tamaño de la lista
    if (posicion > lista.tamanio()) {
        return;
    }
    
    // Llamada recursiva para procesar el resto de la lista
    imprimirInversoRecursivo(lista, posicion + 1);
    
    // Al retornar de la recursión, imprimir el elemento actual
    // Esto garantiza el orden inverso
    System.out.print(lista.elemento(posicion) + " ");
}
```

**Justificación del diseño:**
- **Parámetro único**: El método público recibe únicamente la lista, manteniendo simplicidad en la interfaz.
- **Polimorfismo**: Funciona indistintamente con `ListaDeEnterosConArreglos` o `ListaDeEnterosEnlazada` gracias a la abstracción.
- **Recursión**: La impresión se realiza durante el "retorno" de las llamadas recursivas, garantizando el orden inverso.

## 1.6 Análisis de implementaciones

### a) Comportamiento de la superclase
Sí, se podría dar comportamiento a algunos métodos de la superclase `ListaDeEnteros`. Métodos como `incluye()`, `toString()`, o incluso `agregarInicio()` podrían implementarse genéricamente utilizando los métodos abstractos básicos.

La clase se define como **abstracta** porque las operaciones fundamentales (`agregarEn`, `eliminarEn`, `elemento`) dependen completamente de la estructura de datos específica utilizada. No existe una implementación genérica eficiente que funcione óptimamente para ambas estructuras (arreglos vs. nodos enlazados), ya que cada una tiene estrategias de acceso y manipulación completamente diferentes.

### b) Diferencias al agregar nodos en listas enlazadas

#### **Escenarios según disponibilidad de referencias:**

**1. Con referencias completas (inicio, fin, tamaño):**
- **Al principio**: 
  - Complejidad: O(1)
  - Operación: Crear nuevo nodo, enlazarlo al inicio actual, actualizar referencia de inicio
  - Consideración especial: Si es el primer elemento, también actualizar fin

- **Al final**: 
  - Complejidad: O(1) 
  - Operación: Crear nuevo nodo, enlazarlo desde el nodo fin, actualizar referencia de fin
  - Ventaja: No requiere recorrido

- **En el medio**: 
  - Complejidad: O(n)
  - Operación: Recorrer hasta posición-1, crear nodo, actualizar enlaces
  - Limitación: Siempre requiere recorrido independientemente de las referencias disponibles

**2. Con referencias básicas (solo inicio):**
- **Al principio**: O(1) - Directo
- **Al final**: O(n) - Requiere recorrido completo hasta encontrar el último nodo
- **En el medio**: O(n) - Requiere recorrido hasta la posición deseada

**3. Impacto en el rendimiento:**
La disponibilidad de referencias adicionales (como `fin`) transforma operaciones O(n) en O(1) para casos específicos, pero no elimina la naturaleza secuencial de la estructura para el acceso al medio.

### c) Posición del primer elemento en arreglos

La ubicación del primer elemento **depende completamente de la implementación** del programador. En tu código se demuestra esta dependencia:

```java
@Override
public Integer elemento(int pos) {
    return datos[pos - 1];  // Posición lógica 1 -> índice físico 0
}

@Override
public boolean agregarEn(Integer elem, int pos) {
    // ... validaciones ...
    datos[pos - 1] = elem;  // Mapeo posición lógica a índice físico
    return true;
}
```

**Análisis del código:**
- **Posición lógica**: La interfaz pública maneja posiciones desde 1 (primera posición = 1)
- **Índice físico**: Internamente el arreglo usa índices desde 0
- **Mapeo**: `posición_lógica - 1 = índice_físico`

Esta implementación específica podría haberse diseñado de manera diferente:
- Posiciones desde 0: `return datos[pos];`
- Arreglo con índice 1 válido: `Integer[] datos = new Integer[31];` y usar `datos[pos]`

## 2.1 Resolución con listas genéricas

Absolutamente, es posible resolver todos los ejercicios del punto 1 utilizando listas genéricas. La migración sería directa:

- `ListaDeEnterosConArreglos` → `ListaGenericaConArreglos<Integer>`
- `ListaDeEnterosEnlazada` → `ListaEnlazadaGenerica<Integer>`

La funcionalidad se mantiene idéntica, pero se gana flexibilidad de tipos y reutilización de código.

## 2.4 Análisis de listas genéricas

### a) Diferencias entre `ListaEnlazadaGenerica` y `ListaDeEnterosEnlazada`

La diferencia fundamental radica en la **parametrización de tipos**. `ListaEnlazadaGenerica<T>` utiliza genéricos de Java, lo que permite:

**Flexibilidad de tipos:**
- `ListaDeEnterosEnlazada`: Exclusivamente para objetos `Integer`
- `ListaEnlazadaGenerica<T>`: Para cualquier tipo de objeto (`String`, `Estudiante`, `Double`, etc.)

**Reutilización de código:**
La versión genérica elimina la necesidad de crear clases separadas para cada tipo de dato. Una sola implementación sirve para múltiples tipos, siguiendo el principio DRY (Don't Repeat Yourself).

**Type Safety:**
Los genéricos proporcionan verificación de tipos en tiempo de compilación, eliminando la necesidad de casting explícito y reduciendo errores de ejecución tipo `ClassCastException`.

**Mantenimiento:**
Las correcciones y mejoras en la implementación genérica benefician automáticamente a todos los tipos que la utilizan, reduciendo el esfuerzo de mantenimiento.

**Ejemplo de beneficio:**
```java
// Sin genéricos - múltiples clases necesarias
ListaDeEnterosEnlazada numeros = new ListaDeEnterosEnlazada();
ListaDeStringEnlazada nombres = new ListaDeStringEnlazada(); // Habría que crear esta clase

// Con genéricos - una sola implementación
ListaEnlazadaGenerica<Integer> numeros = new ListaEnlazadaGenerica<>();
ListaEnlazadaGenerica<String> nombres = new ListaEnlazadaGenerica<>();
```

### b) Definición del nodo genérico

El nodo genérico se define como:
```java
public class NodoGenerico<T> {
    private T dato;
    private NodoGenerico<T> siguiente;
    
    // getters y setters
}
```

**Creación de instancia:**
```java
NodoGenerico<T> aux = new NodoGenerico<T>();
// o con inferencia de tipos (Java 7+):
NodoGenerico<T> aux = new NodoGenerico<>();
```

### c) Retorno del método `elemento()`

El método `elemento()` devuelve un objeto de **tipo `T`** (el tipo genérico especificado al instanciar la lista), o `null` si la posición no es válida.

**Significado del tipo de retorno:**
- **Type Safety**: El compilador garantiza que el objeto devuelto es del tipo esperado
- **No casting requerido**: A diferencia de estructuras no genéricas, no necesitas convertir el resultado
- **Polimorfismo**: El tipo `T` puede ser cualquier clase o interfaz, manteniendo el comportamiento polimórfico

**Ejemplo práctico:**
```java
ListaEnlazadaGenerica<Estudiante> lista = new ListaEnlazadaGenerica<>();
// ...
Estudiante estudiante = lista.elemento(1); // No necesita casting
// vs versión no genérica que requeriría:
// Estudiante estudiante = (Estudiante) lista.elemento(1);
```

Esta característica elimina errores comunes y mejora la legibilidad del código.

### d) Implementación del método `agregar(T[]):boolean`

```java
/**
 * Agrega todos los elementos del arreglo a la lista
 * @param elementos Arreglo de elementos a agregar
 * @return true si todos los elementos fueron agregados exitosamente
 */
public boolean agregar(T[] elementos) {
    // Validación de entrada
    if (elementos == null) {
        return false;
    }
    
    boolean todosAgregados = true;
    
    // Iterar sobre todos los elementos del arreglo
    for (T elemento : elementos) {
        // Intentar agregar cada elemento al final de la lista
        if (!this.agregarFinal(elemento)) {
            todosAgregados = false;
            // Continuamos intentando agregar el resto, no interrumpimos
        }
    }
    
    return todosAgregados;
}
```

**Justificación del diseño elegido:**

1. **Uso de `agregarFinal()`**: Se opta por agregar al final para mantener el orden original del arreglo en la lista.

2. **Retorno booleano agregado**: Se devuelve `true` solo si **todos** los elementos fueron agregados exitosamente, proporcionando información completa sobre el resultado de la operación.

3. **Manejo de errores resiliente**: Si un elemento falla al agregarse, el método continúa intentando con los restantes, no interrumpe la operación completamente.

4. **Validación robusta**: Se verifica que el arreglo no sea `null` antes de procesarlo, evitando `NullPointerException`.

5. **Enhanced for loop**: Se utiliza for-each para mayor legibilidad y menor propensión a errores de índices.

Esta implementación balancea robustez, eficiencia y usabilidad, proporcionando un comportamiento predecible y útil para el usuario de la clase.

## 4.a Estructura de datos para balanceo

Para resolver el problema de verificación de balanceo de paréntesis, corchetes y llaves, se utiliza una **Pila (Stack)** como estructura de datos principal.

### Justificación del uso de Pila

**Principio LIFO (Last In, First Out):**
La pila implementa el principio "último en entrar, primero en salir", que es perfectamente adecuado para el problema de balanceo porque:

- El **último símbolo de apertura** encontrado debe ser el **primero en cerrarse**
- Esto mantiene la correspondencia correcta en estructuras anidadas
- Permite detectar cruces incorrectos como `"([)]"`

**Correspondencia natural con el anidamiento:**
- Los símbolos más internos (últimos en abrirse) deben cerrarse primero
- La pila mantiene automáticamente este orden jerárquico
- Cada símbolo de cierre debe corresponder exactamente con el último símbolo de apertura sin cerrar

### Algoritmo de implementación

1. **Inicialización**: Crear una pila vacía
2. **Recorrido**: Procesar cada carácter del string secuencialmente
3. **Símbolos de apertura** `(`, `[`, `{`: Apilar el símbolo
4. **Símbolos de cierre** `)`, `]`, `}`: 
   - Verificar que la pila no esté vacía
   - Desapilar el último símbolo de apertura
   - Verificar correspondencia de tipos
5. **Validación final**: La pila debe estar vacía para que esté balanceado

### Ventajas de esta aproximación

- **Eficiencia temporal**: O(n) - un solo recorrido del string
- **Eficiencia espacial**: O(n) en el peor caso (todos símbolos de apertura)
- **Simplicidad conceptual**: Mapeo directo entre el problema y la estructura
- **Robustez**: Detecta todos los casos de desbalanceo posibles

Esta estructura garantiza la detección correcta de secuencias mal anidadas y asegura el balanceo adecuado en casos complejos, siendo la solución más natural y eficiente para este tipo de problemas de correspondencia jerárquica.