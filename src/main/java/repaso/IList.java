package repaso;

public interface IList<T> {
    boolean add(T elemento);

    boolean remove(T elemento);

    T get(int index);

    int size();

    boolean isEmpty();

    void clear();
}
