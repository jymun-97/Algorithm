import java.util.*;
import java.io.*;

public class Main {
    static int n, k, p, x;
    static int[][] dp = new int[10][10];
    static HashMap<Integer, String> toString = new HashMap<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        init();
        pre();

        int count = 0;
        for (int target = 1; target <= n; target++) {
            int result = convert(target);
            if (result <= p && result != 0) count++;
        }
        System.out.println(count);
    }

    static int convert(int target) {
        String temp = "000000000";
        String N = Integer.toString(target);
        String X = Integer.toString(x);

        N = temp.substring(0, k - N.length()) + N;
        X = temp.substring(0, k - X.length()) + X;

        int sum = 0;
        for (int i = 0; i < k; i++) {
            int x = X.charAt(i) - '0';
            sum += dp[x][N.charAt(i) - '0'];
        }
        return sum;
    }

    static void init() {
        toString.put(0, "1111110");
        toString.put(1, "0110000");
        toString.put(2, "1101101");
        toString.put(3, "1111001");
        toString.put(4, "0110011");
        toString.put(5, "1011011");
        toString.put(6, "1011111");
        toString.put(7, "1110000");
        toString.put(8, "1111111");
        toString.put(9, "1111011");
    }

    static void pre() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                String sx = toString.get(x);
                String sy = toString.get(y);

                int count = 0;
                for (int i = 0; i < 7; i++) if (sx.charAt(i) != sy.charAt(i)) count++;
                dp[x][y] = count;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}