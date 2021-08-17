import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_15970 {
    static class Point implements Comparable<Point> {
        int location;
        int color;

        public Point(int location, int color) {
            this.location = location;
            this.color = color;
        }

        public int compareTo(Point other) {
            if (color != other.color) return color - other.color;

            return location - other.location;
        }
    }
    static int N;
    static Point[] points;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(points);
    }

    static void getResult() {
        int sum = points[1].location - points[0].location;

        for (int i = 1; i < N - 1; i++) {
            // 이전 색깔과 같은 경우
            if (points[i].color == points[i - 1].color) {
                // 1. 다음 색깔까지 같은 경우
                if (points[i].color == points[i + 1].color) {
                    sum += Math.min(leftDistance(i), rightDistance(i));
                }
                // 2. 오직 이전 색깔과 같은 경우
                else {
                    sum += leftDistance(i);
                }
            }
            // 이전 색깔과 다른 경우
            else {
                sum += rightDistance(i);
            }
        }
        sum += leftDistance(N - 1);
        System.out.println(sum);
    }

    static int leftDistance(int index)  { return points[index].location - points[index - 1].location; }
    static int rightDistance(int index) { return points[index + 1].location - points[index].location; }

    public static void main(String[] args) throws Exception {
        input();
        getResult();
    }
}
