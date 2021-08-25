import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m, count;
    static int vertex, edge;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException {
        visit = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    static void solve(int testCase) {
        int start = 0;

        while ((start = getStart()) != 0) {
            bfs(start);

            if (isTree()) {
                count++;
            }
            vertex = edge = 0;
        }

        sb.append("Case ").append(testCase).append(": ");
        if (count == 0) {
            sb.append("No trees.").append('\n');
        }
        else if (count == 1) {
            sb.append("There is one tree.").append('\n');
        }
        else {
            sb.append("A forest of ").append(count).append(" trees.").append('\n');
        }
        
        count = 0;
    }

    static int getStart() {
        for (int i = 1; i <= n; i++) {
            if (!visit[i])
                return i;
        }
        return 0;
    }

    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();

        que.add(start);
        visit[start] = true;
        vertex++;

        while (!que.isEmpty()) {
            int from = que.poll();

            edge += graph[from].size();
            for (int to : graph[from]) {
                if (!visit[to]) {
                    que.add(to);
                    visit[to] = true;
                    vertex++;
                }
            }
        }
    }

    static boolean isTree() {
        edge /= 2;

        return (vertex - 1 == edge) ? true : false;
    }

    public static void main(String[] args) throws Exception {
        int testCase = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                System.out.println(sb);
                break;
            }
            input();
            solve(testCase++);
        }
    }
}
