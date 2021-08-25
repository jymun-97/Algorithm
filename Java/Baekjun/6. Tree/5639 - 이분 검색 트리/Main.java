import java.util.*;
import java.io.*;

public class Main {

    static int[][] graph;
    static int root;
    static Queue<Integer> que = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        graph = new int[1000000][2];
        String line;
        while (true) {
            line = br.readLine();
            if (line == null || line.equals("")) break;
            que.add(Integer.parseInt(line));
        }
    }

    static void solve() {
        root = que.poll();
        while (!que.isEmpty()) {
            int node = que.poll();
            dfs(root, node);
        }
        postOrder(root);

        System.out.println(sb);
    }
    
    static void dfs(int parent, int child) {
        if (parent > child) {
            if (graph[parent][0] == 0) graph[parent][0] = child;
            else dfs(graph[parent][0], child);
        }
        else {
            if (graph[parent][1] == 0) graph[parent][1] = child;
            else dfs(graph[parent][1], child);
        }
    }

    static void postOrder(int parent) {
        if (parent == 0) return;
        
        postOrder(graph[parent][0]);
        postOrder(graph[parent][1]);
        sb.append(parent).append('\n');
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}