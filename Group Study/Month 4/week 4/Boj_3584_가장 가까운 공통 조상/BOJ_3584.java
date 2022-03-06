import java.util.*;
import java.io.*;

public class BOJ_3584 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] parentOf;
    static int[] targets = new int[2];

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        parentOf = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            parentOf[child] = parent;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        targets[0] = Integer.parseInt(st.nextToken());
        targets[1] = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        boolean[] visit = new boolean[n + 1];

        Queue<Integer> que = new LinkedList<>();
        que.add(targets[0]);
        que.add(targets[1]);
        visit[targets[0]] = visit[targets[1]] = true;

        while (!que.isEmpty()) {
            int child = que.poll();
            int parent = parentOf[child];

            if (parent == 0) continue;
            if (visit[parent]) {
                sb.append(parent).append('\n');
                return;
            }

            que.add(parent);
            visit[parent] = true;
        }
    }

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t --> 0) {
            input();
            solve();
        }

        System.out.println(sb);
    }
}