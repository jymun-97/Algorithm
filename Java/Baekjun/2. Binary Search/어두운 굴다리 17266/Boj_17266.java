import java.util.*;
import java.io.*;

public class Boj_17266 {

    static int n, m;
    static int[] x;
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        x = new int[m];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) x[i] = Integer.parseInt(st.nextToken());

    }

    static void solve() {
        int left = Integer.min(x[0], x[m - 1]), right = n;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (run(mid)) {
                ans = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }

    static boolean run(int h) {
        Queue<Integer> que = new LinkedList<>();
        for (int loc : x) {
            int start = (loc - h) >= 0 ? loc - h : 0;
            int end = (loc + h) <= n ? loc + h : n;

            que.add(start);
            que.add(end);
        }

        if (que.poll() > 0) return false;
        while (que.size() > 1) {
            int preEnd = que.poll();
            int newStart = que.poll();

            if (newStart > preEnd) return false;
        }
        if (que.poll() < n) return false;

        return true;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}