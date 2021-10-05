import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static int[] nBuildings;
    static ArrayList<Integer> order = new ArrayList<>();
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();

    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nBuildings = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());    
            int child = Integer.parseInt(st.nextToken());    

            graph[child].add(parent);
        }

        while (k --> 0) {
            st = new StringTokenizer(br.readLine());
            int action = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken());

            if (action == 1) {
                for (int parent : graph[building]) {
                    if (nBuildings[parent] <= 0) {
                        sb.append("Lier!");
                        return;
                    }
                }
                nBuildings[building]++;
            }
            else {
                if (nBuildings[building] <= 0) {
                    sb.append("Lier!");
                    return;
                }
                nBuildings[building]--;
            }
        }

        sb.append("King-God-Emperor");
    }
    
    public static void main(String[] args) throws Exception {
        
        solve();
        System.out.println(sb);
    }
}
