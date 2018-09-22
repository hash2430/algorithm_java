/**
 * Node is identical to ListNode from the example, but parameterized with T
 *
 * @author downey
 *
 */
public class Node<E> {
    public E data;
    public Node next;

    public Node() {
        this.data = null;
        this.next = null;
    }

    public Node(E data) {
        this.data = data;
        this.next = null;
    }
    @SuppressWarnings("unused")
    public Node(E data, Node next) {
        this.data = data;
        this.next = next;
    }
//    public String toString() {
//        return "Node(" + data.toString() + ")";
//    }
}
