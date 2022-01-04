import java.util.*;
import java.io.*;

public class Main {
    static int n, x;
    static int[] a;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        a = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

    }

    static void solve() {
        int left = 0, right = x - 1;
        int sum = 0, ans = 0, cnt = 0;
        for (int i = 0; i < x; i++) sum += a[i];

        while (right < a.length) {
            if (sum > ans) {
                ans = sum;
                cnt = 1;
            }
            else if (sum == ans) cnt++;
            
            sum -= a[left];
            left++;
            right++;
            if (right < a.length) sum += a[right];
        }

        if (ans == 0) {
            System.out.println("SAD");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append('\n').append(cnt);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}