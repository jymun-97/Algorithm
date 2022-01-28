import java.util.*;
import java.io.*;

public class Boj_13702 {

    static int n, k;
    static int[] a;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());

        }
    }

    static void solve() {
        long left = 0, right = Integer.MAX_VALUE;
        int ans = 0;
        while (left <= right) {
            int mid = (int)((left + right) / 2);

            if (run(mid) >= k) {
                left = mid + 1;
                ans = mid;
            }
            else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }
    
    static long run(int cand) {
        if (cand == 0) return k;

        long sum = 0;
        for (int target : a) {
            sum += target / cand;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}