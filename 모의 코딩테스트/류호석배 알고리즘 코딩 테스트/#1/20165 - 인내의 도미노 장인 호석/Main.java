import java.util.*;
import java.io.*;

class Domino {
    int h;
    char state;

    public Domino(int h, char state) {
        this.h = h;
        this.state = state;
    }
}

public class Main {
    static int r, c, round;
    static Domino[][] map;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        round = Integer.parseInt(st.nextToken());

        map = new Domino[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= c; j++) {
                map[i][j] = new Domino(Integer.parseInt(st.nextToken()), 'S');
            }
        }
    }

    static void solve() throws IOException {
        int score = 0;
        while (round-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);

            if (map[row][col].state == 'F')
                continue;

            map[row][col].state = 'F';
            score++;

            for (int i = map[row][col].h - 1; i > 0; i--) {
                row += dir(d)[0];
                col += dir(d)[1];

                if (row < 1 || row > r || col < 1 || col > c)
                    break;

                if (map[row][col].state == 'S') {
                    map[row][col].state = 'F';
                    score++;
                    i = Integer.max(i, map[row][col].h);
                }
            }

            st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            map[row][col].state = 'S';
        }

        StringBuilder sb = new StringBuilder();
        sb.append(score).append('\n');

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                sb.append(map[i][j].state).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static int[] dir(char d) {
        switch (d) {
            case 'E':
                return new int[] { 0, 1 };
            case 'W':
                return new int[] { 0, -1 };
            case 'S':
                return new int[] { 1, 0 };
            case 'N':
                return new int[] { -1, 0 };
            default:
                return null;
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}