import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final char EMPTY = '.', WATER = '*', ROCK = 'X', BEAVER = 'D', HEDGEHOG = 'S';
    static int R, C;
    static int startRow, startCol;
    static int destRow, destCol;
    static char[][] map;
    static boolean[][] visit;
    static boolean[][] waterVisit;
    static int[][] distance;
    static int[][] waterDist;
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static Queue<Integer> waterQue = new LinkedList<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R + 1][C + 1];
        visit = new boolean[R + 1][C + 1];
        waterVisit = new boolean[R + 1][C + 1];
        distance = new int[R + 1][C + 1];
        waterDist = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            String line = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = line.charAt(j - 1);

                if (map[i][j] == HEDGEHOG) {
                    startCol = j;
                    startRow = i;
                }
                else if (map[i][j] == WATER)  {
                    waterQue.add(i);
                    waterQue.add(j);
                    waterVisit[i][j] = true;
                }
                else if (map[i][j] == BEAVER) {
                    destCol = j;
                    destRow = i;
                }
            }
        }
    }

    static void solve() {
        bfs();
        System.out.println(distance[destRow][destCol] == 0 ? "KAKTUS" : distance[destRow][destCol]);
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(startRow);
        que.add(startCol);
        visit[startRow][startCol] = true;
        waterFlows();

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();
            
            for (int i = 0; i < dir.length; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr <= 0 || nr > R || nc <= 0 || nc > C) continue;

                if (map[nr][nc] != WATER && map[nr][nc] != ROCK && !visit[nr][nc] && (distance[row][col] + 1 < waterDist[nr][nc] || waterDist[nr][nc] == 0)) {
                    que.add(nr);
                    que.add(nc);
                    visit[nr][nc] = true;
                    distance[nr][nc] = distance[row][col] + 1;
                }
            }
        }
    }

    static void waterFlows() {
        while (!waterQue.isEmpty()) {
            int row = waterQue.poll();
            int col = waterQue.poll();

            for (int i = 0; i < dir.length; i++) {
                int nr = row + dir[i][0];
                int nc = col + dir[i][1];

                if (nr <= 0 || nr > R || nc <= 0 || nc > C) continue;

                if (map[nr][nc] != ROCK && map[nr][nc] != BEAVER && !waterVisit[nr][nc]) {
                    waterQue.add(nr);
                    waterQue.add(nc);
                    waterVisit[nr][nc] = true;
                    waterDist[nr][nc] = waterDist[row][col] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
