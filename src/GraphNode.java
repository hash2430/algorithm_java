public class GraphNode {
    public int data;
    public boolean visited = false;
    public GraphNode[] children;
    public GraphNode(int n) {
        data = n;
    }
}
