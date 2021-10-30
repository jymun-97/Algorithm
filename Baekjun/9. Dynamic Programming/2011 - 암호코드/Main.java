import java.util.*;
import java.io.*;

public class Main {
    static String str;
    static int n;
    static int[] dp;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        n = str.length();
        dp = new int[str.length()];
    }

    static void solve() {
        for (int i = 1; i <= n; i++) {
            if (str.charAt(i) != '0') dp[i] = dp[i - 1];
            if (isValuable(str.charAt(i - 1), str.charAt(i))) {
                
            }
        }
    }

    static boolean isValuable(char a, char b) {
        if (a == '0') return false;
        if (a == '1') return true;
        if (a >= '3') return false;
        return b <= '6';
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}