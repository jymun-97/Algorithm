import java.util.*;

class Solution {

    class Point {
        int row, col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public boolean equals(Object o) {
            Point p = (Point)o;
            return row == p.row && col == p.col;
        }
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            return sb.append(row).append(col).toString().hashCode();
        }
    }
    HashSet<Point> selected;
    char[][] map;
    int r, c;
    int[][] dir = {{1, 0}, {0, 1}, {1, 1}};

    public int solution(int m, int n, String[] board) {
        this.r = m;
        this.c = n;
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String line = board[i];
            for (int j = 0; j < c; j++) map[i][j] = line.charAt(j);
        }

        while (true) {
            selected = new HashSet<>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (isValuable(i, j)) selected.add(new Point(i, j));
                }
            }
            if (selected.size() == 0) break;
            update();
            // print();
        }

        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == '.') answer++;
            }
        }
        return answer;
    }

    void print() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void update() {
        for (Point point : selected) {
            map[point.row][point.col] = '.';
            for (int i = 0; i < dir.length; i++) map[point.row + dir[i][0]][point.col + dir[i][1]] = '.';
        }
        
        for (int col = 0; col < c; col++) {
            ArrayList<Character> list = new ArrayList<>();
            ArrayList<Character> remains = new ArrayList<>();

            for (int row = 0; row < r; row++) {
                if (map[row][col] == '.') list.add(map[row][col]);
                else remains.add(map[row][col]);
            }
            
            list.addAll(remains);
            for (int row = 0; row < r; row++) map[row][col] = list.get(row);
        }
    }

    boolean isValuable(int row, int col) {
        if (map[row][col] == '.') return false;
        for (int i = 0; i < dir.length; i++) {
            int nr = row + dir[i][0];
            int nc = col + dir[i][1];

            if (!inRange(nr, nc)) return false;
            if (map[row][col] != map[nr][nc]) return false;
        }
        return true;
    }

    boolean inRange(int row, int col) {
        return row >= 0 && row < r && col >= 0 && col < c;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

        System.out.println(s.solution(m, n, board));
    }
}
