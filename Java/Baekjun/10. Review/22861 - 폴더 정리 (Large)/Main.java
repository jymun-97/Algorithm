import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k, q, idx;
    static int[] count, parentOf;
    static ArrayList<Integer>[] graph;
    static HashSet<String>[] fileOf;
    static HashMap<String, Integer> toIndex = new HashMap<>();

    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        fileOf = new HashSet[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            fileOf[i] = new HashSet<>();
        }

        count = new int[n + 1];
        parentOf = new int[n + 1];
        toIndex.put("main", idx++);
        parentOf[0] = -1;

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            if (!toIndex.containsKey(str)) toIndex.put(str, idx++);
            int from = toIndex.get(str);

            str = st.nextToken();
            boolean isFolder = (Integer.parseInt(st.nextToken()) == 0) ? false : true;

            if (isFolder) {
                if (!toIndex.containsKey(str)) toIndex.put(str, idx++);
                int to = toIndex.get(str);
                
                graph[from].add(to);
                parentOf[to] = from;
            }
            else {
                count[from]++;
                fileOf[from].add(str);
            }
        }

        k = Integer.parseInt(br.readLine());
        while (k --> 0) {
            st = new StringTokenizer(br.readLine());
            String[] tokens = st.nextToken().split("/");
            int from = toIndex.get(tokens[tokens.length - 1]);

            tokens = st.nextToken().split("/");
            int to = toIndex.get(tokens[tokens.length - 1]);

            fileOf[to].addAll(fileOf[from]);
            count[to] = fileOf[to].size();
            graph[to].add(from);
            
            fileOf[from].clear();
            count[from] = 0;

            graph[parentOf[from]].remove(graph[parentOf[from]].indexOf(from));
        }

        StringBuilder sb = new StringBuilder();
        dfs(0);
        q = Integer.parseInt(br.readLine());
        while (q --> 0) {
            String[] tokens = (br.readLine()).split("/");
            String target = tokens[tokens.length - 1];

            sb.append(fileOf[toIndex.get(target)].size()).append(' ').append(count[toIndex.get(target)]).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(int from) {
        if (graph[from].size() == 0) return;

        for (int to : graph[from]) {
            dfs(to);
            count[from] += count[to];
            fileOf[from].addAll(fileOf[to]);
        }
    }

    public static void main(String[] args) throws Exception {

        solve();
    }
}