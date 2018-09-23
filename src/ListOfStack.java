import java.util.ArrayList;

public class ListOfStack<Integer> {
    ArrayList<MyStack<Integer>> list = new ArrayList<>();
    int stackSize = 0;
    int stackMaxSize;
    public ListOfStack (int stackMaxSize) {
        this.stackMaxSize = stackMaxSize;
    }

    public void push(Integer data) {
        if (list.size() == 0) {
            MyStack<Integer> stack = new MyStack<>();
            stack.push(data);
            stackSize = 1;
            list.add(stack);
        }
        if (stackSize == stackMaxSize) {
            MyStack<Integer> stack = new MyStack<>();
            stack.push(data);
            stackSize = 1;
            list.add(stack);
        } else {
            list.get(list.size()-1).push(data);
            stackSize++;
        }
    }

    public Integer pop() {
        Integer item = list.get(list.size()-1).pop();
        stackSize--;
        if (stackSize == 0) {
            stackSize = stackMaxSize;
            list.remove(list.size() - 1);
        }
        return item;
    }
}
