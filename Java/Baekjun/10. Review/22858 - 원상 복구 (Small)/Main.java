import java.util.*;
import java.io.*;

public class Main {

    static int n, k;
    static int[] d, s;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        d = new int[n + 1];
        s = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) s[i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) d[Integer.parseInt(st.nextToken())] = i;
    }

    static void solve() {
        for (int count = 1; count <= k; count++) {
            int[] p = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = s[d[i]];
            }
            s = p.clone();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) sb.append(s[i]).append(' ');

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}