import java.util.*;
import java.io.*;

public class Main {
    static int n, root = 1, end, count;
    static final int L = 0, R= 1;
    static int[][] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
 
            graph[node][L] = left;
            graph[node][R] = right;
        }
    }

    static void solve() {
        int node = root;
        while (graph[node][R] != -1) node = graph[node][R];
        end = node;

        dfs(root);
    }

    static void dfs(int node) {
        if (graph[node][L] != -1) {
            count++;
            dfs(graph[node][L]);
            count++;
        }
        if (graph[node][R] != -1) {
            count++;
            dfs(graph[node][R]);
            count++;
        }
        if (node == end) {
            System.out.println(count);
            return;
        }
    }
    
    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}