import java.util.*;
import java.io.*;

public class Boj_10816 {

    static int n, m, base = 10000000;
    static int[] cards, targets;
    static int[] count = new int[base * 2 + 1];
    static StringBuilder sb = new StringBuilder();
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cards = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            count[cards[i] + base]++;
        }

        m = Integer.parseInt(br.readLine());
        targets = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) targets[i] = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        for (int target : targets) {
            sb.append(count[target + base]).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}