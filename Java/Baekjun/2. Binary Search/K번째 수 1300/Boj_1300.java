import java.io.*;

public class Boj_1300 {
    static int n, k;
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
    }

    static void solve() {
        long left = 1, right = (long)Math.pow(10, 10);
        long ans = 0;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            if (isValuable(mid)) {
                ans = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }

    static boolean isValuable(long cand) {
        long count = 0;
        for (int row = 1; row <= n; row++) {
            int left = 1, right = n;
            int temp = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                long target = (long)mid * row;
                if (target >= cand) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                    temp = mid;
                }
            }
            count += temp;
        }  
        return count <= k - 1;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}