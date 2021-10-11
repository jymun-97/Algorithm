import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] indeg, buildTime, done;
    static ArrayList<Integer>[] graph;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        indeg = new int[n + 1];
        done = new int[n + 1];
        buildTime = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int to = 1; to <= n; to++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            buildTime[to] = Integer.parseInt(st.nextToken());
            int from;
            while ((from = Integer.parseInt(st.nextToken())) != -1) {
                graph[from].add(to);
                indeg[to]++;
            }
        }
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indeg[i] == 0) {
                que.add(i);
                done[i] = buildTime[i];
            }
        }

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                if (--indeg[to] == 0) {
                    que.add(to);
                }
                done[to] = Integer.max(done[to], done[from] + buildTime[to]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(done[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
