import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, root, target, nLeaf;
    static ArrayList<Integer>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int child = 0; child < n; child++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent == -1) root = child;
            else {
                graph[parent].add(child);
            }
        }

        target = Integer.parseInt(br.readLine());
    }

    static void solve() {
        if (target == root) {
            System.out.println(0);
            return;
        }
        for (int parent = 0; parent < n; parent++) {
            if (graph[parent].contains(target)) {
                graph[parent].remove(graph[parent].indexOf(target));
                break;
            }
        }
        dfs(root);
        System.out.println(nLeaf);
    }

    static void dfs(int parent) {
        if (graph[parent].size() == 0) {
            nLeaf++;
            return;
        }
        for (int child : graph[parent]) {
            dfs(child);
        }
    }

     public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
