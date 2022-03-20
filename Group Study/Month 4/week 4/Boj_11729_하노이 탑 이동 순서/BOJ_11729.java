import java.io.*;

public class BOJ_11729 {

    static StringBuilder[][][] dp;
    static int n;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new StringBuilder[n + 1][4][4];

        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    dp[i][j][k] = new StringBuilder();
                    // i개의 원판을 j장대에서 k장대로 옮길 때의 이동 순서
                }
            }
        }
    }

    static void solve() {
        move(1);

        for (int i = 2; i <= n; i++) {
            move(i);
        }

        System.out.println(count() + "\n" + dp[n][1][3]);
    }

    static void move(int k) {
        for (int from = 1; from <= 3; from++) {
            for (int to = 1; to <= 3; to++) {
                if (from == to) continue;

                int temp = 6 - from - to;
                
                if (k == 1) dp[k][from][to].append(from).append(' ').append(to).append('\n');
                else dp[k][from][to]
                    .append(dp[k - 1][from][temp])
                    .append(dp[1][from][to])
                    .append(dp[k - 1][temp][to]);
            }
        }
    }

    static int count() {
        return (int)Math.pow(2, n) - 1;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}