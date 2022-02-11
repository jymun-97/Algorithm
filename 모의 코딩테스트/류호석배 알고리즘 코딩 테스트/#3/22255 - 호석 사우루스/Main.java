import java.util.*;
import java.io.*;

public class Main {
    static int r, c, startR, startC, endR, endC;
    static int[][] map;
    static int[][][] result;
    static int[][][] dir = {
        { {1, 0}, {-1, 0}, {0, 1}, {0, -1} },
        { {1, 0}, {-1, 0} },
        { {0, 1}, {0, -1} }
    };
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r + 1][c + 1];
        result = new int[3][r + 1][c + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j <= r; j++) {
                for (int k = 1; k <= c; k++) {
                    result[i][j][k] = -1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken());
        startC = Integer.parseInt(st.nextToken());
        endR = Integer.parseInt(st.nextToken());
        endC = Integer.parseInt(st.nextToken());

        for (int row = 1; row <= r; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= c; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        que.add(startR);
        que.add(startC);
        que.add(1);
        result[1][startR][startC] = 0;

        while (!que.isEmpty()) {
            int row = que.poll();
            int col = que.poll();
            int move = que.poll();
            if (row == endR && col == endC) continue;

            for (int i = 0; i < dir[move].length; i++) {
                int nr = row + dir[move][i][0];
                int nc = col + dir[move][i][1];
                int next = (move + 1) % 3;

                if (nr < 1 || nr > r || nc < 1 || nc > c) continue;
                if (map[nr][nc] != -1) {
                    if (result[next][nr][nc] != -1 && result[next][nr][nc] <= result[move][row][col] + map[nr][nc]) continue;
                    que.add(nr);
                    que.add(nc);
                    que.add(next);
                    result[next][nr][nc] = result[move][row][col] + map[nr][nc];
                }
            }
        }
        if (result[0][endR][endC] == -1 && result[1][endR][endC] == -1 && result[2][endR][endC] == -1) {
            System.out.println(-1);
            return;
        }

        int ans = Integer.MAX_VALUE;
        for (int m = 0; m < 3; m++) {
            if (result[m][endR][endC] != -1) {
                ans = Math.min(ans, result[m][endR][endC]);
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}