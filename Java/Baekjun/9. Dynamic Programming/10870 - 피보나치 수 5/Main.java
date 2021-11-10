import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    static void solve() {
        int[] dy = new int[21];
        dy[0] = 0;
        dy[1] = 1;

        for (int i = 2; i < 21; i++) {
            dy[i] = dy[i - 2] + dy[i - 1];
        }

        System.out.println(dy[n]);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}