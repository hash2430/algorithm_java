public class stack_and_Queue {

    public static void main(String[] args) {
//        // Q2) O(1)에 min을 반환하는 함수를 갖는 stack
//        MyStack<Integer> stack1 = new MyStack<>();
//        stack1.push(1);
//        stack1.push(10);
//        stack1.push(2);
//        Integer i1 = stack1.q1();
        // Q3) 접시 나눠 쌓기
        ListOfStack<Integer> listOfStack = new ListOfStack<>(3);
        listOfStack.push(1);
        listOfStack.push(2);
        listOfStack.push(3);
        listOfStack.push(4);
        listOfStack.push(5);
        listOfStack.push(6);
        listOfStack.push(7);

        for (int i =0; i < 7; i++) {
            System.out.println(listOfStack.pop());
        }

    }


}
