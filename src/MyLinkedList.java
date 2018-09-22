/**
 * 
 */

import java.util.*;

/**
 * @author downey
 * @param <E>
 *
 */
public class MyLinkedList<E> implements List<E> {
	private int size;            // keeps track of the number of elements
	private Node head;           // reference to the first node

	/**
	 *
	 */
	public MyLinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// run a few simple tests
		List<Integer> mll = new MyLinkedList<Integer>();
		mll.add(1);
		mll.add(2);
		mll.add(3);
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());

		mll.remove(new Integer(2));
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
	}

	@Override
	public boolean add(E element) {
		if (head == null) {
			head = new Node(element);
		} else {
			Node node = head;
			// loop until the last node
			for ( ; node.next != null; node = node.next) {}
			node.next = new Node(element);
		}
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
	    if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException();
        }

	    if (index == 0) {
	        Node newNode = new Node(element, head);
	        head = newNode;
	        size++;
	        return;
        }

        Node node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        Node newNode = new Node(element, node.next);
        node.next = newNode;
        size++;
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean flag = true;
		for (E element: collection) {
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object obj: collection) {
			if (!contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int index) {
		Node node = getNode(index);
		return (E) node.data;
	}

	/** Returns the node at the given index.
	 * @param index
	 * @return
	 */
	public Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		for (int i=0; i<index; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public int indexOf(Object target) {
		Node node = head;
		boolean equals = false;
		int i = 0;
        for (; node.next != null; node = node.next) {
        	equals = equals(target, node.data);
        	if (equals) {
        	    return i;
			}
			i++;
		}

		equals = equals(target, node.data);
        if (equals) {
            return i;
        }

		return -1;
	}

	/** Checks whether an element of the array is the target.
	 *
	 * Handles the special case that the target is null.
	 *
	 * @param target
	 * @param element
	 */
	private boolean equals(Object target, Object element) {
		if (target == null) {
			return element == null;
		} else {
			return target.equals(element);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		E[] array = (E[]) toArray();
		return Arrays.asList(array).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		Node node = head;
		int index = -1;
		for (int i=0; i<size; i++) {
			if (equals(target, node.data)) {
				index = i;
			}
			node = node.next;
		}
		return index;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public boolean remove(Object obj) {
		int index = indexOf(obj);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        E returnVal;

        if (index == 0) {
            returnVal = (E) node.data;
            head = node.next;
        }
        else {
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            returnVal = (E) node.next.data;
            node.next = node.next.next;
        }
        size--;
        return returnVal;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean flag = true;
		for (Object obj: collection) {
			flag &= remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		Node node = getNode(index);
		E old = (E)node.data;
		node.data = element;
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		// TODO: classify this and improve it.
		int i = 0;
		MyLinkedList<E> list = new MyLinkedList<E>();
		for (Node node=head; node != null; node = node.next) {
			if (i >= fromIndex && i <= toIndex) {
				list.add((E)node.data);
			}
			i++;
		}
		return list;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i = 0;
		for (Node node=head; node != null; node = node.next) {
			// System.out.println(node);
			array[i] = node.data;
			i++;
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	public void q1() {
		HashSet<Integer> set = new HashSet<>();
		Node node = head;
		Node prev = null;
		if (node == null) {
			return;
		}
		while (node != null) {
			if (set.contains(node.data)){
				prev.next = node.next;
				node = prev.next;
				prev = prev; // 적을 필요 없지만 가독성 위해서 적음
				size--;
			} else {
				set.add((Integer)node.data);
				prev = node;
				node = node.next;
			}
		}
	}

	public void print(){
		Node node = head;
		for (int i = 0; i < size; i++) {
			System.out.println(node);
			node = node.next;
		}
	}

	// Q2) 뒤에서 k번째 원소 리턴
    public E q2(int k) {
	    if (k > size - 1) {
	        throw new IndexOutOfBoundsException();
        }
	    int n = size - k - 1;
	    Node node = head;
	    for (int i = 0; i < n; i++) {
	        node = node.next;
        }
        return (E)node.data;
    }
    // Q3) 중간 노드 삭제. 해당 노드만 접근가능
    public void q3(Node<E> node) {
	    Node<E> nextNode = node.next;
	    node.data = nextNode.data;
	    node.next = nextNode.next;
	    size--;
    }

    // Q4) x값을 기준으로 더 작은 그룹과 크거나 같은 그룹으로 분할된 연결 리스트
    public MyLinkedList<E> q4(int x){
	    MyLinkedList<E> newList1 = new MyLinkedList<>();
	    MyLinkedList<E> newList2 = new MyLinkedList<>();
	    Node<E> node = head;
	    for (int i =0; i < size; i++) {
	        if ((Integer) node.data < new Integer(x)){
	            newList1.add(node.data);
            } else {
	            newList2.add(node.data);
            }
            node = node.next;
        }

        for (int i = 0; i < newList2.size; i++) {
	        newList1.add(newList2.get(i));
        }
	    return newList1;
    }

    // Q5) 두개의 linked list 의 각 node의 값을 역순으로 정수로 표현한 것의 합
    // 두 리스트가 자리수가 같은 경우에 대해 푼 다음에 자리수가 다른 경우에 대해 확장한 것이 굉장히 divice-and-conquer 적인 접근이었다고 생각함.
    public static MyLinkedList<Integer> q5(MyLinkedList<Integer> l1, MyLinkedList<Integer> l2) {
	    MyLinkedList<Integer> l3 = new MyLinkedList<>();
	    boolean flag = false;
	    Node<Integer> p1;
	    Node<Integer> p2;
        if (l1.size > l2.size) {
            p1 = l1.head;
            p2 = l2.head;
        } else {
            p1 = l2.head;
            p2 = l1.head;
        }
	    Node<Integer> p3 = l3.head;
	    int sum = -1;
	    while (p1 != null && p2 != null) {
	        sum = p1.data + p2.data;
	        if (flag) {
	            sum += 1;
            }
	        p1 = p1.next;
	        p2 = p2.next;
	        if (sum > 10) {
	            flag = true;
	            sum -= 10;
            } else {
	            flag = false;
            }
            // put to l3
            l3.add(sum);
        }
        while (p1!=null && p2 == null) {
	        sum = p1.data;
	        if(flag){
	            sum += 1;
            }
            p1 = p1.next;
	        if (sum>=10){
	            flag = true;
	            sum -= 10;
            } else {
	            flag = false;
            }
            l3.add(sum);
        }
        if (flag) {
            l3.add(1);
        }
        return l3;
    }

    // todo: 역순으로 linked list에 접근하려면 이중 루프가 필요하다
    // 역순으로 linked list에 접근할 때 한 턴이 끝나면 노드 포인터를 맨 앞으로 돌리는 것을 잊지 말아야한다.
    public static MyLinkedList<Integer> q5_2(MyLinkedList<Integer>l1, MyLinkedList<Integer>l2) {
	    boolean flag = false;
	    MyLinkedList<Integer> l3 = new MyLinkedList<>();
	    Node<Integer> p1;
	    Node<Integer> p2;
	    int sum;
	    int lengthDiff = l1.size - l2.size;
	    if(l1.size > l2.size) {
	        p1 = l1.head;
	        p2 = l2.head;
        } else {
	        p1 = l2.head;
	        p2 = l1.head;
        }
        for(int i = 0; i < l1.size - l2.size; i++) {
	        p1 = p1.next;
        }
        Node<Integer> p1Start = p1;
        for (int i =0; i < l2.size; i++) {
	        for (int j = 0; j < l2.size-i-1; j++) {
	            p1 = p1.next;
	            p2 = p2.next;
            }
            sum = p1.data + p2.data;
	        if (flag) {
	            sum++;
            }
            if (sum >= 10) {
	            flag = true;
	            sum-=10;
            } else{
	            flag = false;
            }
            // l3에 넣는다.
            l3.add(sum);
	        p1 = p1Start;
	        p2 = l2.head;
        }
        // l1의 남은 길이에 대한 연산
        p1 = l1.head;
        for (int i = 0; i < lengthDiff; i++) {
            for (int j = 0; j < lengthDiff - i - 1; j++) {
                p1 = p1.next;
            }
            sum = p1.data;
            if (flag) {
                sum++;
            }
            if (sum >= 10) {
                sum-=10;
                flag = true;
            } else {
                flag = false;
            }
            l3.add(sum);
            p1 = l1.head;
        }
        if (flag) {
	        l3.add(1);
        }

        //l3을 반전시킨다.
        Node<Integer> p3 = l3.head;
	    MyLinkedList<Integer> l4 = new MyLinkedList<>();
        for(int i = 0; i < l3.size; i++) {
	        for(int j=0; j<l3.size-i-1;j++) {
	            p3=p3.next;
            }
            l4.add(p3.data);
	        p3 = l3.head;
        }
        return l4;
    }

    // Q6) 회문인지 검사하기
    public boolean q6() {
	    int size = size();
	    boolean isOdd = (size%2)!=0;
	    int rStart;
	    if (isOdd) {
	        rStart = (size+1)/2;
        } else {
	        rStart = size/2;
        }

        // lP는 왼쪽 포인터의 끝을 가리킨다.
        int lStart = (isOdd)?rStart-2:rStart-1;
	    Node<Integer> lP = head;
//        Node<Integer> lInit = lP;
//        for (int i =0; i < lStart; i++) {
//	        lInit = lP.next;
//        }

        // rP는 오른쪽 포인터의 시작을 가리킨다.
        Node<Integer> rP = head;
        for (int i = 0; i < rStart-1; i++) {
            rP = rP.next;
        }

        // lP는 왼쪽으로, rP는 오른쪽으로 이동하며 비교한다.
        int timestep = (isOdd)?((size-1)/2):(size/2);
        for (int i = 0; i < timestep; i++) {
            //lP는 포인터는 왼쪽으로 이동
            for (int j = 0; j < timestep-i-1; j++) {
                lP = lP.next;
            }
            //rP는 오른쪽으로 이동
            rP = rP.next;
            //lP.data와 rP.data를 비교
            if (lP.data != rP.data) {
                return false;
            }
            // lP 초기화
            lP = head;
        }
        return true;
    }
}
