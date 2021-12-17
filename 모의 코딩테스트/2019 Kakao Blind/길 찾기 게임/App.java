import java.util.*;

class Node implements Comparable<Node> {
    int index;
    int x, y;

    public Node(int index, int x, int y) {
        this.index = index;        
        this.x = x;
        this.y = y;        
    }
    @Override
    public int compareTo(Node other) {
        if (y != other.y) return other.y - y;
        else return x - other.x;
    }
    @Override
    public boolean equals(Object o) {
        Node other = (Node)o;
        return index == other.index;
    }
}
class Solution {

    Node[][] graph;
    Node root;
    int n, index = 0;
    int[][] answer;

    public int[][] solution(int[][] nodeinfo) {
        ArrayList<Node> list = new ArrayList<>();
        n = nodeinfo.length;
        graph = new Node[n + 1][2];
        answer = new int[2][n];

        for (int i = 0; i < n; i++) list.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        Collections.sort(list);
        root = list.get(0);

        for (int i = 1; i < n; i++) insert(list.get(i), root);

        preOrder(root);
        index = 0;
        postOrder(root);

        return answer;
    }

    public void insert(Node node, Node parent) {
        if (node.y >= parent.y) return;

        if (node.x < parent.x) {
            if (graph[parent.index][0] == null) {
                graph[parent.index][0] = node;
                return;
            }
            else insert(node, graph[parent.index][0]);
        }
        else {
            if (graph[parent.index][1] == null) {
                graph[parent.index][1] = node;
                return;
            }
            else insert(node, graph[parent.index][1]);
        }
    }

    public void preOrder(Node node) {
        if (node == null) return;

        answer[0][index++] = node.index;
        preOrder(graph[node.index][0]);
        preOrder(graph[node.index][1]);
    }
    public void postOrder(Node node) {
        if (node == null) return;

        postOrder(graph[node.index][0]);
        postOrder(graph[node.index][1]);
        answer[1][index++] = node.index;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
        int[][] result = s.solution(nodeinfo);

        for (int[] info : result) System.out.println(Arrays.toString(info));
    }
}
