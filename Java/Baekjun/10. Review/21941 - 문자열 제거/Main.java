import java.util.*;
import java.io.*;

public class Main {
    static String S;
    static int m;
    static HashMap<String, Integer> map = new HashMap<>();
    static int[][] dp;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = "_" + br.readLine();
        m = Integer.parseInt(br.readLine());

        int size = S.length();
        dp = new int[size][size];

        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
    }

    static void pre() {
        for (int i = 1; i < S.length(); i++) {
            String str = Character.toString(S.charAt(i));
            dp[i][i] = map.getOrDefault(str, 1);
        }
    }

    static void solve() {
        pre();

        for (int size = 2; size < S.length(); size++) {
            int start = 1;
            int end = start + size - 1;

            while (end < S.length()) {
                String subString = S.substring(start, end + 1);
                dp[start][end] = map.getOrDefault(subString, Integer.MIN_VALUE);

                for (int i = 0; i < size - 1; i++) {
                    int cand = dp[start][start + i] + dp[start + i + 1][end];
                    if (dp[start][end] < cand)
                        dp[start][end] = cand;
                }
                start++;
                end++;
            }
        }

        System.out.println(dp[1][S.length() - 1]);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}