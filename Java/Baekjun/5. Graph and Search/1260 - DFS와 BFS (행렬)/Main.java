import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int nVertex, nEdge, start;
    static int[][] graph;
    static boolean[] visit;
    static StringBuilder sb;


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nVertex = Integer.parseInt(st.nextToken());
        nEdge = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        graph = new int[nVertex + 1][nVertex + 1];

        init();

        for (int i = 1; i <= nEdge; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from][to] = 1;
            graph[to][from] = 1;
        }
    }

    static void init() {
        visit = new boolean[nVertex + 1];
        sb = new StringBuilder();
    }

    static void dfs(int from) {
        visit[from] = true;
        sb.append(from).append(' ');

        for (int to = 1; to <= nVertex; to++) {
            if (graph[from][to] != 0 && !visit[to])
                dfs(to); 
        }
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visit[start] = true;

        while (!que.isEmpty()) {
            int from = que.poll();
            sb.append(from).append(' ');

            for (int to = 1; to <= nVertex; to++) {
                if (graph[from][to] != 0 && !visit[to]) {
                    que.add(to);
                    visit[to] = true;
                }
            }
        }
        
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        dfs(start);
        System.out.println(sb);
        init();
        bfs();
    }
}
