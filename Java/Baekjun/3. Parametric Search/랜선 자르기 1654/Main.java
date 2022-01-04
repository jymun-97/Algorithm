import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K, N;
    static int[] cables;
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cables = new int[K];

        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
        }
    }

    static boolean isValuable(long size) {
        long count = 0;

        for (int i = 0; i < K; i++) {
            count += cables[i] / size;
        }

        return count >= N;
    }

    static void solve() {
        long left = 1;
        long right = Integer.MAX_VALUE;
        long answer = left;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (isValuable(mid)) {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
