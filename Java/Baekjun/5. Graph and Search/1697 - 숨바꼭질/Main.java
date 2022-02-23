import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static boolean[] visit = new boolean[100001];
    static int[] dir = {1, -1};
    static int[] dist = new int[100001];

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        bfs(N);
        System.out.println(dist[K]);
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();

        que.add(start);
        visit[start] = true;

        while (!que.isEmpty()) {
            int from = que.poll();
            
            for (int i = 0; i < dir.length; i++) {
                int to = from + dir[i];
                
                if (to < 0 || to > 100000) continue;
                if (!visit[to]) {
                    que.add(to);
                    visit[to] = true;
                    dist[to] = dist[from] + 1;
                }
            }

            int to = from * 2;
            if (to > 100000) continue;
            if (!visit[to]) {
                que.add(to);
                visit[to] = true;
                dist[to] = dist[from] + 1;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
