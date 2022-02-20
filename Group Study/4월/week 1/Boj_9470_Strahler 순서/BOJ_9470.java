import java.util.*;
import java.io.*;

public class BOJ_9470 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static ArrayList<Integer>[] graph;
    static int n, m, ans;
    static int[] indeg, stra;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb.append(Integer.parseInt(st.nextToken())).append(' ');

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = 0;

        indeg = new int[n + 1];
        stra = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            indeg[to]++;
        }
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indeg[i] == 0) {
                stra[i] = 1;   
                que.add(i);
            }
        }

        int[] info = new int[n + 1];
        while (!que.isEmpty()) {
            int from = que.poll();
            ans = Math.max(ans, stra[from]);

            for (int to : graph[from]) {
                if (stra[to] < stra[from]) stra[to] = info[to] = stra[from];
                else if (stra[to] == stra[from]) {
                    if (info[to] != stra[from]) info[to] = stra[from];
                    else stra[to]++;
                }

                if (--indeg[to] == 0) que.add(to);
            }
        }

        sb.append(ans).append('\n');
    }

    public static void main(String[] args) throws Exception {

        int k = Integer.parseInt(br.readLine());
        while (k --> 0) {
            input();
            solve();
        }
        System.out.println(sb);   
    }
}