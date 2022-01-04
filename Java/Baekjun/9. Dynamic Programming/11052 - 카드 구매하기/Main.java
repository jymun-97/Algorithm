import java.util.*;
import java.io.*;

public class Main {

    static int n, max = 1001;
    static int[] p;
    static ArrayList<Integer>[] dp;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        p = new int[n + 1];
        dp = new ArrayList[max];
        for (int i = 0; i < max; i++) dp[i] = new ArrayList<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        dp[1].add(p[1]);
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i].add(dp[j].get(0) + dp[i - j].get(0));
            }
            dp[i].add(p[i]);
            Collections.sort(dp[i], Collections.reverseOrder());
        }
        System.out.println(dp[n].get(0));
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}