import java.util.*;
import java.io.*;

public class Boj_6236 {

    static int n, m;
    static int[] a;
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n];

        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(br.readLine());
    }


    static void solve() {
        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            left = (left < a[i]) ? a[i] : left;
            right += a[i];
        }
        binSearch(left, right);
    }

    static void binSearch(int l, int r) {
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (run(mid) > m) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
                ans = mid;
            }
        }
        System.out.println(ans);
    }

    static int run(int k) {
        int remain = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (remain < a[i]) {
                remain = k;
                count++;
            }
            remain -= a[i];
        }
        return count;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}