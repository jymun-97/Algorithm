import java.util.*;
import java.io.*;

class Node {
    int x, y, v;
    public Node(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }
}

public class Main {
    static int n, c;
    static final int MAX = 100000;
    static PriorityQueue<Node> que = new PriorityQueue<>( new Comparator<Node>() {
        public int compare(Node n, Node other) {
            if (n.y != other.y) return n.y - other.y;
            else return other.x - n.x;
        }
    });

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            que.add(new Node(x, y, v));
        }
    }

    static void solve() {
        int x = MAX, y = 0;
        int count = 0;
        long sum = 0, ans = 0;
        PriorityQueue<Node> selected = new PriorityQueue<>((o1, o2) -> o2.x - o1.x);

        while (x >= 0 && y <= MAX) {
            while (!que.isEmpty() && que.peek().y == y) {
                Node node = que.poll();
                if (node.x > x) break;

                count++;
                sum += node.v;
                selected.add(node);
            }

            if (count > c) {
                int targetX = selected.peek().x;
                while (!selected.isEmpty() && selected.peek().x == targetX) {
                    Node node = selected.poll();
                    x = node.x - 1;
                    sum -= node.v;
                    count--;
                }
            }
            else {
                ans = Math.max(ans, sum);
                if (!que.isEmpty()) y = que.peek().y;
                else break;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}