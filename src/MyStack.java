import java.util.EmptyStackException;

public class MyStack<Integer> {
    public StackNode<Integer> top;
    public StackNode<Integer> min;
    public Integer pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        Integer item = top.data;
        top = top.next;
        return item;
    }

    public void push(Integer item) {
        StackNode<Integer> node = new StackNode<>(item);
        node.next = top;
        top = node;


    }

    public Integer peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        Integer item = top.data;
        return item;
    }

    public boolean isEmpty() {
        if (top == null) {
            return true;
        }
        return false;
    }


}
