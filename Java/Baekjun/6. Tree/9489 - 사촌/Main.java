import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, k, root, max = 1000001;
    static int[] parentOf, indexOf;
    static ArrayList<Integer>[] graph;
    
    static void input() throws IOException {
        parentOf = new int[max];
        indexOf = new int[max];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n ; i++) graph[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int pre = root = Integer.parseInt(st.nextToken());
        indexOf[root] = 1;
        int idx = 2;

        while (st.hasMoreTokens()) {
            int node = Integer.parseInt(st.nextToken());
            indexOf[node] = idx++; 
            insert(node, pre);
            pre = node;
        }
    }

    static void solve() {
        int ans = 0;
        int target = parentOf[k];
        if (target == root) {
            sb.append(ans).append('\n');
            return;
        }
        for (int i = 1; i < max; i++) {
            if (parentOf[target] == parentOf[i] && i != target) {
                ans += graph[indexOf[i]].size();
            }
        }
        sb.append(ans).append('\n');

    }

    static void insert(int node, int pre) {
        if (pre == root) {
            graph[indexOf[root]].add(node);
            parentOf[node] = root;
            return;
        }

        if (node - pre == 1) {
            graph[indexOf[parentOf[pre]]].add(node);
            parentOf[node] = parentOf[pre];
        }
        else {
            Queue<Integer> que = new LinkedList<>();
            que.add(root);

            while (!que.isEmpty()) {
                int cand = que.poll();
                if (graph[indexOf[cand]].size() == 0) {
                    graph[indexOf[cand]].add(node);
                    parentOf[node] = cand;
                    break;
                }

                for (int child : graph[indexOf[cand]]) {
                    que.add(child);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) break;

            input();
            solve();
        }
        System.out.println(sb);
    }
}