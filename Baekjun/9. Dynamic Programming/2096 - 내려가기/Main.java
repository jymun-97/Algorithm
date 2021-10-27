import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] a;
    static int[][] max, min;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n + 1][3];
        max = new int[n + 1][3];
        min = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = Integer.parseInt(st.nextToken());
            a[i][2] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        max[n][0] = min[n][0] = a[n][0];
        max[n][1] = min[n][1] = a[n][1];
        max[n][2] = min[n][2] = a[n][2];

        for (int i = n - 1; i > 0; i--) {
            max[i][0] = a[i][0] + Integer.max(max[i + 1][0], max[i + 1][1]);
            max[i][1] = a[i][1] + Integer.max(max[i + 1][2], Integer.max(max[i + 1][0], max[i + 1][1]));
            max[i][2] = a[i][2] + Integer.max(max[i + 1][1], max[i + 1][2]);

            min[i][0] = a[i][0] + Integer.min(min[i + 1][0], min[i + 1][1]);
            min[i][1] = a[i][1] + Integer.min(min[i + 1][2], Integer.min(min[i + 1][0], min[i + 1][1]));
            min[i][2] = a[i][2] + Integer.min(min[i + 1][1], min[i + 1][2]);
        }

        int maxScore = Integer.max(max[1][0], Integer.max(max[1][1], max[1][2]));
        int minScore = Integer.min(min[1][0], Integer.min(min[1][1], min[1][2]));
        System.out.println(maxScore + " " + minScore);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}