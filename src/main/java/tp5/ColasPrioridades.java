package tp5;

public interface ColasPrioridades<T> {
    boolean esVacia();

    void eliminar();

    boolean agregar(T elemento);

    void tope();
}
