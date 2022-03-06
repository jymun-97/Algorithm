import java.util.*;
import java.io.*;

public class BOJ_10986 {

    static int n, m;
    static int[] num, count;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new int[n + 1];
        count = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) num[i] = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += num[i];

            int mod = (int) (sum % m);
            count[mod]++;
        }

        long answer = count[0];
        for (int i = 0; i < m; i++) {
            answer += (long) count[i] * (count[i] - 1) / 2;
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}