import java.util.*;
import java.io.*;

public class Main {
    static int q, index;
    static long ans;
    static PriorityQueue<Integer>[] info;
    static HashMap<String, Integer> map = new HashMap<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        q = Integer.parseInt(br.readLine());

        info = new PriorityQueue[100000];
        for (int i = 0; i < 100000; i++) info[i] = new PriorityQueue<>(Comparator.reverseOrder());

        while (q --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            int count = Integer.parseInt(st.nextToken());
            
            if (query == 1) {
                if (!map.containsKey(name)) map.put(name, index++);

                int i = map.get(name);
                while (count --> 0) info[i].add(Integer.parseInt(st.nextToken()));
            }
            else {
                if (!map.containsKey(name)) continue;

                int i = map.get(name);
                while (count --> 0) {
                    if (info[i].isEmpty()) break;
                    ans += info[i].poll();
                }
            }
        }
    }

    static void solve() {
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}