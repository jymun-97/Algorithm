import java.util.*;
import java.io.*;

class Info {
    int left, energy;

    public Info(int l, int e) {
        left = l;
        energy = e;
    }
}

public class Main {
    static int n, k;
    static long[] a, dp;
    static ArrayList<Info>[] x;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new long[n + 1];
        dp = new long[n + 1];
        x = new ArrayList[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Long.parseLong(st.nextToken());
            x[i] = new ArrayList<>();
        }

    }

    static void solve() {
        pre();

        for (int i = 1; i <= n; i++) {
            long cand = 0;
            for (Info info : x[i]) {
                cand = Math.max(cand, info.energy + dp[info.left - 1]);
            }
            dp[i] = Math.max(dp[i - 1], cand);
        }
        System.out.println(dp[n]);
    }

    static void pre() {
        int R = 1;
        long sum = 0;
        for (int L = 1; L <= n; L++) {
            while (R <= n && sum < k) {
                sum += a[R];
                R++;
            }    
            if (sum >= k) {
                x[R - 1].add(new Info(L, (int)(sum - k)));
            }
            sum -= a[L];
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}