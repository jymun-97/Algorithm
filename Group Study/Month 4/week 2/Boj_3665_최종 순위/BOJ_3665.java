import java.util.*;
import java.io.*;

public class BOJ_3665 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static int[] ranked;
    static int[][] infos;
    static HashSet<Integer>[] set; 

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        ranked = new int[n + 1];
        set = new HashSet[n + 1];

        for (int i = 1; i <= n; i++) set[i] = new HashSet<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ranked[i + 1] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        infos = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            infos[i][0] = Integer.parseInt(st.nextToken());
            infos[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for (int i = 1; i <= n; i++) {
            int target = ranked[i];

            if (i > 1) {
                set[target].addAll(set[ranked[i - 1]]);
                set[target].add(ranked[i - 1]);
            }
        }

        for (int[] info : infos) {
            int a = info[0];
            int b = info[1];

            if (!set[a].contains(b) && !set[b].contains(a)) {
                sb.append('?').append('\n');
                return;
            }
            if (set[a].contains(b)) {
                set[a].remove(b);
                set[b].add(a);
            }
            else {
                set[b].remove(a);
                set[a].add(b);
            }
        }

        int[] ranking = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int rank = set[i].size() + 1;
            if (ranking[rank] != 0) {
                sb.append("IMPOSSIBLE").append('\n');
                return;
            }
            ranking[rank] = i;
        }

        for (int i = 1; i <= n; i++) sb.append(ranking[i]).append(' ');
        sb.append('\n');
    }

    public static void main(String[] args) throws Exception {

        // int t = Integer.parseInt(br.readLine());

        // while (t --> 0) {
        //     input();
        //     solve();
        // }

        // System.out.println(sb);

        HashSet<Integer> test = new HashSet<>();
        test.add(5);
        test.add(3);
        test.add(2);
        test.add(10);
        System.out.println(Arrays.toString(test.toArray()));
    }
}