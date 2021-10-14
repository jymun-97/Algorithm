import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static boolean[] visit;
    static HashMap<Integer, Integer>[] graph;
    static HashSet<Integer> leaf;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        leaf = new HashSet<>();
        visit = new boolean[n + 1];
        graph = new HashMap[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new HashMap<>();

        int k = Integer.parseInt(br.readLine());

        while (k --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            graph[from].put(to, count);
        }
    }

    static void solve() {
        dfs(n);
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(leaf);
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int need : list) {
            sb.append(need).append(' ').append(graph[n].get(need)).append('\n');
        }
        System.out.println(sb.toString());
    }

    static void dfs(int from) {
        visit[from] = true;

        if (graph[from].size() == 0) {
            leaf.add(from);
            return;
        }

        HashMap<Integer, Integer> temp = new HashMap<>();
        for (int to : graph[from].keySet()) {
            if (!visit[to]) dfs(to);

            for (int need : graph[to].keySet()) {
                temp.put(need, temp.getOrDefault(need, 0) + graph[from].get(to) * graph[to].get(need));
            }
        }
        for (int need : temp.keySet()) {
            graph[from].put(need, graph[from].getOrDefault(need, 0) + temp.get(need));
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}