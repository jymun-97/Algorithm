import java.util.*;
import java.io.*;

public class Main {
    static int n, count;
    static String[] people;
    static int[] indeg, parentOf;
    static ArrayList<Integer>[] graph;
    static HashMap<String, Integer> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        people = new String[n];
        indeg = new int[n];
        parentOf = new int[n];
        graph = new ArrayList[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            people[i] = st.nextToken();
            graph[i] = new ArrayList<>();
            parentOf[i] = -1;
        }

        Arrays.sort(people);
        for (int i = 0; i < n; i++) map.put(people[i], i);

        int m = Integer.parseInt(br.readLine());
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int to = toIndex(st.nextToken());
            int from = toIndex(st.nextToken());

            graph[from].add(to);
            indeg[to]++;
        }
    }

    static void solve() {
        bfs();
        System.out.println(count);

        for (String name : people) {
            sb.append(name).append(' ');

            ArrayList<String> child = new ArrayList<>();
            for (int cand : graph[toIndex(name)]) {
                if (parentOf[cand] == toIndex(name)) child.add(toName(cand));
            }
            Collections.sort(child);
            sb.append(child.size()).append(' ');

            for (String s : child) sb.append(s).append(' ');
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                que.add(i);
                count++;
                sb.append(toName(i)).append(' ');
            }
        }
        sb.append('\n');
        
        while (!que.isEmpty()) {
            int from = que.poll();
            for (int to : graph[from]) {
                if (--indeg[to] == 0) {
                    que.add(to);
                    parentOf[to] = from;
                }
            }
        }
    }

    static int toIndex(String name) {
        return map.get(name);
    }
    
    static String toName(int index) {
        return people[index];
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}