import java.util.*;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        Point other = (Point)o;
        return x == other.x && y == other.y;
    }
    @Override
    public int hashCode() {
        return Integer.toString(x).hashCode() + Integer.toString(y).hashCode();
    }
}
class Edge {
    Point p1, p2;
    public Edge(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    @Override
    public boolean equals(Object o) {
        Edge other = (Edge)o;
        return p1.equals(other.p1) && p2.equals(other.p2); 
    }
}

class Solution {
    public int solution(int[] arrows) {
        int answer = 0;
        int[][] dir = { {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1} };
        int x = 0, y = 0;

        HashSet<Edge> pass = new HashSet<>();
        HashSet<Point> visit = new HashSet<>();
        visit.add(new Point(x, y));

        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) {
                int nx = x + dir[arrow][0];
                int ny = y + dir[arrow][1];

                Point now = new Point(x, y);
                Point next = new Point(nx, ny);
                Edge edge1 = new Edge(now, next);
                Edge edge2 = new Edge(next, now);

                if (visit.contains(next)) {
                    if (!pass.contains(edge1) && !pass.contains(edge2)) {
                        answer++;
                    }
                }
                visit.add(next);
                pass.add(edge1);
                pass.add(edge2);

                x = nx;
                y = ny;
            }
        }
        return answer;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        HashSet<Point> set = new HashSet<>();
        set.add(new Point(0, 0));

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(2, 0);
        Point p4 = new Point(2, 0);
        Point p5 = new Point(0, 0);
        Point p6 = new Point(0, 1);
        
        System.out.println(set.contains(p1));
        System.out.println(set.contains(p2));
        System.out.println(set.contains(p3));
        System.out.println(set.contains(p4));
        System.out.println(set.contains(p5));
        System.out.println(set.contains(p6));

        for (Point p : set) {
            System.out.println(p.x + ", " + p.y);
        }
    }
}
