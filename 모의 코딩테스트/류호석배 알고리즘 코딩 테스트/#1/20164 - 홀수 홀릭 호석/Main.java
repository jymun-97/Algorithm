import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] dp = new int[1000];

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

    }

    static void pre() {
        for (int i = 1; i < 10; i++) {
            dp[i] = i % 2;
        }
        for (int i = 10; i < 1000; i++) {
            dp[i] = count(i) + dp[i / 100 + i % 100 / 10 + i % 10];
        }
    }

    static int[] solve(int num) {
        if (length(num) < 4) return new int[]{ dp[num], dp[num] };
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int s1 = 1; s1 <= length(num) - 2; s1++) {
            for (int s2 = 1; s2 <= length(num) - 1 - s1; s2++) {
                int s3 = length(num) - s1 - s2;
                int[] tokens = split(num, s1, s2, s3);

                min = Integer.min(min, solve(tokens[0] + tokens[1] + tokens[2])[0] + count(num));
                max = Integer.max(max, solve(tokens[0] + tokens[1] + tokens[2])[1] + count(num));
            }
        }
        return new int[]{ min, max };
    }

    static int length(int num) { return Integer.toString(num).length(); }

    static int count(int num) {
        int rst = 0;

        for (int i = 0; i < 10; i++) {
            rst += num % (int)Math.pow(10, i + 1) / (int)Math.pow(10, i) % 2;
        }
        return rst;
    }

    static int[] split(int num, int s1, int s2, int s3) {
        String str = Integer.toString(num);
        return new int[]{ 
            Integer.parseInt(str.substring(0, s1)),
            Integer.parseInt(str.substring(s1, s1 + s2)),
            Integer.parseInt(str.substring(s1 + s2, str.length()))
        };
    }

    public static void main(String[] args) throws Exception {

        input();
        pre();

        int min, max;
        if (length(n) < 4) min = max = dp[n];
        else {
            int[] ans = solve(n);
            min = ans[0];
            max = ans[1];
        }
        System.out.println(min + " " + max);
    }
}