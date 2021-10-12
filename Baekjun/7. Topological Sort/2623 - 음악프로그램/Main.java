import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] indeg;
    static boolean[] visit;
    static ArrayList<Integer>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indeg = new int[n + 1];
        visit = new  boolean[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            int from = Integer.parseInt(st.nextToken());
            while (k --> 1) {
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                indeg[to]++;
                from = to;
            }
        }
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (indeg[i] == 0) {
                que.add(i);
                visit[i] = true;
                sb.append(i).append('\n');
            }
        }

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                if (visit[to]) {
                    System.out.println(0);
                    return;
                }

                if (--indeg[to] == 0) {
                    que.add(to);
                    visit[to] = true;
                    sb.append(to).append('\n');
                }
            }
        }
        System.out.println((isValuable()) ? sb : 0);
    }
    
    static boolean isValuable() {
        for (int i = 1; i <= n; i++) {
            if (!visit[i]) return false;
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
