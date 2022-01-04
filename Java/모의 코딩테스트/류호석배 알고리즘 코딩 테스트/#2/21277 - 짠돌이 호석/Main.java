import java.util.*;
import java.io.*;

public class Main {
    static int n, m, r, c;
    static int[][] base, map, result;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        base = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                base[i][j] = line.charAt(j) - 48;
            }
        }

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j) - 48;
            }
        }
    }

    static void solve() {
        ArrayList<Integer> cand = new ArrayList<>();
        for (int rtt = 0; rtt < 4; rtt++) {
            for (int nRotate = 0; nRotate < 4; nRotate++) {
                cand.add((n + r) * Math.max(m, c));
                cand.add((m + c) * Math.max(n, r));

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        int result = combine(i, j);
                        if (result != -1) cand.add(result);
                    }
                }

                rotate();
            }
            rotateBase();
        }

        Collections.sort(cand);
        System.out.println(cand.get(0));
    }

    static int combine(int sR, int sC) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int row = sR + i;
                int col = sC + j;

                if (row >= n || col >= m) continue;
                if (base[row][col] + map[i][j] > 1) return -1;
            }
        }
        return Math.max(n, sR + r) * Math.max(m, sC + c); 
    }

    static void rotate() {
        int[][] newMap = new int[c][r];

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                newMap[i][j] = map[j][c - 1 - i];
            }
        }
        map = newMap;
        int temp = r;
        r = c;
        c = temp;
    }

    static void rotateBase() {
        int[][] newMap = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = base[j][m - 1 - i];
            }
        }
        base = newMap;
        int temp = n;
        n = m;
        m = temp;
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}