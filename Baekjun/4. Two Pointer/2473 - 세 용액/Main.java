import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] a;
    static ArrayList<Integer> ans;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        Arrays.sort(a);
        StringBuilder sb = new StringBuilder();
        
        int ans1 = 0, ans2 = 0, ans3 = 0;
        long min = Long.MAX_VALUE;
        for (int x = 0; x <= n - 3; x++) {
            int y = x + 1, z = n - 1;
            while (y < z) {
                long sum = (long)a[x] + a[y] + a[z];
                if (sum == 0) {
                    sb.append(a[x]).append(' ').append(a[y]).append(' ').append(a[z]);
                    System.out.println(sb);
                    return;
                }

                if (Math.abs(sum) < min) {
                    ans1 = a[x]; ans2 = a[y]; ans3 = a[z];
                    min = Math.abs((long)ans1 + ans2 + ans3);
                }
                
                if (sum > 0) z--;
                else y++;
            }
        }
        sb.append(ans1).append(' ').append(ans2).append(' ').append(ans3);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}