import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long remain;
    static int[] limits;


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        limits = new int[N];

        for (int i = 0; i < N; i++)
            limits[i] = Integer.parseInt(br.readLine());
    }

    static boolean isValuable(int k) {
        int count = 0;
        remain = 0;

        for (int limit : limits) {
            if (remain >= limit) {
                remain -= limit;
            }
            else {
                remain += k;
                count++;
                remain -= limit;
            }
        }

        return count <= M;
    }

    static void solve() {
        int left = limits[0];
        for (int limit : limits) left = Math.max(left, limit);
        int right = 1000000000;
        int answer = left;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isValuable(mid)) {
                right = mid - 1;
            }
            else {
                answer = mid;
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
