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
    }
}
