import java.io.*;
import java.util.*;

public class Main {

    static int n, c;
    static int[] locations;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        locations = new int[n];

        c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            locations[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(locations);
    }

    static boolean isValuable(int distance) {
        int count = 1;
        int last = locations[0];

        for (int i = 1; i < n; i++) {
            if (distance <= locations[i] - last) {
                count++;
                last = locations[i];
            }
        }
        return count >= c;
    }

    static void solve() {
        int left = 0;
        int right = locations[n - 1];
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

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
