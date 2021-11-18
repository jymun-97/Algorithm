import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static ArrayList<Integer>[] graph;
    static int[] count, indeg, temp;
    static int[][] records;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        count = new int[n + 1];
        indeg = new int[n + 1];
        temp = new int[n + 1];
        records = new int[k][2];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            records[i][0] = Integer.parseInt(st.nextToken());
            records[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for (int[] record : records) {
            if (!isValid(record[0], record[1])) {
                System.out.println("Lier!");
                return;
            }
        }
        System.out.println("King-God-Emperor");
    }

    static boolean isValid(int action, int num) {
        if (action == 1) {
            if (temp[num] < indeg[num]) return false;

            count[num]++;
            if (count[num] == 1) {
                for (int to : graph[num]) temp[num]++;
            }
        }
        else {
            if (count[num] == 0) return false;

            count[num]--;
            if (count[num] == 0) {
                for (int to : graph[num]) temp[to]--;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}