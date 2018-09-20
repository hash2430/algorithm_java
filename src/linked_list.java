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
        list.q3();
        list.print();
        // Q2
    }
}
