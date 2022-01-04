import java.util.*;
import java.io.*;

public class Main {
    static int n, MAX = 1000;
    static long[] dy;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dy = new long[MAX + 1];
    }

    static void solve() {
        dy[1] = 1;
        dy[2] = 2;

        for (int i = 3; i <= n; i++) {
            dy[i] = (dy[i - 1] + dy[i - 2]) % 10007L; 
        }
        System.out.println(dy[n]);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}