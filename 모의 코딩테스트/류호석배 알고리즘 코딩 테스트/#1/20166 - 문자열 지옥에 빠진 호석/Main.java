import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static char[][] map;
    static String[] targets;
    static String str;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1} };
    static HashMap<String, Integer> hashMap = new HashMap<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        targets = new String[k];
        map = new char[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = line.charAt(j - 1);
            }
        }

        for (int i = 0; i < k; i++) targets[i] = br.readLine();
    }

    static void solve() {
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                str = "";
                dfs(r, c);
            }
        }
    }

    static void dfs(int r, int c) {
        str += Character.toString(map[r][c]);
        addString(str);
        if (str.length() == 5) return;


        for (int i = 0; i < 8; i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];

            if (row == 0) row = n;
            else if (row == n + 1) row = 1;
            if (col == 0) col = m;
            else if (col == m + 1) col = 1;
            
            dfs(row, col);
            if (str.length() > 1)
                str = str.substring(0, str.length() - 1);
        }
    }

    static void addString(String str) {
        if (hashMap.containsKey(str))
            hashMap.put(str, hashMap.get(str) + 1); 
        else 
            hashMap.put(str, 1);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            if (hashMap.containsKey(targets[i]))
                sb.append(hashMap.get(targets[i])).append('\n');
            else 
                sb.append(0).append('\n');
        }
        System.out.println(sb);
    }
}