import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int SAFE = 0, WALL = 1, VIRUS = 2;
    static int R, C, answer = -1;
    static int[][] map;
    static int[][] newMap;
    static boolean[][] visit;
    static Queue<Virus> que = new LinkedList<>();
    static int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };


    static class Virus {
        int row, col;

        public Virus(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        newMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void makeWalls(int count, int startR, int startC) {
        if (count == 3) {
            spreadVirus();
            return;
        }

        for (int i = startC; i < C; i++) {
            if (map[startR][i] == SAFE) {
                map[startR][i] = WALL;
                makeWalls(count + 1, startR, i + 1);
                map[startR][i] = SAFE;
            }
        }
            
        for (int i = startR + 1; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == SAFE) {
                    map[i][j] = WALL;
                    makeWalls(count + 1, i, j + 1);
                    map[i][j] = SAFE;
                }
            }
        }
    }

    static void spreadVirus() {
        initNewMap();

        while (!que.isEmpty()) {
            Virus virus = que.poll();

            for (int i = 0; i < dir.length; i++) {
                int nr = virus.row + dir[i][0];
                int nc = virus.col + dir[i][1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (map[nr][nc] == SAFE && !visit[nr][nc]) {
                    newMap[nr][nc] = VIRUS;
                    que.add(new Virus(nr, nc));
                    visit[nr][nc] = true;
                }
            }
        }

        setAnswer();
        que.clear();
    }

    static void setAnswer() {
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (newMap[i][j] == SAFE) count++;
            }
        }
        answer = Integer.max(answer, count);
    }

    static void initNewMap() {
        visit = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newMap[i][j] = map[i][j];
                if (map[i][j] == VIRUS) {
                    que.add(new Virus(i, j));
                    visit[i][j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        makeWalls(0, 0, 0);
        System.out.println(answer);
    }
}
