package repaso;

public class SingleNode<T> implements INode {
    private final T value;
    private SingleNode next;

    public SingleNode(T value) {
        this.value = value;
        this.next = null;
    }

    public SingleNode(T value, SingleNode nextNode) {
        this.value = value;
        this.next = nextNode;
    }

    @Override
    public Object getValue() {
        return null;
    }

    public SingleNode<T> getNext() {
        return next;
    }

    public void setNext(SingleNode<T> next) {
        this.next = next;
    }
}