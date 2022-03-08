import java.util.*;
import java.io.*;

public class BOJ_11054 {

    static int n;
    static int[] arr, left, right;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        left = new int[n];
        right = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        if (n == 1) {
            System.out.println(1);
            return;
        }

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            int idx = i - 1;
            int target = i;
            while (idx >= 0) {
                if (arr[idx] < arr[i] && left[idx] > left[target]) {
                    target = idx;
                    if (left[target] == target) break;
                }
                idx--;
            }
            left[i] = (target < 0) ? 1 : left[target] + 1;
        }

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            int idx = i + 1;
            int target = i;
            while (idx < n) {
                if (arr[idx] < arr[i] && right[idx] > right[target]) {
                    target = idx;
                    if (right[target] == target) break;
                }
                idx++;
            }

            right[i] = (target >= n) ? 1 : right[target] + 1;
        }

        int max = left[0] + right[0] - 1;
        for (int i = 1; i < n; i++) {
            int cand = left[i] + right[i] - 1;
            max = Math.max(max, cand);
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}