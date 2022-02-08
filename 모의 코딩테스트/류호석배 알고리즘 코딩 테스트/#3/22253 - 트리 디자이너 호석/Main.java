import java.util.*;
import java.io.*;

public class Main {
    static int n, mod = 1_000_000_000 + 7;
    static long ans;
    static int[] nums;
    static int[] selected;
    static ArrayList<Integer>[] tree;
    static ArrayList<Integer> route = new ArrayList<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];
        tree = new ArrayList[n + 1];
        ans = n;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree[from].add(to);
            tree[to].add(from);
        }
    }

    static void pre(int parent, int node) {
        if (node != 1) tree[node].remove(tree[node].indexOf(parent));

        for (int to : tree[node]) pre(node, to);
    }

    static void solve() {
        pre(0, 1);
        route.add(1);

        dfs(1);
        System.out.println(ans);
    }

    static void dfs(int from) {
        if (tree[from].size() == 0) {
            updateAns();
            return;
        }

        for (int to : tree[from]) {
            route.add(to);
            dfs(to);
            route.remove(route.indexOf(to));
        }
    }

    static void updateAns() {
        for (int size = 2; size <= route.size(); size++) {
            selected = new int[size + 1];
            for (int i = 0; i <= size; i++) selected[i] = -1;
            
            rec_func(1, -1);
        }
    }

    static void rec_func(int k, int pre) {
        if (k == selected.length) {
            ans++;
            ans %= mod;
            return;
        }

        for (int i = pre + 1; i < route.size(); i++) {
            if (selected[k - 1] <= nums[route.get(i)]) {
                selected[k] = nums[route.get(i)];
                rec_func(k + 1, i);
                selected[k] = -1;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        input();
        solve();
    }
}