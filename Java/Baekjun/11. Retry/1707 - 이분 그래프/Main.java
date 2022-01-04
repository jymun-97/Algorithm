import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int v, e;
    static HashSet<Integer>[] graph;
    static boolean[] visit;
    static boolean[] state;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new HashSet[v + 1];
        for (int i = 1; i <= v; i++) graph[i] = new HashSet<>();

        while (e --> 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    static void solve() {
        visit = new boolean[v + 1];
        state = new boolean[v + 1];
        
        for (int i = 1; i <= v; i++) {
            if (!visit[i] && !bfs(i)) {
                sb.append("NO").append('\n');
                return;
            }
        }
        sb.append("YES").append('\n');
    }

    static boolean bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visit[start] = true;

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                if (visit[to]) {
                    if (state[from] == state[to]) return false;
                    continue;
                }
                que.add(to);
                visit[to] = true;
                state[to] = !state[from];
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        int k = Integer.parseInt(br.readLine());

        while (k --> 0) {
            input();
            solve();
        }

        System.out.println(sb.toString());
    }
}