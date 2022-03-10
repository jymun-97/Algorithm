import java.util.*;
import java.io.*;

public class BOJ_13913 {

    static int n, k;
    static int[] time = new int[100001];
    static int[] route = new int[100001];

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        que.add(n);

        while (!que.isEmpty()) {
            int from = que.poll();

            if (from == k) break;

            for (int i = 0; i < 3; i++) {
                int to = 0;
                if (i < 2) to = from + i * 2 - 1;
                else to = from * 2;

                if (to < 0 || to > 100000 || time[to] != 0 || to == n) continue;
                
                time[to] = time[from] + 1;
                route[to] = from;
                que.add(to);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(time[k]).append('\n');

        Stack<Integer> stack = new Stack<>();
        stack.push(k);
        int next = k;
        while (next != n) {
            next = route[next];
            stack.push(next);
        }

        while (!stack.isEmpty()) sb.append(stack.pop()).append(' ');
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}