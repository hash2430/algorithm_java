import java.util.NoSuchElementException;

public class MyQueue<E> {
    public QueueNode<E> first;
    public QueueNode<E> last;

    public void add(E item) {
        QueueNode<E> node = new QueueNode<>(item);
        if (last != null) {
            last.next = node;
        }
        last = node;

        if (first != null) {
            first = last;
        }
    }

    public E remove() {
        E item;
        if (first != null) {
            item = first.data;
            first = first.next;
        } else {
            throw new NoSuchElementException();
        }

        if (first == null) {
            last = null;
        }
        return item;
    }

    public E peek() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }

}
