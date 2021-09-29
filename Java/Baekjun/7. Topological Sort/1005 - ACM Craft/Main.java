import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, k, target;
    static int[] indeg, needs, finish;
    static ArrayList<Integer>[] graph;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        needs = new int[n + 1];
        indeg = new int[n + 1]; 
        finish = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) { needs[i] = Integer.parseInt(st.nextToken()); }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            indeg[to]++;
        }

        target = Integer.parseInt(br.readLine());
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indeg[i] == 0) {
                que.add(i);
                finish[i] = needs[i];
            }
        }

        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                indeg[to]--;
                if (indeg[to] == 0) que.add(to); 
                finish[to] = Integer.max(finish[to], finish[from] + needs[to]);
            }
        }
        sb.append(finish[target]).append('\n');
    } 

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            input();
            solve();
        }

        System.out.println(sb);
    }
}
