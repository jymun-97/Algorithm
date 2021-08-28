import java.util.*;
import java.io.*;

public class Boj_1764 {

    static int n, m, count;
    static String[] noSeen, noHeard;
    static StringBuilder sb = new StringBuilder();


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        noSeen = new String[n];
        noHeard = new String[m];
        for (int i = 0; i < n; i++) noSeen[i] = br.readLine();
        for (int i = 0; i < m; i++) noHeard[i] = br.readLine();
    }

    static void solve() {
        Arrays.sort(noSeen);
        Arrays.sort(noHeard);

        for (String str : noSeen) {
            BinarySearch(str);
        }
        sb.insert(0, count + "\n");
        System.out.println(sb);
    }

    static void BinarySearch(String str) {
        int left = 0;
        int right = m - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (str.compareTo(noHeard[mid]) < 0) {
                right = mid - 1;
            }
            else if (str.compareTo(noHeard[mid]) > 0) {
                left = mid + 1;
            }
            else {
                count++;
                sb.append(str).append('\n');
                return;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}