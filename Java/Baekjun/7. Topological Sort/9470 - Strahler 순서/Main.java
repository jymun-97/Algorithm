import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int k, m, p;
    static int[] indeg, strahler;
    static ArrayList<Integer>[] graph;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        indeg = new int[m + 1];
        strahler = new int[m + 1];
        
        graph = new ArrayList[m + 1];
        for (int i = 1; i <= m; i++) graph[i] = new ArrayList<>();

        while (p --> 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            indeg[to]++;    
        }
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        int max = 1;
        int[] flags = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            if (indeg[i] == 0) {
                que.add(i);
                strahler[i] = 1;
            }
        }
        while (!que.isEmpty()) {
            int from = que.poll();

            for (int to : graph[from]) {
                if (--indeg[to] == 0) que.add(to);

                if (strahler[to] < strahler[from]) {
                    strahler[to] = strahler[from];
                    flags[to] = strahler[from];
                }
                else if (strahler[to] == strahler[from]) {
                    if (flags[to] != strahler[from]) {
                        flags[to] = strahler[from];
                    }
                    else {
                        strahler[to] = strahler[from] + 1;
                    }
                }

                if (max < strahler[to]) max = strahler[to];
            }
        }
        sb.append(k).append(' ').append(max).append('\n');
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        
        while (T --> 0) {
            input();
            solve();
        }

        System.out.println(sb);
    }
}
