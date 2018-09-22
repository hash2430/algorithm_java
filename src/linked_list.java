import static jdk.nashorn.internal.objects.Global.print;
// TODO: 만약 linked list 문제가 나온다면 add와 size는 구현을 해두어야 문제를 풀 때 편하다.
public class linked_list {
    // Q1. 정렬 안된 연결리스트에서 중복 원소를 제거하는 코드

    // Q2. 단방향 연결 리스트가 주어졌을 때 뒤에서 k번째 원소를 찾는 알고리즘

    // Q3. 단방향 연결리스트가 주어졌을 때 중간에 있는 노드 하나만 삭제
    // 정확히 가운데일 필요는 없다
    // 삭제할 노드에만 access할 수 있다.
    // 리턴값은 없지만 해당 노드는 삭제돼야함
    public static void main(String[] args) {
        // Q1
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(new Integer(3));
        list.add(new Integer(1));
        list.add(new Integer(3));
        list.add(new Integer(1));
        list.q1();
        list.print();
        // Q2
        // 뒤에서 k번째 원소 구하기
        int q2_int = 3;
        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        list2.add(5);
        list2.add(1);
        list2.add(7);
        list2.add(10);
        int q2_return = list2.q2(q2_int);
        System.out.println("Q2: " + q2_return);

        // Q3: 연결리스트에서 중간 노드 삭제하기. 단 중간노드만 접근가능
        MyLinkedList<Integer> list3 = new MyLinkedList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.add(4);
        Node<Integer> node = list3.getNode(2);
        list3.q3(node);
        System.out.println("Q3:");
        list3.print();

        //Q4: x를 기준으로 그보다 작은값과 같거나 큰값으로 분할된 연결리스트
        MyLinkedList<Integer> list4 = new MyLinkedList<>();
        list4.add(3);
        list4.add(5);
        list4.add(8);
        list4.add(5);
        list4.add(10);
        list4.add(2);
        list4.add(1);
        list4 = list4.q4(5);
        System.out.println("Q4: ");
        list4.print();
        // Q5) 연결리스트로 각 자리수가 역순으로 표현된 정수의 합
        MyLinkedList<Integer> l1 = new MyLinkedList<>();
        MyLinkedList<Integer> l2 = new MyLinkedList<>();

        l1.add(7);
        l1.add(1);
        l1.add(6);
        l1.add(1);
        l1.add(2);

        l2.add(5);
        l2.add(9);
        l2.add(2);

        MyLinkedList<Integer> l3 = MyLinkedList.q5(l1, l2);
        System.out.println("Q5: ");
        l3.print();

        MyLinkedList<Integer> l3_2 = MyLinkedList.q5_2(l1, l2);
        System.out.println("Q5_2:");
        l3_2.print();

        // Q6) 회문인지 검사하기
        MyLinkedList<Integer> l6 = new MyLinkedList<>();
        l6.add(1);
        l6.add(3);
        l6.add(5);
        l6.add(3);
        l6.add(1);

        boolean l6_ = l6.q6();
        System.out.println("Q6: " + l6_);

        MyLinkedList<Integer> l6_1 = new MyLinkedList<>();
        l6_1.add(1);
        l6_1.add(3);
        l6_1.add(3);
        l6_1.add(1);

        l6_ = l6_1.q6();
        System.out.println("Q6: " + l6_);

        // Q7) 교집합 노드 반환
        MyLinkedList<Integer> l7_1 = new MyLinkedList<>();
        l7_1.add(1);
        l7_1.add(2);
        l7_1.add(3);
        l7_1.add(4);
        l7_1.add(5);

        Node<Integer> subsetStartingNode = l7_1.getNode(2);
        MyLinkedList<Integer> l7_2 = new MyLinkedList<>(subsetStartingNode);
        Node<Integer> l7_return = l7_1.q7(l7_2);
        System.out.println(l7_return.data);

        MyLinkedList<Integer> l7_3 = new MyLinkedList<>();
        l7_3.add(3);
        l7_3.add(4);
        l7_3.add(5);
        Node<Integer> l7_return2 = l7_1.q7(l7_3);
        System.out.println(l7_return2.data);

    }
}
