import java.util.*;
import java.io.*;

class Point {
    int row, col;
    public Point(int r, int c) {
        row = r;
        col = c;
    }
    @Override
    public boolean equals(Object o) {
        Point other = (Point)o;
        return row == other.row && col == other.col;
    }
    @Override
    public int hashCode() {
        return (row + " " + col).hashCode();
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int r, c, n, ans;
    static char[][] map;
    static int[][] dist, dp;
    static HashMap<Integer, Point> toPoint;
    static HashMap<Point, Integer> toIndex;

    static void input() throws IOException {
        n = 1;
        ans = Integer.MAX_VALUE;
        map = new char[r + 1][c + 1];
        toPoint = new HashMap<>();
        toIndex = new HashMap<>();

        for (int i = 1; i <= r; i++) {
            String line = br.readLine();
            for (int j = 1; j <= c; j++) {
                map[i][j] = line.charAt(j - 1);

                if (map[i][j] == 'o') {
                    Point start = new Point(i, j);
                    toIndex.put(start, 0);
                    toPoint.put(0, start);
                }
                else if (map[i][j] == '*') {
                    Point point = new Point(i, j);
                    toIndex.put(point, n);
                    toPoint.put(n++, point);
                }
            }
        }
        dp = new int[n][n];
    }

    static void solve() {
        for (int i = 0; i < n - 1; i++) {
            Point start = toPoint.get(i);
            bfs(start);

            for (int j = i + 1; j < n; j++) {
                Point end = toPoint.get(j);
                if (dist[end.row][end.col] == -1) {
                    sb.append(-1).append('\n');
                    return;
                }
                dp[toIndex.get(start)][toIndex.get(end)] = dist[end.row][end.col];
                dp[toIndex.get(end)][toIndex.get(start)] = dist[end.row][end.col];
            }
        }
        dfs(1, new HashSet<>(0), new int[n]);
        sb.append(ans).append('\n');
    }

    static void bfs(Point start) {
        dist = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) for (int j = 1; j <= c; j++) dist[i][j] = -1;

        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<Point> que = new LinkedList<>();
        que.add(start);
        dist[start.row][start.col] = 0;

        while (!que.isEmpty()) {
            Point point = que.poll();

            for (int i = 0; i < 4; i++) {
                Point next = new Point(point.row + dir[i][0], point.col + dir[i][1]);

                if (next.row < 1 || next.row > r || next.col < 1 || next.col > c) continue;
                if (map[next.row][next.col] != 'x' && dist[next.row][next.col] == -1) {
                    que.add(next);
                    dist[next.row][next.col] = dist[point.row][point.col] + 1;
                }
            }
        }
    }

    static void dfs(int k, HashSet<Integer> visit, int[] route) {
        if (k == n) {
            int cand = 0;
            for (int i = 0; i < n - 1; i++) {
                cand += dp[route[i]][route[i + 1]];
            }
            ans = Math.min(ans, cand);
            return;
        }

        for (int i = 1; i < n; i++) {
            if (visit.contains(i)) continue;

            route[k] = i;
            visit.add(i);
            dfs(k + 1, visit, route);
            visit.remove(i);
        }
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            
            if (r == 0 && c == 0) break;

            input();
            solve();
        }
        System.out.println(sb.toString());
    }
}