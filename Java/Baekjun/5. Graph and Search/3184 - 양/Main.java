import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final char SHEEP = 'o';
    static final char EMPTY = '.';
    static final char WOLF = 'v';
    static final char FENCE = '#';

    static int r, c;
    static int nSheepInAnArea, nWolfInAnArea;
    static int sumSheep, sumWolf;
    static char[][] map;
    static boolean[][] visit;

    static int[][] dir = {
        {0, 1}, {1, 0}, {-1, 0}, {0, -1}
    };

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();

            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }

    static void solve() {
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                nSheepInAnArea = nWolfInAnArea = 0;

                if (!visit[row][col] && map[row][col] != FENCE) {
                    dfs(row, col);
                    if (nSheepInAnArea > nWolfInAnArea) 
                        sumSheep += nSheepInAnArea;
                    else 
                        sumWolf += nWolfInAnArea;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(sumSheep).append(' ').append(sumWolf);
        System.out.println(sb);
    }

    static void dfs(int row, int col) {
        visit[row][col] = true;
        if (map[row][col] == SHEEP) nSheepInAnArea++;
        if (map[row][col] == WOLF) nWolfInAnArea++;

        for (int i = 0; i < dir.length; i++) {
            int nr = row + dir[i][0];
            int nc = col + dir[i][1];

            if (nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
            if (!visit[nr][nc] && map[nr][nc] != FENCE) {
                dfs(nr, nc);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
