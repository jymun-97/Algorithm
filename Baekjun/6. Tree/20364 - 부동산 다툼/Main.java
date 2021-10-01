import java.util.*;
import java.io.*;

public class Main {

    static int n, q, ans;
    static boolean flag;
    static int[] x;
    static int[] parentOf;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        x = new int[q + 1];
        for (int i = 1; i <= q; i++) x[i] = Integer.parseInt(br.readLine());

        visit = new boolean[n + 1];
        parentOf = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            parentOf[i] = i / 2;
        }
    }

    static void solve() {
        for (int i = 1; i <= q; i++) {
            if (visit[x[i]]) {
                ans = x[i];
                flag = true;
            }
            reverse_dfs(x[i], x[i]);
        }
        System.out.println(sb);
    }

    static void reverse_dfs(int node, int curNode) {
        if (curNode == 1) {
            if (flag) {
                sb.append(ans).append('\n');
                flag = false;
                ans = 0;
            }
            else {
                sb.append(0).append('\n');
                visit[node] = true;
            }
            return;
        }
        
        if (visit[parentOf[curNode]]) {
            ans = parentOf[curNode];
            flag = true;
        }

        reverse_dfs(node, parentOf[curNode]);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}