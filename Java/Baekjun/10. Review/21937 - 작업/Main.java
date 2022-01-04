import java.util.*;
import java.io.*;

public class Main {

    static int n, m, x;
    static int count;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            graph[from].add(to);
        }
        x = Integer.parseInt(br.readLine());

    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        que.add(x);
        visit[x] = true;

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                if (visit[to]) continue;
                
                que.add(to);
                visit[to] = true;
                count++;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}