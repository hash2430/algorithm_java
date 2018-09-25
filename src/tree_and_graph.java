public class tree_and_graph {
    // 트리 탐색 알고리즘을 그래프에 확장
    public static boolean q1(GraphNode a, GraphNode b) {
        return q1_(a, b) | q1_(b, a);
    }
    // DFS 전위 순회 트리 탐색
    public static boolean q1_(GraphNode a, GraphNode b) {
        boolean flag = false;
        GraphNode node = a;
        GraphNode childNode;
        int childID = 0;
        if (a.children == null) {
            return false;
        }
        flag = sameAsTarget(node, b);
        if (flag) return true;
        // 비교 대상은 childNode이고 childNode는 childID와 node에 의해 달라진다.
        while (childID != -1){
            childNode = node.children[childID];
            flag = sameAsTarget(childNode, b);
            if (flag) return true;
            childID = getUnvisitedChildID(childNode);
            if (childID != -1) {
                node = childNode;
            } else {
                childID = getUnvisitedChildID(node);
            }
        }
        return flag;
    }

    public static boolean sameAsTarget(GraphNode a, GraphNode target) {
        a.visited = true;
        if (a.toString().compareTo(target.toString())==0)
            return true;

        return false;
    }

    public static int getUnvisitedChildID(GraphNode n) {
        int ret = -1;
        if (n.children == null) {
            return ret;
        }
        for (int i = 0; i < n.children.length; i++) {
            if (!n.children[i].visited) {
                return i;
            }
        }
        return ret;
    }

    // Q2) 오름차순 정렬된 배열. 중복 없는 정수 원소들. 최소 높이의 이진탐색트리를 만들어라
    public static void q2(BinaryTreeNode n1, int[] arr) {
        if (arr.length == 1) {
            n1.data = arr[0];
            return;
        } else if (arr.length == 2) {
            n1.l = new BinaryTreeNode(arr[0]);
            n1.data = arr[1];
            return;
        } else {
            int medIdx = (arr.length%2==0)?arr.length/2:(arr.length-1)/2;
            n1.data = arr[medIdx];
            int[] left = new int[medIdx];
            for (int i =0; i < left.length; i++) {
                left[i] = arr[i];
            }

            int[] right = new int[arr.length-medIdx-1];
            for (int i =0; i < right.length; i++) {
                right[i] = arr[medIdx + i+1];
            }

            n1.l = new BinaryTreeNode();
            n1.r = new BinaryTreeNode();
            q2(n1.l, left);
            q2(n1.r, right);
        }
    }
    public static void main(String[] args) {
        // Q1) 방향 그래프의 두 노드에 대해 연결 여부를 리턴
        Graph g1 = new Graph();
        GraphNode gn0 = new GraphNode(0);
        GraphNode gn4 = new GraphNode(4);
        g1.nodes = new GraphNode[]{gn0, gn4};
        GraphNode gn1 = new GraphNode(1);
        GraphNode gn2 = new GraphNode(2);
        GraphNode gn3 = new GraphNode(3);
        GraphNode gn5 = new GraphNode(5);

        gn4.children = new GraphNode[]{gn5};
        gn3.children = new GraphNode[]{gn2};
        gn2.children = new GraphNode[]{gn3, gn5};
        gn1.children = new GraphNode[]{gn2};
        gn0.children = new GraphNode[]{gn1};

        boolean q1 = q1(gn3, gn5);
        System.out.println(q1);

        // Q2) 오름차순 정렬된 배열. 중복 없는 정수 원소들. 최소 높이의 이진탐색트리를 만들어라
        int[] arr = new int[]{1,2,3,4,5,6,7,8};
        BinaryTreeNode n1 = new BinaryTreeNode();
        q2(n1, arr);
        System.out.println("");
    }
}
