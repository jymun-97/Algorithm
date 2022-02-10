import java.util.*;
import java.io.*;

public class Main {
    static int n, x;
    static int[] time;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        time = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) time[i] = Integer.parseInt(st.nextToken());

    }

    static void solve() {
        int left = 1, right = 100000;
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (isPossible(mid)) {
                ans = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }

    static boolean isPossible(int n) {
        PriorityQueue<Long> lines = new PriorityQueue<>();
        for (int i = 0; i < n; i++) lines.add(0L);

        for (int t : time) {
            long sum = lines.poll();
            sum += t;
            lines.add(sum);
        }

        long result = 0L;
        for (long sum : lines) result = Math.max(result, sum);

        return result <= x;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}