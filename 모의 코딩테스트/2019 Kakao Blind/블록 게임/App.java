import java.util.*;

class Point {
    int row, col;
    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
class Shape {
    int index, nLayer;
    ArrayList<Point> points;
    ArrayList<Point> needs;

    public Shape(int index) {
        this.index = index;
        points = new ArrayList<>();
        needs = new ArrayList<>();
    }
    
    public boolean isValuable() {
        ArrayList<Point>[] layers = new ArrayList[3];
        for (int i = 0; i < 3; i++) layers[i] = new ArrayList<>();

        int pre = points.get(0).row;
        int layer = 0;
        for (Point point : points) {
            if (point.row != pre) layer++;
            
            layers[layer].add(point);
            pre = point.row;
        }

        nLayer = (layers[2].size() == 0) ? 2 : 3;

        int preLayer = layers[0].size();
        for (int i = 1; i < nLayer; i++) {
            if (layers[i].size() == 0) break;
            if (preLayer > layers[i].size()) return false;

            preLayer = layers[i].size();
        }

        setNeeds(layers);
        return true;
    }

    private void setNeeds(ArrayList<Point>[] layers) {
        Point top = layers[0].get(0);
        
        if (nLayer == 2) {
            Point under = layers[1].get(1);
            if (top.col == under.col) {
                needs.add(new Point(top.row, top.col - 1));
                needs.add(new Point(top.row, top.col + 1));
            }
            else if (top.col > under.col) {
                needs.add(new Point(top.row, top.col - 1));
                needs.add(new Point(top.row, top.col - 2));
            }
            else {
                needs.add(new Point(top.row, top.col + 1));
                needs.add(new Point(top.row, top.col + 2));
            }
        }
        else {
            Point under = layers[2].get(1);
            if (top.col >= under.col) {
                needs.add(new Point(top.row, top.col - 1));
                needs.add(new Point(top.row + 1, top.col - 1));
            }
            else {
                needs.add(new Point(top.row, top.col + 1));
                needs.add(new Point(top.row + 1, top.col + 1));
            }
        }
    }
}
class Solution {

    HashMap<Integer, Shape> map = new HashMap<>();
    int[][] board;
    int n;
    HashSet<Integer> visit = new HashSet<>();
    HashMap<Integer, Boolean> erasible = new HashMap<>();
    
    public int solution(int[][] board) {
        int answer = 0;

        this.board = board;
        this.n = board.length;
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int index = board[row][col];
                if (index == 0) continue;

                Shape shape = map.getOrDefault(index, new Shape(index));
                shape.points.add(new Point(row, col));
                map.put(index, shape);
            }
        }
        for (int i : map.keySet()) {
            Shape shape = map.get(i);
            if (!shape.isValuable()) {
                visit.add(i);
                erasible.put(i, false);
            }
        }
        for (int i : map.keySet()) {
            Shape shape = map.get(i);
            if (isErasible(shape)) {
                answer++;
                System.out.println(shape.index);
            }
        }

        return answer;
    }

    boolean isErasible(Shape shape) {
        if (visit.contains(shape.index)) return erasible.get(shape.index);

        for (Point need : shape.needs) {
            int col = need.col;
            int row = 0;

            while (row < n) {
                if (board[row][col] == shape.index) break;
                if (board[row][col] != 0) {
                    Shape target = map.get(board[row][col]);

                    if (!isErasible(target)) {
                        visit.add(shape.index);
                        erasible.put(shape.index, false);
                        return false;
                    }
                }
                row++;
            }
        }

        visit.add(shape.index);
        erasible.put(shape.index, true);
        return true;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();

        int[][] board = { {2,2,0,0},
                        {1,2,0,4},
                        {1,2,0,4},
                        {1,1,4,4} };

        System.out.println(s.solution(board));
    }
}
