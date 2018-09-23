public class QueueOfStack {
    MyStack<Integer> pushStack = new MyStack<>();
    int size = 0;
    MyStack<Integer> popStack = new MyStack<>();

    public void push(Integer data) {
        pushStack.push(data);
        size++;
    }

    public Integer pop() {
        if (popStack.isEmpty()){
            for (int i = 0; i < size; i++) {
                popStack.push(pushStack.pop());
            }
        }

        // pop from popStack
        Integer item = popStack.pop();
        size--;
        return item;
    }
}
